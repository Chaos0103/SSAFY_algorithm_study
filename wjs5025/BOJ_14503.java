import java.io.*;
import java.util.*;

public class BOJ_14503 {
	static int N, M;
	static int clearCnt = 0; // 청소 개수
	static int[][] map;
	static int nowR, nowC, direction;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static boolean isDone = false;

	// 반시계로 회전
	static void rotate() {
		switch (direction) {
		case 0:
			direction = 3;
			return;
		case 1:
			direction = 0;
			return;
		case 2:
			direction = 1;
			return;
		case 3:
			direction = 2;
			return;
		}
	}

	static void clear(int r, int c) {
		map[r][c] = 2;
		clearCnt++;
	}

	static void goStraight() {
		switch (direction) {
		case 0:
			if (map[nowR - 1][nowC] != 1)
				nowR -= 1;
			return;
		case 1:
			if (map[nowR][nowC + 1] != 1)
				nowC += 1;
			return;
		case 2:
			if (map[nowR + 1][nowC] != 1)
				nowR += 1;
			return;
		case 3:
			if (map[nowR][nowC - 1] != 1)
				nowC -= 1;
			return;
		}
	}

	static boolean goBack() {
		// 북쪽을 볼때
		if (direction == 0) {
			if (nowR + 1 < N && map[nowR + 1][nowC] != 1) {
				nowR += 1;
				return true;
			} else {
				isDone = true;
				return false;
			}
		}
		// 동쪽을 볼때
		if (direction == 1) {
			if (nowC - 1 >= 0 && map[nowR][nowC - 1] != 1) {
				nowC -= 1;
				return true;
			} else {
				isDone = true;
				return false;
			}
		}
		// 남쪽을 볼때
		if (direction == 2) {
			if (nowR - 1 >= 0 && map[nowR - 1][nowC] != 1) {
				nowR -= 1;
				return true;
			} else {
				isDone = true;
				return false;
			}
		}
		// 서쪽을 볼때
		if (direction == 3) {
			if (nowC + 1 < M && map[nowR][nowC + 1] != 1) {
				nowC += 1;
				return true;
			} else {
				isDone = true;
				return false;
			}
		}
		return false;
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		map = new int[N][M];

		nowR = sc.nextInt();
		nowC = sc.nextInt();
		direction = sc.nextInt();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		// 0이면 북쪽 , 1이면 동쪽 ,2면 남쪽, 3이면 서쪽

		while (!isDone) {
			boolean existBlank = false;

			// 1. 현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
			if (map[nowR][nowC] == 0)
				clear(nowR, nowC);

			// 2. 4방 탐색.
			for (int i = 0; i < 4; i++) {
				int nx = nowR + dx[i];
				int ny = nowC + dy[i];

				if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
					if (map[nx][ny] == 0) {
						existBlank = true;
					}
				}
			}

			// 2. 현재 칸 주변 4칸 중 청소되지 않은 빈칸이 없는 경우.
			if (!existBlank) {
				goBack();
			}
			// 3. 현재 칸 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우
			else {
				rotate();
				if (direction == 0) {
					if (nowR - 1 >= 0 && map[nowR - 1][nowC] == 0) {
						goStraight();
					}
				} else if (direction == 1) {
					if (nowC + 1 < M && map[nowR][nowC + 1] == 0) {
						goStraight();
					}
				} else if (direction == 2) {
					if (nowR + 1 < N && map[nowR + 1][nowC] == 0) {
						goStraight();
					}
				} else if (direction == 3) {
					if (nowC - 1 >= 0 && map[nowR][nowC - 1] == 0) {
						goStraight();
					}
				}

			}

		}

//		print();
		System.out.println(clearCnt);

	}

	static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();

		}
	}
}
