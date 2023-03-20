package Algorithm_230321;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2573 {
	static int N, M, year, cnt = 1;
	static int[][] map, sea;
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };
	static boolean[][] visit;

	public static void main(String[] args) throws IOException {
		init();

		int cir = 0;
		
		while(true) {
			sea = new int[N][M];
			visit = new boolean[N][M];
			cir = 0;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(!visit[i][j] && map[i][j] != 0) {
						dfs(i, j);
						cir++;
					}
				}
			}
			
			map = sea;
			year++;
			
			if(cir == 0) {
				year = 0;
				break;
			} else if(cir > 1) {
				year-=1;
				break;
			}
		}

		System.out.println(year);
	}
	
	public static void dfs(int row, int col) {
		if(map[row][col] == 0 || visit[row][col]) {
			return;		
		}
		
		int seaNum = 0;

		for (int dir = 0; dir < 4; dir++) {
			int nx = col + dc[dir];
			int ny = row + dr[dir];

			if(checkRange(ny, nx)) continue;
			if(map[ny][nx] == 0) {
				seaNum++;
			}
		}

		int val = map[row][col] - seaNum;
		sea[row][col] = val >= 0 ? val : 0;
		
		visit[row][col] = true;
		
		for (int dir = 0; dir < 4; dir++) {
			int nr = row + dr[dir];
			int nc = col + dc[dir];

			if (checkRange(nr, nc) || visit[nr][nc] || map[nr][nc] == 0) {
				continue;
			}
			dfs(nr, nc);
		}
	}

	public static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = stoi(st.nextToken());
			}
		}
	}

	public static boolean checkRange(int y, int x) {
		return y < 0 || y >= N || x < 0 || x >= M;
	}

	public static int stoi(String str) {
		return Integer.parseInt(str);
	}
}