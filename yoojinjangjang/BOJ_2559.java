package com.yoojin.twopointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2559 {
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		int sum = 0;
		
		st = new StringTokenizer(in.readLine());
		for(int i = 0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if(i<K) {
				sum += arr[i];
			}
		}
		
		
		int s = 0;
		int e = K-1;
		int max = sum;
		while(e<N-1) {
			sum -= arr[s++];
			sum += arr[++e];	

			max = Math.max(sum, max);
			
		}
		System.out.println(max);
	}
}