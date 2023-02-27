package algorithm_study.day25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 솔루션 봤음
 * 풀이는 약 30분~1시간
 * 이동 문제는 일단 DFS / BFS 로 생각해보는 습관을 들여야 할 것 같음
 * </pre>
 * @author YoungHwan
 *
 */
public class BOJ_14503 {
	// 방향 배열
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static int n, m, row, col, direction; // 방의 크기
	static int result; // 청소한 칸의 수
	static int[][] map; // 방
	static boolean[][] used; // 방문배열

	// 입출력
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	// 메인 메소드
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력 및 초기화
		input();
		// 재귀 수행
		clean(row, col, direction);
		// 결과 출력
		print();
	}

	// 입력 및 초기화
	private static void input() throws IOException {
		// 밤의 크기 입력
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		// 배열 및 방문 배열 생성
		map = new int[n][m];
		used = new boolean[n][m];

		// 로봇 청소기 위치, 방향 입력
		st = new StringTokenizer(br.readLine());
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		direction = Integer.parseInt(st.nextToken());

		// 방 상태 입력
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	// 출력 
	private static void print() {
		System.out.println(result);
	}

	// 로봇 청소기 청소 및 이동 메소드
	private static void clean(int r, int c, int d) {
		boolean isValid = false;

		// 1. 현재 위치를 청소 할 수 있으면 청소
		clean(r, c);

		// 현재 바라보는 방향부터 탐색 수행
		for (int i = 0; i < 4; i++) {
			d = turnLeft(d);
			int nr = r + dr[d];
			int nc = c + dc[d];
			// 청소가 가능하면 값 증가 후 재귀 호출
			if (!isDone(nr, nc)) {
				clean(nr, nc, d);
				isValid = true;
				break;
			}
		}

		// 청소가 불가능한 경우
		// 바라보는 방향을 유지한채 한칸 뒤로 이동
		moveBackward(r, c, d, isValid);
	}

	// 청소 가능하면 청소
	private static void clean(int r, int c) {
		if (map[r][c] == 0 && !used[r][c]) {
			used[r][c] = true;
			result++;
		}
	}

	// 후진 가능하면 후진
	private static void moveBackward(int r, int c, int d, boolean isValid) {
		if (!isValid) {
			int nr = r - dr[d];
			int nc = c - dc[d];
			// 후진이 가능하면 후진
			if (!isWall(nr, nc)) {
				clean(nr, nc, d);
			}
		}
	}

	// 청소가 끝난 곳인지 확인
	private static boolean isDone(int nr, int nc) {
		return (nr < 0 || nc < 0 || nr >= n || nc >= m || map[nr][nc] != 0 || used[nr][nc]);
	}

	// 벽인지 확인
	private static boolean isWall(int nr, int nc) {
		return (nr < 0 || nc < 0 || nr >= n || nc >= m || map[nr][nc] == 1);
	}

	// 반시계 방향으로 회전
	private static int turnLeft(int direction) {
		direction--;
		if (direction < 0) {
			direction = 3;
		}
		return direction;
	}
}