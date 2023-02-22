package com.yoojin.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class SWEA_5658 {
	static char[] passwords;
	static int N,K;
	static Set<Integer> set;
	static Map<Character,Integer> map = new HashMap<Character,Integer>() {{
		put('A',10);
		put('B',11);
		put('C',12);
		put('D',13);
		put('E',14);
		put('F',15);
	}};
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		for(int testNum = 1;testNum<=T;testNum++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			set = new TreeSet<>(); // 정렬된 중복 제거 
			
			passwords = new char[N];
			String pwd = in.readLine();
			for(int i =0;i<N;i++) {
				passwords[i] = pwd.charAt(i);
			}
			
			for(int i =1;i<=N/4;i++) {
				int startIdx = N - i;
				for(int j = 0;j<4;j++) {
					// 함수 호출 (10진수 변환함수)
					to10(startIdx);
					startIdx = (startIdx + N/4) % N;
				}
			}
			
			System.out.print("#"+ testNum+" ");
			System.out.println(set.toArray()[set.size()-K]);
		}
	}
	
	static void to10(int start) {
		int sum = 0;
		int idx = start;
		int d = 0;
		for(int i = (N/4)-1;i>=0;i--) {
			char charD = passwords[idx];
			if("ABCDEF".contains(charD+"")) {
				// 해당 문자 포함시
				d = map.get(charD);
			}else {
				d = passwords[idx] - '0';
			}
			int digit = (int) Math.pow(16, i);
			
			sum += (d*digit);
			idx = (idx+1)%N;
		}
		
		set.add(sum);
	}
}
