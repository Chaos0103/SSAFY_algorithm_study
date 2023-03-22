package com.yoojin.boj.g4;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_3055 {
	static char[][] maps;
	static int[][] waters;
	static int[][] kaktus;
	static int currentTime = 0;
	static int[][] locs = {{-1,0},{1,0},{0,-1},{0,1}};
	static int R,C;
	static Point start;
	static Point result;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		maps = new char[R][C];
		waters = new int[R][C];
		kaktus = new int[R][C];
		for(int i =0;i<R;i++) {
			String str = br.readLine();
			for(int j =0;j<C;j++) {
				maps[i][j] = str.charAt(j);
				if(maps[i][j] == 'S') {
					start = new Point(i,j);
				}
				if(maps[i][j] == 'D') {
					result = new Point(i,j);
					waters[i][j] = Integer.MAX_VALUE;
				}
			}
		}

		flood();
//		for(int i=0;i<R;i++) {
//			for(int j =0;j<C;j++) {
//				System.out.print(waters[i][j]+" ");
//			}
//			System.out.println();
//		}
//		
		kaktusMove();
//		System.out.println();
//		for(int i=0;i<R;i++) {
//			for(int j =0;j<C;j++) {
//				System.out.print(kaktus[i][j]+" ");
//			}
//			System.out.println();
//		}
		
		if(kaktus[result.x][result.y] != 0) {
			System.out.println(kaktus[result.x][result.y]);
			
		}else {
			System.out.println("KAKTUS");
			
		}
	}
	
	private static void kaktusMove() {
		Queue<Point> queue = new LinkedList<>();
		queue.offer(start);
		
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			int next = kaktus[cur.x][cur.y] + 1;
			for(int[] loc:locs) {
				int nx = cur.x + loc[0];
				int ny = cur.y + loc[1];
				if(nx < 0||nx>=R||ny <0||ny>=C) continue;
				if(kaktus[nx][ny] > 0  || maps[nx][ny] == 'X' || maps[nx][ny] == '*') continue;
				if(waters[nx][ny] != 0 && waters[nx][ny] <= next) continue;
				kaktus[nx][ny] = next;
				queue.offer(new Point(nx,ny));
			}
		}
		
	}

	private static void flood() {
		for(int i =0;i<R;i++) {
			for(int j =0;j<C;j++) {
				if(waters[i][j] == 0 && maps[i][j] == '*') {
					bfsWater(new Point(i,j));
				}
			}
		}
		
	}

	private static void bfsWater(Point point) {
		Queue<Point> queue = new LinkedList<>();
		queue.offer(point);
		
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			
			for(int[] loc:locs) {
				int nx = cur.x +loc[0];
				int ny = cur.y + loc[1];
				if(nx < 0 || nx >= R || ny <0||ny >= C) continue;
				if(waters[nx][ny] > 0 || maps[nx][ny] == 'D' || maps[nx][ny] == 'X' || maps[nx][ny] == '*') continue;
				waters[nx][ny] = waters[cur.x][cur.y]+ 1;
				queue.offer(new Point(nx,ny));
			}
		}
	}
	





}
