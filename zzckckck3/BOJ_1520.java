package Algorithm_230310;

import java.io.*;
import java.util.*;

public class BOJ_1520 {
	static int N, M; 
	static int[][] map;
	static int[][] cnt;
	
	public static int dfs(int row, int col) {
		int dr[] = {-1, 1, 0, 0};
		int dc[] = {0, 0, -1, 1};
		
		if(row == N-1 && col == M-1) { return 1; }
		
		cnt[row][col] = 0;
		for(int i = 0; i < 4; i++) {
			int nr = row + dr[i];
			int nc = col + dc[i];
			if(0 <= nr && nr < N && 0 <= nc && nc < M && map[nr][nc] < map[row][col]) {
					if(cnt[nr][nc]!=-1) {
					cnt[row][col] += cnt[nr][nc];
				}
				else {
					cnt[row][col] += dfs(nr, nc);
				}
			}
		}
		return cnt[row][col];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		cnt = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				cnt[i][j] = -1;
			}
		}
		dfs(0, 0);
		System.out.println(cnt[0][0]);
	}
}