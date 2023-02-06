package BJ;

import java.util.Scanner;

/*
 * 문제 : 오르막 수
 * 검색 : 모듈러 연산   
 */
public class BOJ_11057 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		int[][] dp = new int[n+1][10];
		
		for(int i = 0; i < 10; i++) {
			dp[0][i] = 1;
		}
		
		for(int i = 1; i < n+1; i++) {
			for(int j = 0; j < 10; j++) {
				for(int k = j; k < 10; k++) {
					dp[i][j] += dp[i-1][k];
					dp[i][j] %= 10007;
				}
			}
		}
		System.out.println(dp[n][0]% 10007  );
	}

}
