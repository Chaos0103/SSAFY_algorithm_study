package BOJ_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1149 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		
		int[][] grid = new int[n][3];
		int[][] dp = new int[n][3];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = grid[i][j];
			}
		}
		
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < 3; j++) {
				dp[i][0] = Math.min(dp[i - 1][1] + grid[i][0], dp[i - 1][2] + grid[i][0]);
				dp[i][1] = Math.min(dp[i - 1][0] + grid[i][1], dp[i - 1][2] + grid[i][1]);
				dp[i][2] = Math.min(dp[i - 1][0] + grid[i][2], dp[i - 1][1] + grid[i][2]);
			}
		}
		
		int ans = Math.min(dp[n - 1][0], Math.min(dp[n - 1][1], dp[n - 1][2]));
		
		System.out.println(ans);
	}
}
