package com.yoojin.boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BJ_2565 {
	static int N;
	static int[][] wire;
	static int[] dp;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		wire = new int[N][2];
		
		StringTokenizer st;
		for(int i =0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			wire[i][0] = Integer.parseInt(st.nextToken());
			wire[i][1] = Integer.parseInt(st.nextToken());
		}
		
		dp = new int[N];
		
		Arrays.fill(dp, 1);
		
		Arrays.sort(wire,new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[0], o2[0]);
			}

		});
		
		
		for(int i=0;i<N-1;i++) {
			for(int j =i+1;j<N;j++) {
				if(wire[i][1] <= wire[j][1]) {
					dp[j] = Math.max(dp[j], dp[i]+1);
				}
			}
		}
		
		int max = Integer.MIN_VALUE;
		for(int i =0;i<N;i++) {
			max = Math.max(dp[i], max);
		}
		
		System.out.println(N-max);
	}
}
