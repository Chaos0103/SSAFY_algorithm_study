package com.yoojin.shortest_path;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11403 {
	public static int n;
	public static int[][] graph;
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(in.readLine());
		
		graph = new int[n][n];
		
		for(int i =0;i<n;i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			for(int j = 0;j<n;j++) {
					graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		for(int k = 0;k<n;k++) {
			for(int i = 0;i<n;i++) {
				for(int j =0;j<n;j++) {
					if(graph[i][k] + graph[k][j] >1) {
						graph[i][j] = 1;
					}
				}
			}
		}
		
		
		
		for(int i =0;i<n;i++) {
			for(int j = 0;j<n;j++) {
				System.out.print(graph[i][j]+" ");
			}
			System.out.println();
		}
	}
		
}