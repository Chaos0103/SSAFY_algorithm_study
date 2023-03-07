package com.yoojin.boj;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_11066 {
	static int K;
	static int[] input;
	static int[][] dp;
	static int[] sum;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int testNum = 1;testNum<=T;testNum++) {
			K = Integer.parseInt(br.readLine());
			input = new int[K+1];
			dp = new int[K+1][K+1];
			sum = new int[K+1];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 1;i<=K;i++) {
				input[i]= Integer.parseInt(st.nextToken());
				sum[i]= sum[i-1] + input[i]; 
			}
			
			for(int n = 1;n<=K;n++) {
				for(int from = 1;from + n<=K;from++) {
					int to = from + n;
					dp[from][to]= Integer.MAX_VALUE;
					for(int divide = from;divide<to;divide++) {
						dp[from][to] = Math.min(dp[from][to], dp[from][divide] + dp[divide+1][to]+ sum[to]- sum[from-1]);
					}
				}
			}
			
			System.out.println(dp[1][K]);
			
			
		}
		
		
				
	}
}