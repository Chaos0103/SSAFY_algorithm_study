package com.yoojin.db;


import java.util.Scanner;

public class BOJ_11727 {
	static int[] f;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int  N = sc.nextInt();
		f = new int[N+1];
		System.out.println(dp(N));
	}
	public static int dp(int N) {
		if (N==1) {
			return (f[N] = 1);
		}
		if (N==2) {
			return (f[N] = 3);
		}
		
		if (f[N] != 0) {
			return f[N];
		}
		
		return (f[N] = (dp(N-1)%10007 + (dp(N-2)* 2)%10007)%10007 );
	}
}