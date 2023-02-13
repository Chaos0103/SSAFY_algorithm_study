package com.yoojin.bfsearch;

import java.util.Scanner;

public class BOJ_10974 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N];
		for(int i = 1;i<=N;i++) {
			arr[i-1] = i;
		}
		int[] output = new int[N];
		boolean[] visited = new boolean[N];
		perm(arr,output,visited,0,N,N);
	}
	
	static void perm(int[] arr, int[] output, boolean[] visited, int depth, int n, int r) {
		if (depth == r) {
			print(output, r);
			return;
		}
		
		for(int i = 0;i<n;i++) {
			if (visited[i]!=true) {
				visited[i] = true;
				output[depth] = arr[i];
				perm(arr,output,visited,depth+1,n,r);
				visited[i] = false;
			}
		}
	}
	
	static void print(int[] output, int r) {
		for(int i = 0;i<r;i++) {
			System.out.print(output[i] + " ");
		}
		System.out.println();
	}
}
