package com.yoojin.bfsearch;

import java.util.Scanner;

public class BOJ_10819 {
	static int MAX = Integer.MIN_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N];
		for (int i = 0;i<N;i++) {
			arr[i] = sc.nextInt();
		}
		
		permutation(arr, new int[N], new boolean[N], 0, N);
		
		System.out.println(MAX);
	}
	
	static void permutation(int[] arr, int[] output, boolean[] visited, int depth, int n) {
		if (depth == n) {

			calMax(output,n);
			return;
		}
		
		for(int i = 0;i<n;i++) {
			if(!visited[i]) {
				visited[i] = true;
				output[depth] = arr[i];
				permutation(arr, output, visited, depth+1, n);
				visited[i] = false;
			}
		}
	}
	
	static void calMax(int[] output,int n) {
		int sum = 0;
		for(int i = 0;i<n-1;i++) {
			sum += Math.abs(output[i]-output[i+1]);
		}
		MAX = Math.max(MAX, sum);
	}
}
