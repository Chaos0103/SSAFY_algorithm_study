package com.yoojin.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1080 {
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] arrA = new int[N][M];
		int[][] arrB = new int[N][M];
		
		for(int i = 0;i<N;i++) {
			String str = in.readLine();
			for(int j = 0; j<M;j++) {
				arrA[i][j] = str.charAt(j) - '0';
				System.out.print(arrA[i][j] + " ");
			}
			System.out.println();
		}
		
		for(int i = 0;i<N;i++) {
			String str = in.readLine();
			for(int j = 0; j<M;j++) {
				arrB[i][j] = str.charAt(j) - '0';
				System.out.print(arrB[i][j] + " ");
			}
			System.out.println();

		}
		
		
		
		int cnt = 0;
		for(int i = 0;i<=N-3;i++) {
			for(int j =0;j<=M-3;j++) {
				if(arrA[i][j] != arrB[i][j] && N >= 3 && M>=3) {
					cnt++;
					for(int x = 0;x<3;x++) {
						for(int y = 0;y<3;y++) {
							arrA[i+x][j+y] = arrA[i+x][j+y] == 0?1:0; 
						}
					}
				}
			}
		}
		
		boolean flag = true;
		for(int i = 0;i<N;i++) {
			for(int j = 0;j<M;j++) {
				if(arrA[i][j] != arrB[i][j]) {
					flag = false;
					break;
				}
			}
			if (!flag) break;
		}
		
		if(!flag) {
			System.out.println(-1);
		} else {
			System.out.println(cnt);
		}
	}
}
