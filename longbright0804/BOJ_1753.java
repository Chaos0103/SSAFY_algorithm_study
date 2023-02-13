import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * SSAFY 알고리즘 스터디 - 최단경로
 * 
 * @author YoungHwan
 *
 */
public class BOJ_1753 {
	static final int INF = (int) 1e9;
	static int v, e, k; // 정점의 개수, 간선의 개수, 시작점
	static List<ArrayList<Node>> graph = new ArrayList<>(); // 그래프
	static int[] d = new int[20001]; // 최대 정점의 개수 만큼 최단거리 테이블 생성

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		// 정점, 간선 개수, 시작점 입력
		v = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(br.readLine());

		// 그래프 초기화(정점 1부터 v까지)
		for (int i = 0; i <= v; i++) {
			graph.add(new ArrayList<Node>());
		}

		// 간선 입력
		for (int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int u = Integer.parseInt(st.nextToken()); // 시작 정점
			int v = Integer.parseInt(st.nextToken()); // 도착 정점
			int w = Integer.parseInt(st.nextToken()); // 가중치 값
			graph.get(u).add(new Node(v, w)); // u 에서 v 로 가는 간선이 존재하며, 가중치 w 를 가짐
		}

		// 최단거리 테이블 초기화
		Arrays.fill(d, INF);

		// 다익스트라 메소드 호출
		dijkstra(k);

		// 모든 노드로 가기 위한 최단거리 출력
		for (int i = 1; i <= v; i++) {
			// 갈 수 없는 경우 INF 출력
			if (d[i] == INF) {
				System.out.println("INF");
			}
			// 최단거리 출력
			else {
				System.out.println(d[i]);
			}
		}
	}

	// 다익스트라 알고리즘
	private static void dijkstra(int k) {
		// 우선순위 큐 사용
		PriorityQueue<Node> pq = new PriorityQueue<>();

		// 시작 노드로 가기 위한 최단 경로는 0으로 설정하여 큐에 삽입
		pq.offer(new Node(k, 0));
		d[k] = 0;

		// 큐가 빌때까지 수행
		while (!pq.isEmpty()) {
			// 최단거리가 가장 짧은 노드 선택
			Node now = pq.poll();
			int dist = now.distance; // 현재 노드까지의 비용
			int index = now.index; // 현재 노드 번호
			// 현재 노드가 처리된 적있다면 다음 노드로 이동
			if (d[index] < dist) {
				continue;
			}
			// 현재 노드와 연결된 다른 노드들 확인
			for (int i = 0; i < graph.get(index).size(); i++) {
				int cost = d[index] + graph.get(index).get(i).distance;
				// 현재 노드를 거쳐서 다른 노드로 이동하는 경우가 더 짧은 경우
				if (cost < d[graph.get(index).get(i).index]) {
					d[graph.get(index).get(i).index] = cost;
					pq.offer(new Node(graph.get(index).get(i).index, cost));
				}
			}
		}
	}
}

class Node implements Comparable<Node> {
	int index;
	int distance;

	public Node(int index, int distance) {
		this.index = index;
		this.distance = distance;
	}

	@Override
	public int compareTo(Node o) {
		return Integer.compare(this.distance, o.distance);
	}
}