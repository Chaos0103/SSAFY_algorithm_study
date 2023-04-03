import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ_2589 {
	static int N, M;
	static String[][] map;
	static boolean[][] visited;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	static int max = Integer.MIN_VALUE;

	static class Node {
		int r;
		int c;
		int time;

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", time=" + time + "]";
		}

		public Node(int r, int c, int time) {
			super();
			this.r = r;
			this.c = c;
			this.time = time;
		}
	}

	static void bfs(Node start) {
		Queue<Node> q = new ArrayDeque<Node>();
		q.offer(start);

		while (!q.isEmpty()) {
			Node current = q.poll();
			visited[current.r][current.c] = true;
			max = Math.max(max, current.time);

			for (int i = 0; i < 4; i++) {
				int nx = current.r + dx[i];
				int ny = current.c + dy[i];

				if (!(nx >= 0 && ny >= 0 && nx < N && ny < M))
					continue;
				if (visited[nx][ny])
					continue;
				if (map[nx][ny].equals("L")) {
					visited[nx][ny] = true;
					q.offer(new Node(nx, ny, current.time + 1));
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] tmp = br.readLine().split(" ");
		N = Integer.parseInt(tmp[0]);
		M = Integer.parseInt(tmp[1]);

		map = new String[N][M];

		for (int i = 0; i < N; i++) {
			tmp = br.readLine().split("");
			for (int j = 0; j < M; j++) {
				map[i][j] = tmp[j];
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j].equals("L")) {
					visited = new boolean[N][M];
					bfs(new Node(i, j, 0));
				}
			}
		}
		System.out.println(max);
	}
}
