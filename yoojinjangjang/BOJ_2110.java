package com.yoojin.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2110 {
	 public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int[] house = new int[N];
		for(int i = 0;i<N;i++) {
			house[i]  = Integer.parseInt(in.readLine());
		}
		
		Arrays.sort(house);
		int distance = 0;
		
		// 가장 인접한 거리를 이진 탐색으로 구함
		int start = 1;	 // 가장 작은 값 (1)
		int end = house[house.length-1] - house[0]; // 현재 집들 중 가장 큰 인접 거리 (맨마지막 집 - 처음 집)
		int mid;
		int count;
		int current; // 공유기 설치할 현재의 집
		while(start <= end) {
			mid = (start+end) / 2; // 중간값 구하기
			count = 1;
			current = house[0]; // 첫번째 집
			
			for(int i = 1;i<N;i++) {
				// 각 집을 돌면서 
				if((current+mid) <= house[i]) { // 현재집에서 인접거리를 더한 값이 탐색중인 집보다 작은 경우 (해당 집을 포함하지 못함) 
					count++; // 포함한 집의 수를 증가시킴
					current = house[i]; // 현재 집 위치 변경
				}
			}
			
			if (count >= C) {
				// 현재 포함한 집의 수가 실제로 설치할 집의 수보다 크거나 같은 경우 => 인접 거리를 키움
				distance = mid; // 현재 인접 거리 저장
				start = mid + 1;
			} else {
				// 아닌경우 인접거리를 줄임
				end = mid -1;
			}
		}
		
		System.out.println(distance);
		
		
	}
}
