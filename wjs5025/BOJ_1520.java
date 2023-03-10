package BOJ_1520;

import java.io.*;
import java.util.*;

public class Main {
	static class Pos {
		int r;
		int c;
		int height;

		@Override
		public String toString() {
			return "Pos [r=" + r + ", c=" + c + "]";
		}

		public Pos(int r, int c, int height) {
			super();
			this.r = r;
			this.c = c;
			this.height = height;
		}
	}

	static int N, M;
	static int[][] map;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int cnt = 0;
	static boolean[][] visited;
	static int[][] dp;

	static int dfs(Pos now) {
		
		// 끝까지가면 1 리턴
		if (now.r == N - 1 && now.c == M - 1) {
			return 1;
		}
		
		// 0이면 끝까지 못가는 경우이다.
		dp[now.r][now.c] = 0;

		

		for (int i = 0; i < 4; i++) {
			int nx = now.r + dx[i];
			int ny = now.c + dy[i];

			if (!(nx >= 0 && ny >= 0 && nx < N && ny < M))
				continue;

			if (map[nx][ny] < now.height) {
				// 아직 방문하지 않은 곳이라면,
				if (dp[nx][ny] == -1) {
					dp[now.r][now.c]+= dfs(new Pos(nx,ny,map[nx][ny])); 
				}
				// 방문했던 곳이고 0이 아니면(못가는 경우가 아니라면)
				else if (dp[nx][ny] >= 1) {
					dp[now.r][now.c] += dp[nx][ny];
				}
			}
		}
		cnt = dp[now.r][now.c];
		return dp[now.r][now.c];
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		map = new int[N][M];
		visited = new boolean[N][M];
		dp = new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		// dp 배열 초기화
		for(int[] d : dp) {
			Arrays.fill(d, -1);
		}

		dfs(new Pos(0, 0, map[0][0]));
		System.out.println(cnt);
	}

	static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}
