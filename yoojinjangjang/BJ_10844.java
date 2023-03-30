package com.yoojin.boj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_10844 {
	static int[][] dp;
	static int N;
	static int[][] locs = {{-1,-1},{-1,1}};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[N+1][10];
		dp[1][0] = 0;
		for(int i =1;i<10;i++) {
			dp[1][i] = 1;
		}
		
		for(int i=1;i<=N;i++) {
			// 각 자릿수를 보면서 왼쪽위와 오른쪽위의 값을 가지고 계산하기
			for(int j = 0;j<10;j++) {
				for(int[] loc: locs) {
					int nx = i + loc[0];
					int ny = j + loc[1];
					if(ny < 0 || ny >= 10) continue;
					dp[i][j] = (dp[i][j] + dp[nx][ny]) % 1_000_000_000;
				}
			}
		}
		
		int result = 0;
		for(int i = 0;i<10;i++) {
			result = (result + dp[N][i]) % 1_000_000_000;
		}
		
		System.out.println(result);
		
	}
}