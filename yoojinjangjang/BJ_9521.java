package com.yoojin.boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class BJ_9521 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] str1 = br.readLine().toCharArray();
		char[] str2 = br.readLine().toCharArray();
		
		int len1 = str1.length;
		int len2 = str2.length;
		
		int dp[][] = new int[len1+1][len2+2];
		for(int i =1;i<=len1;i++) {
			for(int j =1;j<=len2;j++) {
				if(str1[i-1] == str2[j-1]) {
					dp[i][j] = dp[i-1][j-1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		
		System.out.println(dp[len1][len2]);
		
	}
}
