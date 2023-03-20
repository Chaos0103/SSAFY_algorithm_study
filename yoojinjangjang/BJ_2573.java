package com.yoojin.boj.g4;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2573 {
	static int N,M;
	static boolean[][] visited;
	static int[][] maps;
	static int[][] temp;
	static int repeatCnt;
	static int count;
	static int[][] locs = {{-1,0},{1,0},{0,-1},{0,1}};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		maps = new int[N][M];
		temp = new int[N][M];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j =0;j<M;j++) {
				int a = Integer.parseInt(st.nextToken());
				maps[i][j] = a;
				temp[i][j] = a;
			}
		}
		repeatCnt = 0;
		while(true) {
			repeatCnt++;
			
			removeIce();
			
			duplicateTemp();
			
			visited = new boolean[N][M];
			count = 0;
			getCrowd();
			
			if(count>=2) {
				System.out.println(repeatCnt);
				break;
			}
			
			if(count==0) {
				System.out.println(0);
				break;
			}
		}
	}

	private static void getCrowd() {
		for(int i=0;i<N;i++) {
			for(int j =0;j<M;j++) {
				if(maps[i][j] != 0 && !visited[i][j]) {
					dfs(new Point(i,j));
					count++;
				}
			}
		}
	}

	private static void dfs(Point point) {
		visited[point.x][point.y]= true;
		Queue<Point> queue = new LinkedList<>();
		queue.offer(point);
		
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			for(int[] loc:locs) {
				int nx = cur.x + loc[0];
				int ny = cur.y + loc[1];
				if(nx<0||nx>=N||ny<0||ny>=M) continue;
				if(visited[nx][ny]) continue;
				if(maps[nx][ny] ==0) continue;
				visited[nx][ny] = true;
				queue.offer(new Point(nx,ny));
			}
		}
		
	}

	private static void duplicateTemp() {
		for(int i=0;i<N;i++) {
			maps[i] = Arrays.copyOf(temp[i], M);
		}
	}

	private static void removeIce() {
		for(int i=0;i<N;i++) {
			for(int j =0;j<M;j++) {
				if(maps[i][j] == 0) continue;
				for(int[] loc:locs) {
					int nx = i+loc[0];
					int ny = j+loc[1];
					if(nx < 0 || nx >= N||ny <0 || ny>=M) continue;
					if(maps[nx][ny] == 0) {
						temp[i][j]--;
						if(temp[i][j] == 0) break;
					}
				}
			}
		}
	}
}
