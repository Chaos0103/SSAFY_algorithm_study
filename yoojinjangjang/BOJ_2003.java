package com.yoojin.twopointer;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2003 {
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] arrays = new int[N];
		st = new StringTokenizer(in.readLine());
		for(int i = 0;i<N;i++) {
			arrays[i] = Integer.parseInt(st.nextToken());
		}
		
		int s = 0;
		int e = 0;
		int sum = 0;
		int count = 0;
		while(s<=e) {
			if ( sum >= M) {
				// sum 이 크므로 s를 증가시켜야 한다. (부분집합 줄이기)
				sum -= arrays[s];
				s++;
			} else if (e >= N){ // e가 마지막 배열 요소를 넘은 경우 브레이크 -> 아래 검사 안하고 s를 계속 증가시킴 
				break;
			}else {
				// sum이 작으므로 e를 증가시켜야 한다. (부분집합 늘리기)
				sum += arrays[e];
				e++;
			}
			
			if (sum == M) {
				count++;
			}
		}
		
		System.out.println(count);
	}
}