package com.yoojin.dfsbfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_4963 {
	static boolean[][] visited;
	static int[][] map;
	static int h;
	static int w;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(true) {
			w = sc.nextInt();
			h = sc.nextInt();
			if (w==0 || h ==0) {
				break;
			}
			
			map = new int[h][w];
			for (int i = 0;i<h;i++) {
				for(int j = 0;j<w;j++) {
					map[i][j] = sc.nextInt();
				}
			}
			visited = new boolean[h][w];
			int cnt = 0;
			for(int i = 0;i<h;i++) {
				for (int j = 0;j<w;j++) {
					if(!visited[i][j] && map[i][j] != 0) {
						bfs(i,j);
						cnt++;
					}
				}
			}
			
			System.out.println(cnt);
			
		}
	}

	private static void bfs(int i, int j) {
		visited[i][j] = true;
		Queue<List<Integer>> queue = new LinkedList<>();
		queue.add(Arrays.asList(i,j));
		// 상하좌우 좌상 우상 좌하 우하 
		int[] dx = {-1,-1,-1,0,0,1,1,1};
		int[] dy = {-1,0,1,-1,1,-1,0,1};
		
		while(!queue.isEmpty()) {
			List<Integer> cur = queue.poll();
			int x = cur.get(0);
			int y = cur.get(1);
			for(int loc = 0;loc<dx.length;loc++) {
				int nx = x+dx[loc];
				int ny = y+dy[loc];
				if(nx<0 || nx >= h || ny<0 || ny >= w) {
					continue;
				}
				if(!visited[nx][ny] && map[nx][ny] ==1) {
					visited[nx][ny] = true;
					queue.add(Arrays.asList(nx,ny));
				}
			}
			
		}
		
		
	}
}