package com.yoojin.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2138 {
	static int N;
	static int[] arrA1;
	static int[] arrA2;
	static int[] arrB;
	
	static int[] dx = {-1,0,1};
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		arrA1 = new int[N];
		arrA2 = new int[N];
		arrB = new int[N];
		
		String str = in.readLine();
		for(int i = 0;i<N;i++) {
			arrA1[i] = str.charAt(i) -'0';
			arrA2[i] = arrA1[i];
		}
		str = in.readLine();
		for(int i = 0;i<N;i++) {
			arrB[i] = str.charAt(i) -'0';
		}
		
		int MIN = Integer.MAX_VALUE;
		
		int cnt1 = 0;
		int cnt2 = 0;
		
		arrA1 = change(arrA1,0);
		cnt1++; // 0번쨰 변경
		
		for(int i =1;i<N;i++) {
			if(arrA1[i-1] != arrB[i-1]) {
				arrA1 = change(arrA1,i);
				cnt1++;
			}
		}
		
		if(arrA1[N-1] == arrB[N-1]) {
			MIN = Math.min(MIN, cnt1);
		}
		
		for(int i =1;i<N;i++) {
			if(arrA2[i-1] != arrB[i-1]) {
				arrA2 = change(arrA2,i);
				cnt2++;
			}
		}
		
		if(arrA2[N-1] == arrB[N-1]) {
			MIN = Math.min(MIN, cnt2);
		}
		
		
		if(MIN == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(MIN);
		}
		
		
	
	
		
	
	}
	
	public static int[] change(int[] data, int i) {
		for(int j =0;j<3;j++) {
			int nx = i + dx[j];
			if(nx <0 || nx > N-1) {
				continue;
			}
			
			data[nx] = data[nx]==0?1:0;
		}
		
		return data;
		
		
	}
	

}
