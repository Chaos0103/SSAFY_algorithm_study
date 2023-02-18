package com.yoojin.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point{
	int x;
	int y;
	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
}


public class BJ_16234 {
	public static int n,L,R;
	public static int[][] arr;
	public static ArrayList<Point> aligns = new ArrayList<>();
	public static boolean[][] visited;
	public static boolean flag;
	public static int days = 0;
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		n = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		arr = new int[n][n];
		for(int i =0;i<n;i++) {
			arr[i] = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		
		while(true) {
			flag = false; //값이 변경된게 하나도 없는 경우
			visited = new boolean[n][n];
			for(int i=0;i<n;i++) {
				for(int j =0;j<n;j++) {
					if (visited[i][j]) continue;
					bfs(i,j);
				}
			}
			if (!flag) break;
			days++;
		}
		
		System.out.println(days);
		
		
	}
	
	private static int[][] locs = {{1,0},{-1,0},{0,1},{0,-1}};
	private static void bfs(int i, int j) {
		aligns = new ArrayList<>();
		aligns.add(new Point(i,j)); // 연합국 리스트
		int cnt = 1; // 연합국 갯수
		int sum = arr[i][j]; // 연학국의 인구 수 총합
		visited[i][j] = true;
		Queue<Point> queue = new LinkedList<>();
		queue.offer(new Point(i,j));
		
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			int x = cur.x;
			int y = cur.y;
			for(int[] loc:locs) {
				int nx = x+loc[0];
				int ny = y+loc[1];
				if(nx <0 || nx >= n|| ny<0||ny >=n) continue;
				if(visited[nx][ny]) continue;
				int diff = Math.abs(arr[x][y] -  arr[nx][ny]);
				if(diff >= L && diff <= R) {
					visited[nx][ny] = true;
					queue.offer(new Point(nx,ny));
					sum += arr[nx][ny];
					cnt++;
					aligns.add(new Point(nx,ny));
					flag = true;
				}
			}
		}
		
		for(int k =0;k<aligns.size();k++) {
			int x = aligns.get(k).x;
			int y = aligns.get(k).y;
			arr[x][y] = sum/cnt;
		}
		
		
	}
}
