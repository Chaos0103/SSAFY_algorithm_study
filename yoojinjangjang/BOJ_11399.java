package com.yoojin.greedy;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11399 {
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		int[] minutes = new int[N];
		StringTokenizer st = new StringTokenizer(in.readLine());
		for(int i =0;i<N;i++) {
			minutes[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(minutes);
		int sum = minutes[0];
		
		for(int i = 1;i<N;i++) {
			minutes[i] += minutes[i-1];
			sum += minutes[i];
		}
		
		System.out.println(sum);
		
	}
}