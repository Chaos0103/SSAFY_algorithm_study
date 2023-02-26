package algorithm_study.day24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <pre>
 * 접근
 * bfs로 영역을 구분하고 그다음 다시 최소 거리를 구하는 bfs 수행
 * 세부 접근
 * 각각의 정보를 담을 클래스를 사용(좌표, 영역번호, 현재까지의 다리 길이)
 * 
 * 메모리 사용량이 이게 맞는지...?
 * </pre>
 * 
 * @author YoungHwan
 *
 */
public class BOJ_2146 {
	static class Pos {
		private int r; // 행 번호
		private int c; // 열 번호
		private int index; // 대륙 번호
		private int dist; // 현재까지의 다리 길이

		/**
		 * @param r
		 * @param c
		 * @param index
		 */
		public Pos(int r, int c, int index) {
			super();
			this.r = r;
			this.c = c;
			this.index = index;
		}

		/**
		 * @param r
		 * @param c
		 * @param index
		 * @param dist
		 */
		public Pos(int r, int c, int index, int dist) {
			super();
			this.r = r;
			this.c = c;
			this.index = index;
			this.dist = dist;
		}

		/**
		 * @return the r
		 */
		public int getR() {
			return r;
		}

		/**
		 * @return the c
		 */
		public int getC() {
			return c;
		}

		/**
		 * @return the index
		 */
		public int getIndex() {
			return index;
		}

		/**
		 * @return the dist
		 */
		public int getDist() {
			return dist;
		}

	}

	// 상하좌우 방향배열
	static int[] dr = { -1, 1, 0, 0, };
	static int[] dc = { 0, 0, -1, 1 };

	static int n, index = 1, min = Integer.MAX_VALUE; // 지도의 크기, 영역의 번호, 최소거리
	static int[][] map, used; // 지도 배열, 방문배열
	static Queue<Pos> q; // BFS 큐

	public static void main(String[] args) throws Exception {
		// 값 입력 및 초기화
		init();
		// 각 구역을 분리
		splitAreas();
		// 다리 길이의 최솟값 갱신
		updateMin();
		// 최솟값 출력
		print();
	}

	// 출력 메소드
	private static void print() {
		System.out.println(min);
	}

	// 최솟값 갱신 메소드
	private static void updateMin() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				resetUsed();
				if (used[i][j] == 0 && map[i][j] != 0) {
					used[i][j] = 1;
					getMin(new Pos(i, j, map[i][j]));
				}
			}
		}
	}

	// 방문배열 초기화
	private static void resetUsed() {
		for (int i = 0; i < n; i++) {
			Arrays.fill(used[i], 0);
		}
	}

	// 대륙 구분 메소드
	private static void splitAreas() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] == 1 && used[i][j] == 0) {
					used[i][j] = 1;
					map[i][j] = index;
					setAreaIndex(new Pos(i, j, index));
					index++;
				}
			}
		}
	}

	// 초기화 메소드
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		// 값 입력 및 초기화
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		used = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	// 각 다리 길이의 최솟값 갱신
	private static void getMin(Pos start) {
		q = new LinkedList<>();
		q.add(start);
		while (!q.isEmpty()) {
			// 현재 위치의 정보 초기화
			Pos now = q.poll();
			int r = now.getR();
			int c = now.getC();
			int nowIdx = now.getIndex();
			int dist = now.getDist();

			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				// 범위를 벗어나거나 같은 대륙이면 무시
				if (nr < 0 || nc < 0 || nr >= n || nc >= n || used[nr][nc] == 1 || map[nr][nc] == nowIdx)
					continue;
				// 바다면 경로에 포함
				if (map[nr][nc] == 0) {
					used[nr][nc] = 1;
					q.add(new Pos(nr, nc, nowIdx, dist + 1));
				}
				// 다른 대륙이면 최솟값 갱신
				if (map[nr][nc] > 0) {
					min = Math.min(dist, min);
				}
			}
		}
	}

	// 각각의 섬들에 번호 부여
	private static void setAreaIndex(Pos start) {
		q = new LinkedList<>();
		q.add(start);
		while (!q.isEmpty()) {
			Pos now = q.poll();
			int r = now.getR();
			int c = now.getC();

			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				// 범위를 벗어나거나 이미 영역에 포함되었거나 바다면 포함 X
				if (nr < 0 || nc < 0 || nr >= n || nc >= n || used[nr][nc] == 1 || map[nr][nc] == 0)
					continue;
				// 같은 대륙으로 포함
				used[nr][nc] = 1;
				map[nr][nc] = now.getIndex();
				q.add(new Pos(nr, nc, map[nr][nc]));
			}
		}
	}

}