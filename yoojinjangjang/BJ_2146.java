package com.yoojin.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.awt.Point;

public class BJ_2146 {
	static int[][] maps;
	static boolean[][] visited;
	static int N;
	static int currentIsland;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		maps = new int[N][N];
		visited = new boolean[N][N];
		for(int i =0;i<N;i++) {
			maps[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		
		currentIsland = 1;
		for(int i =0;i<N;i++) {
			for(int j =0;j<N;j++) {
				if(maps[i][j] != 0 && !visited[i][j]) {
					bfs(new Point(i,j));
					currentIsland++;
				}
			}
		}
	
		visited = new boolean[N][N];
		for(int i =0;i<N;i++) {
			for(int j =0;j<N;j++) {
				if(maps[i][j] != 0) {
					visited = new boolean[N][N];
					bfsToGetDist(new Node(i,j,0));
				}
			}
		}
		
		

		System.out.println(ans);
	}
	static int ans = Integer.MAX_VALUE;
	
	
	static void bfsToGetDist(Node start) {
		int currentIsland = maps[start.x][start.y];
		
		visited[start.x][start.y] = true; 
		Queue<Node> queue = new LinkedList<>();
		queue.offer(start);
		while(!queue.isEmpty()) {
			Node current = queue.poll();
			for(int d =0;d<4;d++) {
				int nx = current.x + dx[d];
				int ny = current.y + dy[d];
				if(nx <0||nx>=N||ny<0||ny>=N) continue;
				if(visited[nx][ny]) continue;
				visited[nx][ny] = true;
				// 0이거나 현재 섬이 아닌 수
				// 다른 섬이면 거리 계산
				if(maps[nx][ny] != currentIsland && maps[nx][ny] != 0) {
					ans = Math.min(ans, current.dist);
					continue;
				}
				// 0이면 
				
				queue.offer(new Node(nx,ny,current.dist+1));
				
				
			}
		}
	}
	
	
	
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,-1,0,1};
	static void bfs(Point start) {
		maps[start.x][start.y]= currentIsland; 
		visited[start.x][start.y] =true;
		Queue<Point> queue = new LinkedList<>();
		queue.offer(start);
		while(!queue.isEmpty()) {
			Point current = queue.poll();
			for(int d = 0;d<4;d++) {
				int nx = current.x + dx[d];
				int ny = current.y + dy[d];
				if(nx<0||nx>=N||ny<0||ny>=N) continue;
				if(visited[nx][ny]) continue;
				if(maps[nx][ny] !=1) continue;
				
				maps[nx][ny] = currentIsland;
				queue.offer(new Point(nx,ny));
				visited[nx][ny] = true;
			}
		}
	}
	
	
	
	
	static class Node {
		int x;
		int y;
		int dist;
		public Node(int x, int y, int dist) {
			super();
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + ", dist=" + dist + "]";
		}
		
	}
	
	
	
}
