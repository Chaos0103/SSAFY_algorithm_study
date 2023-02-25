package com.yoojin.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_14500 {
	static class Point {
		int x;
		int y;
		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + "]";
		}
		
	}
	static int N,M;
	static int[][] maps;
	static boolean[][] visited;
	static int ans = Integer.MIN_VALUE;
	static int[][] locs = {{-1,0},{1,0},{0,-1},{0,1}};
	
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		maps = new int[N][M];
		visited = new boolean[N][M];
		
		for (int y = 0; y < N; y++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int x = 0; x < M; x++) {
				maps[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < M; x++) {
				visited[y][x] = true;
				dfs(y, x, 1, maps[y][x]);
				visited[y][x] = false;
				
				combi(0, 0, y, x, maps[y][x]);	//인접한 4칸 중 3칸 고르기(ㅗ,ㅓ,ㅏ,ㅜ 모양)
			}
		}
		
		System.out.println(ans);
	}
	
	static void combi(int cnt, int start, int x, int y, int sum) {
		if(cnt==3) {
			ans = Math.max(ans, sum);
			return;
		}
		
		for(int i = start;i<4;i++) {
			int nx = x + locs[i][0];
			int ny = y + locs[i][1];
			
			if(nx<0||nx>=N||ny<0||ny>=M) continue;
			
			combi(cnt+1, i+1, x, y, sum+maps[nx][ny]);
			
		}
	}
	
	static void dfs(int y, int x, int cnt, int sum) {
		if(cnt==4) {
			ans = Math.max(ans, sum);
			return;
		}
		
		for(int[] loc:locs) {
			int nx = y + loc[0];
			int ny = x + loc[1];
			
			if(nx<0||nx>=N||ny<0||ny>=M) continue;
			if(visited[nx][ny]) continue;
			
			visited[nx][ny] = true;
			dfs(nx, ny, cnt+1, sum+maps[nx][ny]);
			visited[nx][ny] = false;
			
		}
	}
}