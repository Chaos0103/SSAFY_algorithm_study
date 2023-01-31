package com.yoojin.bfsearch;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_1759 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int L = sc.nextInt();
		int C = sc.nextInt();
		char[] alpha = new char[C];
		for(int i = 0;i<C;i++ ) {
			alpha[i] = sc.next().charAt(0);
		}
		Arrays.sort(alpha);
		combination(alpha, new boolean[C], 0, 0, C, L);
		
	}
	static void combination(char[] alpha, boolean[] visited, int start, int depth, int n, int r) {
		if (depth==r) {
			char[] password = new char[r];
			int cnt = 0;
			for(int i = 0;i<n;i++) {
				if (visited[i]) {
					password[cnt++] = alpha[i];
				}
			}
			print(password);
			
			return;
		}
		
		for(int i = start; i<n;i++) {
			if (!visited[i]) {
				visited[i] = true;
				combination(alpha, visited, i+1, depth+1, n, r);
				visited[i] = false;
			}
		}
	}
	static void print(char[] password) {
		int cnt = 0;
		int jCnt = 0;
		for(int i = 0;i<password.length;i++) {
			if(password[i] == 'a' || password[i] == 'e' || password[i] == 'i' || password[i] == 'o'|| password[i] == 'u' ) {
				cnt++;
				continue;
			}
			jCnt++;
			
		}
		if(cnt==0 || jCnt < 2) {
			return;
		}
		for(int i = 0;i<password.length;i++) {
			System.out.print(password[i]);
		}
		System.out.println();
	}
}
