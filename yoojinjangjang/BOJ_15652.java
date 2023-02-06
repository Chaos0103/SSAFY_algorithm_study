package com.yoojin.bfsearch;

import java.util.Scanner;

public class BOJ_15652 {
	public static void combination(int[] arr, int[] output,int index, int depth, int r) {
		if (depth==r) {
			for(int i = 0;i<r;i++) {
				System.out.print(output[i]+ " ");
			}
			System.out.println();
			return;
		}
		
		for(int i = index;i<arr.length;i++) {
			output[depth] = arr[i];
			combination(arr,output, i,depth+1, r);
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		int[] arr = new int[N];
		for(int i = 1;i<=N;i++) {
			arr[i-1] = i;
		}
		combination(arr,new int[M],0, 0, M);
		
		
	}
}
