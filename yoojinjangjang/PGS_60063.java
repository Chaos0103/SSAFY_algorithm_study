package com.yoojin.programmers;

import java.awt.Point;
import java.util.*;

public class PGS_60063 {

	public int solution(int[][] board) {
		int n = board.length;
		int answer = 0;
		boolean[][][] visited = new boolean[2][n][n]; // 0: 가로, 1: 세로
		int[] dr = { -1, 1, 0, 0 }; // 하, 상, 좌, 우
		int[] dc = { 0, 0, -1, 1 };
		int[][] start = new int[][] { { 0, 0 }, { 0, 1 }, { 0 } }; // 2개의 좌표값과 소요시간

		Queue<int[][]> q = new ArrayDeque<>();
		q.offer(start);
		visited[0][0][0] = true;
		visited[0][0][1] = true;

		// BFS
		while (!q.isEmpty()) {
			int[][] cur = q.poll();
			int r1 = cur[0][0];
			int c1 = cur[0][1];
			int r2 = cur[1][0];
			int c2 = cur[1][1];
			int type = type(cur[0], cur[1]);
			int time = cur[2][0];

			if ((r1 == n - 1 && c1 == n - 1) || (r2 == n - 1 && c2 == n - 1)) {
				answer = time;
				break;
			}

			for (int d = 0; d < 4; d++) {
				int nr1 = r1 + dr[d];
				int nc1 = c1 + dc[d];
				int nr2 = r2 + dr[d];
				int nc2 = c2 + dc[d];

				if (oob(nr1, nc1, n) && oob(nr2, nc2, n) && board[nr1][nc1] != 1 && board[nr2][nc2] != 1) {
					// 로봇을 방향전환 없이 상하좌우 추가
					if ((!visited[type][nr1][nc1] || !visited[type][nr2][nc2])) {
						visited[type][nr1][nc1] = true;
						visited[type][nr2][nc2] = true;
						q.offer(new int[][] { { nr1, nc1 }, { nr2, nc2 }, { time + 1 } });
					}

					// 로봇이 가로이면서 방향이 상하 or 세로이면서 좌우일 때 회전 가능
					int typeChange = 1 ^ type;

					if ((type == 0 && d < 2) || (type == 1 && d > 1)) {
						if (!visited[typeChange][nr1][nc1] || !visited[typeChange][r1][c1]) {
							visited[typeChange][nr1][nc1] = true;
							visited[typeChange][r1][c1] = true;
							q.offer(new int[][] { { nr1, nc1 }, { r1, c1 }, { time + 1 } });
						}

						if (!visited[typeChange][nr2][nc2] || !visited[typeChange][r2][c2]) {
							visited[typeChange][nr2][nc2] = true;
							visited[typeChange][r2][c2] = true;
							q.offer(new int[][] { { nr2, nc2 }, { r2, c2 }, { time + 1 } });
						}
					}

				}
			}
		}

		return answer;
	}

	public boolean oob(int r, int c, int n) {
		return 0 <= r && r < n && 0 <= c && c < n;
	}

	// 두 좌표가 가로이면 0, 세로이면 1 반환
	public int type(int[] pos1, int[] pos2) {
		if (pos1[0] == pos2[0])
			return 0;
		return 1;
	}
}
