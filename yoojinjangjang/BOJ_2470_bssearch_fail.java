package com.yoojin.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2470_bssearch_fail {
	static int N;
	static long[] solutions;
	static long mixture = 2000000001;
	static int[] sol = new int[2];
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		solutions = new long[N];
	
		for(int i = 0;i<N;i++) {
			solutions[i] = Long.parseLong(st.nextToken());
		}
		Arrays.sort(solutions);
		for(int i = 0;i<N;i++) {
			bs(i,0,N-1);
		}
		Arrays.sort(sol);
		System.out.println(solutions[sol[0]]+ " " + solutions[sol[1]]);
		
	}
	
	private static void bs(int idx,int low,int high) {
		int mid;
		if(low <= high) {
			mid = (low+high)/2;
			if (mid == idx) {
				bs(idx,low,mid-1);
				bs(idx,mid+1,high);
				return;
			}
			long beforeMixture = mixture;
			mixture = Math.min(mixture, Math.abs(solutions[mid]+solutions[idx]));

			if(beforeMixture != mixture) {
				sol[0] = idx;
				sol[1] = mid;
			}
			if(mixture == 0) return;
				
				if(solutions[mid] == 0 || solutions[idx] == 0) {
					// 둘 중에 하나가 0 인 경우 
					if (solutions[mid] + solutions[idx] < 0 ) bs(idx,mid+1,high);
					else bs(idx,low,mid-1);
				}else if(solutions[mid] * solutions[idx] < 0 || (solutions[mid] <0 && solutions[idx] < 0)) {
					bs(idx,mid+1,high);
				} else {
					bs(idx,low,mid-1);
				}
				
			
		}
	}
}	

/*
5
-100 -50 20 40 80
*/