package com.yoojin.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_9663 {
	static int N;
	static int[] output; // 퀸을 놓을 열의 위치를 저장하고 있다. 
	static int count;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N =  Integer.parseInt(br.readLine());
		output = new int[N];
		count = 0;
		doNqueen(0);
		System.out.println(count);
	}
	
	private static void doNqueen(int cnt) {
		if(cnt==N) {
			count++;
			return;
		}
		
		for(int i=0;i<N;i++) {
			output[cnt] = i;
			
			if(check(cnt)) {
				doNqueen(cnt+1);
			}
		}
	}

	private static boolean check(int cnt) {
		
		for(int i = 0;i<cnt;i++) {
			// 현재까지 들어온 퀸의 위치를 확인하면서 현재 위치확인
			
			// 1. 현재 확인할 퀸의 열의 위치가 이미 놓은 퀸들의 열의 위치와 일치하는 경우 return false
			if(output[i] == output[cnt]) return false;
			
			// 2. 대각선인 경우 return false
			//if((cnt-i)==1 && Math.abs(output[cnt] - output[i]) == 1) return false;
			if((cnt-i)==Math.abs(output[cnt] - output[i])) return false;
		}
		
		return true;
		
	}
}
