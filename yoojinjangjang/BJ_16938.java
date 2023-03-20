package com.yoojin.boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_16938 {
	static int N,L,R,X;
	static int[] levels;
	static int result = 0;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		levels = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		Arrays.sort(levels);
		subset(0,0,Integer.MIN_VALUE,Integer.MAX_VALUE,0, true);
		
		System.out.println(result);
	}
	
	private static void subset(int cnt, int sum, int max, int min, int count, boolean flag) {
		

		if(sum > R) { // 현재의 합이 R 보다 큰 경우 해당 부분집합은 종료되어야 한다. 
			return;
		}
		
		
		
		if(count >= 2 && flag) { // 2개 이상 골랐다. 
			if(sum >= L && (max-min) >= X) { // 합이 L보다 크고 두 값의 차이가 X보다 크거나 같은 경우 !!
				result++; // 경우의 수 증가 
			}
		}
		
		if(cnt == N) { // 현재 마지막 인덱스까지 다 본 경우 리턴
			return;
		}
		
		
		// 2. 현재 값 포함
		subset(cnt+1, sum+levels[cnt], Math.max(max, levels[cnt]), Math.min(min, levels[cnt]), count+1, true);
		
		//1.  현재값을 포함하지 않음
		subset(cnt+1, sum, max, min, count, false); 
		
		
	}
}
