package com.yoojin.dp;

import java.util.Scanner;

public class BOJ_11057 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		long[][] dp = new long[N+1][10];
		for(int i = 1;i<=10;i++) {
			// dp[1] 초기화
			dp[1][i-1] = 1;
		}
		
		for(int i = 1;i<=N;i++) {
			for(int j = 0;j<10;j++) {
				for(int s = 0;s<=j;s++) {
					dp[i][j] += dp[i-1][s];
					dp[i][j] %= 10007; // 여기서 나머지로 안구해주면 '틀렸습니다' 나옴  왜쥬? 
				}
			}
		}
		
		long result = 0;
		for(int i = 0;i<10;i++) {
			result += dp[N][i];
		}
		System.out.println(result%10007);
	}
}
