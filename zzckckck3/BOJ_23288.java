package Algorithm_230303;

import java.io.*;
import java.util.*;

/*
 * 주사위의 전개도 혹은 주사위의 수는 이동 방향과 관련이 있음
 * - 이동방향에 칸이 없다면 반대로 회전
 * - 도착한 칸에 대한 점수 획득
 * - 이동 방향 결정 (주사위의 수 : A / 이동한 칸 : B)
 *   - A > B : 시계방향 90도 회전
 *   - A < B : 반시계 방향 90도 회전
 *   - A = B : 변화 없음
 * */

public class BOJ_23288 {
	static int N, M, K;
	static int[][] map;
	static int ans = 0;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static boolean [][] visited;
	
	static class Dice {
		int row, col;
		int top, bottom, north, south, east, west;
		int dir;  // 상 우 하 좌 / 1 2 3 4
		public Dice(int row, int col, int top, int bottom, int north, int south, int east, int west, int dir) {
			this.row = row;
			this.col = col;
			this.top = top;
			this.bottom = bottom;
			this.north = north;
			this.south = south;
			this.east = east;
			this.west = west;
			this.dir = dir;
		}
	}
	
	static Dice dice = new Dice(0, 0, 1, 6, 2, 5, 3, 4, 2);  // 좌표가 0, 0, 1이 위로 가있고 방향이 우측인 주사위
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < K; i++) {
			setDiceBeforeMove();
			setDiceAfterMove();
			addScore(dice.row, dice.col);
			visited = new boolean[N][M];
		}
		
		System.out.println(ans);
	}
	
	static void printArr() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j] + " ");
				//System.out.print("(" + i + "," + j + ")");
			}
			System.out.println();
		}
	}
	
	static void printDice() {
		System.out.println("  " + dice.north + "  ");
		System.out.println(dice.west + " " + dice.top + " " + dice.east);
		System.out.println("  " + dice.south + "  ");
		System.out.print("  " + dice.bottom + "  (" + (dice.row+1) + "," + (dice.col+1) + ") ");
		if(dice.dir == 1) {
			System.out.println("상");
		} else if (dice.dir == 2) {
			System.out.println("우");
		} else if (dice.dir == 3) {
			System.out.println("하");
		} else if (dice.dir == 4) {
			System.out.println("좌");
		}
	}
	
	static void setDiceBeforeMove() {
		if(dice.dir == 1) { // Start of dir 1 (North)
			if(dice.row < 1) { // 이동 방향에 칸이 없다면
				dice.dir = 3; // 방향을 반대로 바꿔준 뒤
				setDiceBeforeMove(); // 다시 방향 설정
				return; // 다시 나온 함수에선 아무 행동 안하도록 return
			} else { // 이동 방향이 있다면
				dice.row -= 1;
				int temp = dice.top;
				dice.top = dice.south;
				dice.south = dice.bottom;
				dice.bottom = dice.north;
				dice.north = temp;
			}
		} // End of dir 1 (North)
		else if(dice.dir == 3) { // Start of dir 3 (South)
			if(dice.row >= N-1) {
				dice.dir = 1;
				setDiceBeforeMove();
				return;
			} else {
				dice.row += 1;
				int temp = dice.top;
				dice.top = dice.north;
				dice.north = dice.bottom;
				dice.bottom = dice.south;
				dice.south = temp;
			}
		} // End of dir 3 (South)
		else if(dice.dir == 2) { // Start of dir 2 (East)
			if(dice.col >= M-1) {
				dice.dir = 4;
				setDiceBeforeMove();
				return;
			} else {
				dice.col += 1;
				int temp = dice.top;
				dice.top = dice.west;
				dice.west = dice.bottom;
				dice.bottom = dice.east;
				dice.east = temp;
			}
		} // End of dir 2 (East)
		else if(dice.dir == 4) { // Start of dir 4 (West)
			if(dice.col < 1) {
				dice.dir = 2;
				setDiceBeforeMove();
				return;
			} else {
				dice.col -= 1;
				int temp = dice.top;
				dice.top = dice.east;
				dice.east = dice.bottom;
				dice.bottom = dice.west;
				dice.west = temp;
						
			}
		} // End of dir 4 (West)
	}
	
	static void setDiceAfterMove() {
//		System.out.print("진행 후 주사위 밑 : " + dice.bottom + " ");
//		System.out.print("판 : " + map[dice.row][dice.col]+ "\n");
		if(dice.bottom > map[dice.row][dice.col]) { // A > B 일때 시계방향 회전
			dice.dir += 1;
			if (dice.dir > 4) {
				dice.dir %= 4;
			}
		} else if (dice.bottom < map[dice.row][dice.col]) {
			dice.dir -= 1;
			if (dice.dir < 1) {
				dice.dir += 4;
			}
		}
//		System.out.print("돌린 후 방향 : " + dice.dir);
//		if(dice.dir == 1) {
//			System.out.println("상");
//		} else if (dice.dir == 2) {
//			System.out.println("우");
//		} else if (dice.dir == 3) {
//			System.out.println("하");
//		} else if (dice.dir == 4) {
//			System.out.println("좌");
//		}
	}
	
	static void addScore(int row, int col) {
		visited[row][col] = true;
		ans += map[row][col];
		
		for (int i = 0; i < 4; i++) {
			int nr = row + dr[i];
			int nc = col + dc[i];
			if(nr < 0 || nr >= N || nc < 0 || nc >= M) {
				continue;
			}
			if(!visited[nr][nc] && map[nr][nc] == map[dice.row][dice.col]) {
				addScore(nr, nc);
			}
		}
		
	}
}
