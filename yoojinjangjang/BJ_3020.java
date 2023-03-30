package com.yoojin.boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_3020 {
	static int N;
	static int H;
	static int[] high;
	static int[] low;
	static int min = Integer.MAX_VALUE;
	static int cnt = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		high = new int[H+1];
		low = new int[H+1];
		
		
		for(int i =0;i<N;i++) {
			int num = Integer.parseInt(br.readLine());
			if(i %2 == 1) {
				// 홀수이면 
				// 위의배열에 넣기
				high[H+1-num]++;
				
			} else {
				// 짝수이면 아래의 배열에 넣기
				low[num]++;
				
			}
		}
		
		// 아래의 뚫을 개수 누적합
		for(int i=H-1;i>0;i--) {
			low[i] = low[i] + low[i+1];
		}
		
		// 위의 뚫을 개수 누적합 
		for(int i=2;i<H;i++) {
			high[i+1] = high[i+1] + high[i];
		}

		
		for(int i=1;i<=H;i++) {
			// H까지 돌면서 최솟값을 구하기
			if(min > low[i] + high[i]) {
				// min 값 변경
				min = low[i] + high[i];
				cnt = 0;
			}
			
			if(min == low[i] + high[i]) {
				cnt++;
			}
		}
		
		System.out.println(min+ " " + cnt);
	}
}