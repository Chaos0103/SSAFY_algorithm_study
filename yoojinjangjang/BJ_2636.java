package com.yoojin.boj.g5;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2636 {
	static int N,M;
	static int[][] maps;
	static boolean[][] visited;
	static int time = 0;
	static int cheeseCnt;
	static int[][] locs = {{-1,0},{1,0},{0,-1},{0,1}};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		maps = new int[N][M];
		
		for(int i =0;i<N;i++) {
			maps[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		
		cheese();
		
		System.out.println(time);
		System.out.println(cheeseCnt);
		
	}

	private static void cheese() {
		while(true) {
			visited = new boolean[N][M];
			time++;
			cheeseCnt = 0;
			bfs(new Point(0,0));
			
			boolean flag = true;
			for(int i=0;i<N;i++) {
				for(int j =0;j<M;j++) {
					if(maps[i][j] == 1) {
						flag = false;
						break;
					}
				}
				if(!flag) break;
			}
			
			if(flag) break; // 모두 0이다. 
		}
	}

	private static void bfs(Point start) {
		visited[start.x][start.y]= true;
		Queue<Point> queue = new LinkedList<>();
		queue.offer(start);
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			for(int[] loc: locs) {
				int nx = cur.x + loc[0];
				int ny = cur.y + loc[1];
				if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
				if(visited[nx][ny]) continue;
				visited[nx][ny] = true;
				if(maps[nx][ny] == 0) {
					queue.offer(new Point(nx,ny));
				} else {
					maps[nx][ny] = 0;
					cheeseCnt++;
				}
			}
		}
		
	}
}
