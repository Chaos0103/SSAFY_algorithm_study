package com.yoojin.boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_1107 {
	static String brokens = "";
	static int M;
	static int N;
	static int cnt = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		if(M!=0) {
			brokens = br.readLine().replace(" ", "").trim();
		}
		
		
		cnt = Math.min(cnt,Math.abs(100-N));
		for(int channel = 0;channel <= 999_999;channel++) {
			boolean flag = true; // 해당 번호로 바로 갈 수 있다. (고장난 번호가 없다.)
			
			for(int i=0; i < brokens.length();i++) {
				if(String.valueOf(channel).contains(brokens.charAt(i)+"")) {
					// 해당 글자 하나라도 포함시 
					flag = false;
					break;
				}
			}
			
			if(flag) {
				// 고장난 번호가 없는 경우
				int diff = Math.abs(N - channel);
				cnt = Math.min(cnt,  diff+String.valueOf(channel).length());
			}
			
		}
		
		System.out.println(cnt);
	}
}
