package com.yoojin.boj.g5;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2589 {
	static int N,M;
	static char[][] map;
	static boolean[][] visited;
	static int[][] locs = {{-1,0},{1,0},{0,-1},{0,1}};
	static int result = Integer.MIN_VALUE;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		for(int i =0;i<N;i++) {
			String str = br.readLine();
			for(int j =0;j<M;j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		
		for(int i=0;i<N;i++) {
			for(int j =0;j<M;j++) {
				if(map[i][j] == 'L') {
					visited = new boolean[N][M];
					bfs(new Point(i,j,0));
				}
			}
		}
		
		System.out.println(result);
	
	}
	
	private static void bfs(Point point) {
		Queue<Point> queue = new LinkedList<>();
		queue.add(point);
		visited[point.x][point.y] = true;
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			result = Math.max(result,cur.dist);
			for(int[] loc:locs) {
				int nx = cur.x +loc[0];
				int ny = cur.y + loc[1];
				if(nx < 0 || nx >= N || ny < 0 || ny >=M) continue;
				if(visited[nx][ny]) continue;
				if (map[nx][ny] == 'W') continue;
				visited[nx][ny] = true;
				queue.add(new Point(nx,ny,cur.dist+1));
			}
		}
	}

	static class Point {
		int x,y;
		int dist;
		public Point(int x, int y, int dist) {
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