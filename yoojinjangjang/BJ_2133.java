package com.yoojin.boj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_2133 {
	static int[] dp;
	static int N;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[31];
		dp[0] = 1;
		dp[2] = 3;
		
		for(int n =4;n<=N;n += 2) {
			dp[n] = dp[n-2] *3;
			
			for(int i = n-4;i>=0;i-=2) {
				dp[n] += dp[i] * 2;
			}
		}
		
		System.out.println(dp[N]);
		
	}
}
