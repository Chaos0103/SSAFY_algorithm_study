package com.yoojin.boj;


import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import javax.swing.text.Position;

public class BJ_1520 {
	static int[][] maps;
	static int[][] dp;
	static int M,N;
	static int count;
	static int[][] locs = {{0,1},{1,0},{-1,0},{0,-1}};
	public static void main(String[] args) throws IOException{
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		maps = new int[M][N];
		dp = new int[M][N];
		for(int i =0;i<M;i++) {
			maps[i]= Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray(); 
		}
		
		for(int i=0;i<M;i++) {
			Arrays.fill(dp[i], -1);
		}
		dp[M-1][N-1] = 1;
		dfs(new Point(0,0));
		
		
		System.out.println(dp[0][0]);
	}
	
	private static int dfs(Point point) {
		if(dp[point.x][point.y]!= -1) {
			return dp[point.x][point.y];
		}
		
		dp[point.x][point.y] = 0;
		for(int[] loc:locs) {
			int nx = point.x + loc[0];
			int ny = point.y + loc[1];
			if(nx < 0 || nx >= M || ny < 0 || ny >= N) continue;
			
			if(maps[nx][ny] == -1) continue;
			if(maps[nx][ny] >= maps[point.x][point.y]) continue;
			dp[point.x][point.y] += dfs(new Point(nx,ny));
		}
		
		return dp[point.x][point.y];
	}

	

}