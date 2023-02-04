package BJ;

import java.util.Scanner;

/*
 * 문제 : RGB 거리
 * 풀이시간 : 30분
 */

public class BOJ_1149 {
	
	public static void main(String[] args) {
		int r = 0;
		int g = 1;
		int b = 2;
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[][] dp = new int[n][3];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < 3; j++) {
				dp[i][j] = sc.nextInt();
			}
		}
		for(int i = 1; i < n; i++) {
			dp[i][r] += Math.min(dp[i-1][g], dp[i-1][b]);
			dp[i][g] += Math.min(dp[i-1][r], dp[i-1][b]);
			dp[i][b] += Math.min(dp[i-1][r], dp[i-1][g]);
		}
		
		System.out.println(Math.min(Math.min(dp[n-1][r], dp[n-1][g]), dp[n-1][b]));
	}
}
