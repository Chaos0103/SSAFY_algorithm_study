package com.yoojin.boj.g2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BJ_12015 {
	static int N;
	static int[] arr;
	static int[] dp;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		List<Integer> list = new ArrayList<>();
		
		list.add(0);
		
		for(int i =0;i<N;i++) {
			if(list.get(list.size()-1) <= arr[i]) {
				list.add(arr[i]);
			} else {
				int left = 0;
				int right = list.size()-1;
				while(left<right) {
					int mid = (left+right) >> 1;
					if(list.get(mid) <= arr[i]) {
						right = mid;
					} else {
						left = mid + 1;
					}
				}
				
				list.set(right, arr[i]);
			}
		}
		
		System.out.println(list.size()-1);
	}
}
