package com.yoojin.twopointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_3273 {
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		int[] arrs = new int[N];
		StringTokenizer st = new StringTokenizer(in.readLine());
		for(int i = 0;i<N;i++) {
			arrs[i] = Integer.parseInt(st.nextToken());
		}
		int X = Integer.parseInt(in.readLine());
		
		
		Arrays.sort(arrs); // 정렬 수행
		
		int s = 0;
		int e = N-1;
		int sum = arrs[s] + arrs[e];
		int count = 0;
		while(s < e) {
			if (sum==X) count++; 
			// s가 e보다 작은 경우만 덧셈을 구함
			if (sum >= X) {
				--e;
			} else {
				++s;
			}
			sum = arrs[e] + arrs[s];
			
		}
		
		System.out.println(count);
		
		
		
	}
}