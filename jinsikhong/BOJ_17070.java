package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17070 {
	static int N;
	static int[][] map;
	static int cnt;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(0, 1, 0);
		System.out.println(cnt);
		
		
		
	}
	static void dfs(int x, int y, int direction) { // x좌표, y좌표 , 방향 0 : 가로 , 1 : 세로 , 2 : 대각선
		if(x == N - 1 && y == N - 1) {//맵 끝에 도착 하면
			cnt++;
			return;
		}
		
		if(direction == 0) { // 가로 -> 가로, 대각선
			rowCheck(x, y);
			diagonalCheck(x, y);
			
		}
		
		if(direction == 1) { // 세로 -> 세로, 대각선
			colCheck(x, y);
			diagonalCheck(x, y);
		}
		
		if(direction == 2) { // 대각선 -> 가로, 세로, 대각선
			rowCheck(x, y);
			colCheck(x, y);
			diagonalCheck(x, y);
		}
		
	}
	
	static void rowCheck(int x, int y) {
		if(y+1 >= 0 && y+1 < N && map[x][y+1] == 0) { // 가로 방향 갈 수 있는지
			dfs(x, y+1, 0); //가로방향인채로 탐색
		}
	}
	
	static void colCheck(int x, int y) {
		if(x+1 >= 0 && x+1 < N && map[x+1][y] == 0) { // 세로 방향 갈 수 있는지
			dfs(x+1, y, 1); //세로 방향인채로 탐색
		}
	}
	
	static void diagonalCheck(int x, int y) {
		if(x+1 >= 0 && x+1 < N && y+1 >= 0 && y+1 < N && map[x+1][y+1] == 0 && map[x][y+1] == 0 && map[x+1][y] == 0) {
			dfs(x+1, y+1, 2); //대각선 방향인 채로 탐색
		}
	}
}
