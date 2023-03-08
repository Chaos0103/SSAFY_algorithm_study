package algorithm_study.day30;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <pre>
 * SSAFY 알고리즘 스터디 30일차 - 서울 지하철 2호선
 * 솔루션
 * 
 * 접근
 * 1. 인접 리스트 사용
 * 2. 순환선에 속하는 역 리스트를 생성 -> DFS? BFS?
 * 3. 순환선 리스트에서 다른 역들까지의 거리 계산 -> DFS? BFS?
 * 
 * 못푼 이유
 * 사이클 체크를 어떻게 하는지에 대한 구현 접근을 못했음
 * </pre>
 * 
 * @author YoungHwan
 *
 */
public class BOJ_16947 {
	static boolean visited[], isCycle;
	static int N, distance[];
	static ArrayList<Integer>[] graph;
	static Queue<Integer> queue = new LinkedList<Integer>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		init();
		process();
		print();
	}

	private static void init() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(in.readLine());

		visited = new boolean[N + 1];
		distance = new int[N + 1];
		Arrays.fill(distance, -1);

		// 역과 역을 연결하는 구간의 정보 입력
		graph = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}
	}

	private static void process() {
		// 경로에서 순환선 찾기
		DFS(0, 1);
		// 각 역과 순환선의 거리 구하기
		BFS();
	}

	private static void DFS(int prev, int now) {
		// 탐색하는 역 방문 체크
		visited[now] = true;
		// 연결된 구간 모두 탐색
		for (int next : graph[now]) {
			// 직전 방문지가 아니면서 이미 방문한 곳에 도착 => 사이클을 이뤘다!
			if (visited[next] && next != prev) {
				isCycle = true;
				distance[next] = 0;
				queue.add(next);
				break;
			} else if (!visited[next]) {
				// 아직 방문하지 않은 역 탐색
				DFS(now, next);
				// 사이클에 속하는 경우
				if (isCycle) {
					// 이미 사이클에 속한 곳을 만남 => 사이클을 다 돌았다!
					if (distance[next] == 0) {
						isCycle = false;
					} else {
						distance[next] = 0;
						queue.add(next);
					}
					return;
				}
			}
		}
	}

	private static void BFS() {
		int cnt = 1;
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int now = queue.poll();
				// 연결된 구간을 다음 탐색지에 추가
				for (int next : graph[now]) {
					// 거리가 이미 구해진 역은 제외
					if (distance[next] != -1)
						continue;
					distance[next] = cnt;
					queue.add(next);
				}
			}
			cnt++; // 순환선과의 거리
		}
	}

	private static void print() {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			sb.append(distance[i]).append(" ");
		}
		System.out.println(sb.toString());
	}
}
