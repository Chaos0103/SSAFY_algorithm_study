package com.yoojin.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BJ_2696 {
	static int N;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 1;t<=T;t++) {
			N = Integer.parseInt(br.readLine());
			ArrayList<Integer> arr = new ArrayList<>();
			ArrayList<Integer> result = new ArrayList<>();
			StringTokenizer st;
			for(int i =0;i<N/10;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j =0;j<10;j++) {
					arr.add(Integer.parseInt(st.nextToken()));
					if((j+1)%2==1) {
						// 홀수인경우 
						Collections.sort(arr);
						result.add(arr.get(arr.size()/2));
					}
				}
			}
			if(N%10 != 0) {
				st = new StringTokenizer(br.readLine());
				for(int i =0;i<N%10;i++) {
					arr.add(Integer.parseInt(st.nextToken()));
					if((i+1)%2==1) {
						Collections.sort(arr);
						result.add(arr.get(arr.size()/2));
					}
				}	
			}
			
			
			System.out.println(result.size());
			for(int i =0;i<result.size()/10;i++) {
				for(int j =0;j<10;j++) {
					System.out.print(result.get(10*i+j)+" ");
				}
				System.out.println();
			}
			
			if(arr.size()%10 != 0) {
				for(int i=0;i<result.size()%10;i++) {
					System.out.print(result.get((10*(result.size()/10))+i)+" ");
				}
			}
			System.out.println();
		}
	}
}
