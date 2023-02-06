import java.util.Scanner;

/**
 * SSAFY 알고리즘 스터디 5일차 - DP
 * 23.02.04 RGB 거리
 * 30분 소요
 * 
 * ### 틀린 이유
 * @author YoungHwan
 *
 */
public class BOJ_1149 {
	static int n;
	static int min = Integer.MAX_VALUE;
	static int[][] dp;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		dp = new int[n][3];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 3; j++) {
				dp[i][j] = sc.nextInt();
			}
		}
		
		for (int i = 1; i < n; i++) {
			dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + dp[i][0];
			dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + dp[i][1];
			dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + dp[i][2];
		}
		
		for (int i = 0; i < 3; i++) {
			min = Math.min(dp[n-1][i], min);
		}
		System.out.println(min);
	}
}
