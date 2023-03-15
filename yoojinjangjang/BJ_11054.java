package com.yoojin.boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ_11054 {
	static int N;
	static int[] arr;
	static int[] r_dp;
	static int[] l_dp;
	static int result = Integer.MIN_VALUE;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		r_dp = new int[N];
		l_dp = new int[N];
		arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		leftToRight();
		rightToLeft();
		getMax();
		if(result==0 ) {
			System.out.println(1);
		}else {
			System.out.println(result-1);
			
		}
	}
	
	private static void getMax() {
		for(int i=0;i<N;i++) {
			result = Math.max(result, r_dp[i] + l_dp[i]);
		}
	}

	private static void rightToLeft() {
		for(int i =N-1;i>0;i--) {
			l_dp[i] = Math.max(l_dp[i], 1);
			for(int j = i-1;j>=0;j--) {
				if(arr[i] < arr[j]) {
					l_dp[j] = Math.max(l_dp[i]+1, l_dp[j]);
				}
			}
			l_dp[N-1] = Math.max(l_dp[N-1], 1);

		}
	}

	private static void leftToRight() {
		
		for(int i=0;i<N-1;i++) {
			r_dp[i] = Math.max(r_dp[i], 1);
			for(int j = i+1;j<N;j++) {
				if(arr[i] < arr[j]) {
					r_dp[j] = Math.max(r_dp[i]+1, r_dp[j]);
				}
			}
			r_dp[N-1] = Math.max(r_dp[N-1], 1);
		}
	}
	
	
}
