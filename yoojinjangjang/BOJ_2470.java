package com.yoojin.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2470 {
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
		int i = 0;
		int j = N -1;
		long tmp;
		while(i<j) {
			tmp = Math.abs(solutions[i] + solutions[j]);
			if( tmp < mixture) {
				mixture = tmp;
				sol[0] = i;
				sol[1] = j;
				
			}
			
			if (solutions[i] + solutions[j] > 0) j--;
			else i++;
			
			
		}
		Arrays.sort(sol);
		System.out.println(solutions[sol[0]]+ " " + solutions[sol[1]]);
		
	}
	

}	

/*
5
-100 -50 20 40 80
*/