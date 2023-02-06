package com.yoojin.db;

import java.util.Scanner;

public class BOJ_11052 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] cards = new int[N+1];
		
		for(int i = 1;i<N+1;i++) {
			cards[i] = sc.nextInt();
		}
		
		int[] dp = new int[N+1];
		
		for(int i = 1;i<=N;i++) {
			for(int j = 1;j<=i;j++) {
				dp[i] = Math.max(dp[i], dp[i-j] + cards[j]);
			}
		}
		
		System.out.println(dp[N]);
	}
	
}
