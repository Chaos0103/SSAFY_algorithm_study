package com.yoojin.boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_2293 {
	static int N,K;
	static int[] dp;
	static int[] values;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		values = new int[N];
		for(int i=0;i<N;i++) {
			values[i] = Integer.parseInt(br.readLine());
		}
		dp = new int[K+1];
		dp[0] = 1;
		for(int i=0;i<N;i++) {
			for(int j = values[i];j<=K;j++) {
				dp[j] = dp[j] + dp[j-values[i]];
			}
		}
		
		System.out.println(dp[K]);
		

	}
}
