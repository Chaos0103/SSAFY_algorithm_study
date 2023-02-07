package com.yoojin.recursive;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_10815 {
	static int[] arr; 
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		arr = new int[N];
		for(int i = 0;i<N;i++) {
			arr[i] = sc.nextInt();
		}
		
		Arrays.sort(arr);
		
		int M = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		for(int i = 0;i<M;i++) {
			sb.append(binarySearch(sc.nextInt(), 0, arr.length-1));
			sb.append(" ");
		}
		
		System.out.println(sb.toString());
		
	}
	
	static int binarySearch(int key,int low, int high) {
		int mid;
		
		while(low<=high) {
			mid = (low+high)/2;
			if (key < arr[mid]) high = mid -1;
			else if (key > arr[mid]) low = mid +1;
			else return 1;
		}
		
		return 0;
		
	}
	
	
}