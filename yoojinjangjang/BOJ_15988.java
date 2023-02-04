package com.yoojin.dp;

import java.util.Scanner;

public class BOJ_15988 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder b = new StringBuilder();
		int T = sc.nextInt();
		long[] dp = new long[1_000_001]; // int로 지정하는 경우 에러 발생 whyyyyyyyyyyrano 
		dp[0] = 0;
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;
		
		for(int j = 4;j<=1_000_000;j++) {	// db 미리 계산 !
			dp[j] = (dp[j-1]+ dp[j-2]+ dp[j-3])%1000_000_009;
		}
		for(int i = 0;i<T;i++) {
			int N = sc.nextInt();
			
			b.append(dp[N] + "\n");
		}
		System.out.println(b.toString());
	}
}
