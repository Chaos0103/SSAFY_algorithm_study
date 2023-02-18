import java.io.*;
import java.util.*;

class Node {
	int x;
	int y;

	Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class BOJ_14502 {
	static int N, M;
	static int[][] map;
	static int max = Integer.MIN_VALUE;

	// 순열 만들기
	static ArrayList<Node> spaces = new ArrayList<>(); // 빈 공간 목록
	static ArrayList<Node[]> combinations = new ArrayList<>();
	static Node[] combinationTmp = new Node[3];
	static boolean[] visited;

	// dx dy
	public static int[] dx = { -1, 0, 1, 0 };
	public static int[] dy = { 0, -1, 0, 1 };

	// tmpMap에서 안전구역 구하기
	public static int getSafeArea(int[][] tmpMap) {
		int cnt = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (tmpMap[i][j] == 0)
					cnt++;
			}
		}
		return cnt;
	}

	// 바이러스 퍼뜨리기
	public static int[][] spreadVirus(int[][] tmpMap) {
		Queue<Node> q = new LinkedList<>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (tmpMap[i][j] == 2) {
					q.offer(new Node(i, j));
				}
			}
		}

		while (!q.isEmpty()) {
			int x = q.peek().x;
			int y = q.peek().y;
			q.poll();

			for (int i = 0; i < 4; i++) {
				int nextX = x + dx[i];
				int nextY = y + dy[i];

				if (nextX >= 0 && nextY >= 0 && nextX < N && nextY < M) {
					if (tmpMap[nextX][nextY] == 0) {
						tmpMap[nextX][nextY] = 2;
						q.offer(new Node(nextX, nextY));
					}
				}
			}
		}

		return tmpMap;
	}

	// 벽 만들기
	public static int[][] makeWall(int[][] tmpMap) {
		for (Node node : combinationTmp) {
			tmpMap[node.x][node.y] = 1;
		}
		return tmpMap;
	}

	// 벽을 세우는 모든 경우의 조합 구하기
	public static void getCombination(int idx, int start) {
		if (idx == 3) {
			// 3개가 완성됐으면, 벽만들고 바이러스 작업 후 안전영역 넓이 구하기
			int[][] tmpMap = new int[N][M];

			// 배열 복사
			for (int i = 0; i < N; i++) {
				tmpMap[i] = map[i].clone();
			}

			int cnt = getSafeArea(spreadVirus(makeWall(tmpMap)));
			max = Math.max(cnt, max);
			return;
		}

		for (int i = start; i < spaces.size(); i++) {
			if (visited[i])
				continue;
			visited[i] = true;
			combinationTmp[idx] = spaces.get(i);
			getCombination(idx + 1, i + 1);
			visited[i] = false;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] tmp = br.readLine().split(" ");

		N = Integer.parseInt(tmp[0]);
		M = Integer.parseInt(tmp[1]);

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			tmp = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(tmp[j]);
				if (map[i][j] == 0)
					spaces.add(new Node(i, j));
			}
		}

		visited = new boolean[spaces.size()];

		getCombination(0, 0);

		System.out.println(max);
	}
}