package com.yoojin.boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_2225 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int dp[][] = new int[K+1][N+1];
		
		Arrays.fill(dp[1], 1);
		for(int i =0;i<=K;i++) {
			dp[i][0] = 1;
		}
		
		for(int i =2;i<=K;i++) { // 행을 돌면서
			for(int j = 1;j<=N;j++) { // 열을 돌면서
				dp[i][j]  = dp[i-1][j] + dp[i][j-1];
				dp[i][j] %= 1_000_000_000;
			}
		}
		
		System.out.println(dp[K][N]);
		
	}
}
