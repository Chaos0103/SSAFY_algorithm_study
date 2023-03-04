package algorithm_study.day27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <pre>
 * SSAFY 알고리즘 스터디 27일차 - 주사위 굴리기 2
 * 주사위 회전 -> 메소드 호출로 수행
 * 주사위 이동 -> BFS 로 수행
 * </pre>
 * 
 * @author YoungHwan
 *
 */
public class BOJ_23288 {
	// 방향배열(북, 동, 남, 서), 방향변수
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int direction;

	static int N, M, K, result;
	static int[][] map;
	static boolean used[][];
	static Queue<Pos> q;
	static Dice dice = new Dice();

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		init();
		solution();
		print();
	}

	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		used = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	private static void solution() {
		int r = 0;
		int c = 0;
		direction = 1;
		for (int k = 0; k < K; k++) {
			int nr = r + dr[direction];
			int nc = c + dc[direction];
			// 범위를 벗어난 경우 방향 반대 전환
			if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
				direction = (direction + 2) % 4;
				nr = r + dr[direction];
				nc = c + dc[direction];
			}
			r = nr;
			c = nc;
			// 주사위를 굴림
			dice.rollDice();
			bfs(new Pos(r, c));	// BFS 탐색 후 점수 계산
			setNextDirection(r, c);	// 다음 방향 선택
		}
	}

	private static void setNextDirection(int r, int c) {
		// 주사위의 아랫면이 큰 경우 시계방향으로 회전
		if (map[r][c] < dice.bottom) {
			direction = (direction + 1) % 4;
		}
		// 주사위의 아랫면이 작은 경우 반시계방향으로 회전
		else if (map[r][c] > dice.bottom) {
			direction = (direction + 3) % 4;
		}
	}

	private static void bfs(Pos start) {
		q = new LinkedList<>();
		used = new boolean[N][M];
		used[start.r][start.c] = true;
		q.add(start);
		int count = 1;
		int num = map[start.r][start.c];
		while (!q.isEmpty()) {
			Pos now = q.poll();
			// 동서남북 갈 수 있는 칸 확인
			for (int i = 0; i < 4; i++) {
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];
				// 범위를 벗어나면 이동 불가
				if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
					continue;
				}
				// 이미 방문한 곳은 가지 않음
				if (used[nr][nc]) {
					continue;
				}
				// 갈 수 있는 위치(출발점과 같은 값이어야 이동 가능)
				if (map[nr][nc] == num) {
					used[nr][nc] = true;
					q.add(new Pos(nr, nc));
					count++;
				}
			}
		}
		result += num * count;
	}

	private static void print() {
		System.out.println(result);
	}
	
	// 주사위 클래스
	static class Dice {
		int top = 1;
		int bottom = 6;
		int left = 4;
		int right = 3;
		int front = 5;
		int rear = 2;
				
		public void rollDice() {
			// 각 방향으로 구름
			if (direction == 0) {
				rollNorth();
			} else if (direction == 1) {
				rollEast();
			} else if (direction == 2) {
				rollSouth();
			} else {
				rollWest();
			}
		}
		
		public void rollWest() {
			int temp = top;
			top = right;
			right = bottom;
			bottom = left;
			left = temp;
		}

		public void rollSouth() {
			int temp = top;
			top = rear;
			rear = bottom;
			bottom = front;
			front = temp;
		}

		public void rollEast() {
			int temp = top;
			top = left;
			left = bottom;
			bottom = right;
			right = temp;
		}

		public void rollNorth() {
			int temp = top;
			top = front;
			front = bottom;
			bottom = rear;
			rear = temp;
		}
	}

	static class Pos {
		int r;
		int c;

		/**
		 * @param r
		 * @param c
		 */
		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
}
