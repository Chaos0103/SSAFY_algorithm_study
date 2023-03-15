package com.yoojin.boj.g5;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_2294 {
	static final int INF = 100001;
	static int N,K;
	static int[] coins;
	static int[] dp;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		coins = new int[N];
		dp = new int[K+1];
		for(int i =0;i<N;i++) {
			coins[i]= Integer.parseInt(br.readLine());
		}
		Arrays.sort(coins);
		Arrays.fill(dp, INF);
		dp[0] = 0;
		for(int j=0;j<N;j++) {
			for(int i = coins[j];i<=K;i++) {
				dp[i]= Math.min(dp[i], 1+dp[i-coins[j]]); 
			}
		}
		if(dp[K] == INF) {
			System.out.println(-1);
		} else {
			System.out.println(dp[K]);
		}
	}
}