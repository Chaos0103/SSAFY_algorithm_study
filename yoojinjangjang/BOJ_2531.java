package com.yoojin.twopointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_2531 {
	static int N;
	static int d;
	static int k;
	static int c;
	static int[] sushis;
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		sushis = new int[N];
		for(int i = 0;i<N;i++) {
			sushis[i] = Integer.parseInt(in.readLine());
		}
		
		int s = 0;
		int e = k-1; // s와 e 초기화
		Map<Integer,Integer> sushiCntMap = new HashMap<>();
		sushiCntMap.put(c, 1); // c를 기본적으로 넣어두기
		for(int i = s;i<=e;i++) {
			sushiCntMap.put(sushis[i], sushiCntMap.getOrDefault(sushis[i], 0)+1);
		}
		
		
		int max = 1; // c가 있기 때문에 max 는 1보다 작을 수 없다. 
		while (s  < N) {

			// 최대값 계산하기
			max = Math.max(max, sushiCntMap.size());
			
			// s 위치 값을 map에서 제거
			int sValue = sushiCntMap.get(sushis[s]) -1;
			sushiCntMap.put(sushis[s], sValue);
			if (sValue == 0) {
				// 0인 경우 map에서 아예 삭제
				sushiCntMap.remove(sushis[s]);
			}
			s++;
			e = (e+1) % N; 
			sushiCntMap.put(sushis[e], sushiCntMap.getOrDefault(sushis[e],0 )+1);
			
		}
		
		System.out.println(max);
	}
}
