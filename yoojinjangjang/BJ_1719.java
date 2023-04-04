package com.yoojin.boj.g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class BJ_1719 {
	static int[][] weights;
	static int[][] path;
	static int n;
	static int m;
	static final int INF = 200* 10000;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		weights = new int[n][n];
		path = new int[n][n];
		
		for(int i=0;i<n;i++) { // 초기화
			for(int j= 0;j<n;j++) {
				weights[i][j] = INF;
				path[i][j] = j+1;
			}
		}
		
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int to = Integer.parseInt(st.nextToken())-1;
			int from = Integer.parseInt(st.nextToken())-1;
			int weight = Integer.parseInt(st.nextToken());
			weights[to][from] =  weights[from][to] = Math.min(weights[from][to], weight);
		}
		
		

		for(int k = 0;k<n;k++) {
			for(int i =0;i<n;i++) {
				for(int j = 0;j<n;j++) {
					if(weights[i][j] > weights[i][k] + weights[k][j]) {
						weights[i][j] = weights[i][k] + weights[k][j];
						path[i][j] = path[i][k];
					}
				}
			}
		}
		
		
		for(int i=0;i<n;i++) {
			for(int j =0;j<n;j++) {
				if(i==j) {
					System.out.print("- ");
				} else {
					System.out.print(path[i][j]+ " ");
				}
			}
			System.out.println();
		}

		
	}
}	
