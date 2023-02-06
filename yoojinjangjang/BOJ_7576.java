package com.yoojin.dfsbfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Node {
	private int x;
	private int y;
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
}


public class BOJ_7576 {
	static int M;
	static int N;
	static int[][] tomatos;
	static boolean[][] visited;
	static Queue<Node> queue = new LinkedList<>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		M = sc.nextInt();
		N = sc.nextInt();
		visited = new boolean[N][M];
		tomatos = new int[N][M];
		for(int i = 0;i<N;i++) {
			for(int j = 0;j<M;j++) {
				tomatos[i][j] = sc.nextInt();
				if(tomatos[i][j] == 1) {
					visited[i][j] = true;
					queue.add(new Node(i,j));
				}
			}
		}
		
		dfs();
		boolean flag = false;
		int MAX = Integer.MIN_VALUE;
		for(int i = 0;i<N;i++) {
			for(int j = 0;j<M;j++) {
				if(tomatos[i][j] == 0 ) {
					System.out.println(-1);
					System.exit(0);
				}
				if (tomatos[i][j] > MAX ) {
					MAX = tomatos[i][j];
				}
			}
		}
		System.out.println(MAX-1);
	}
	
	static void dfs() {
		int[] dx = {-1,1,0,0};
		int[] dy = {0,0,-1,1};
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			int x = cur.getX();
			int y = cur.getY();
			for(int i = 0;i<4;i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				if(nx < 0 || nx >= N || ny <0 || ny >= M) {
					continue;
				}
				if(!visited[nx][ny] && tomatos[nx][ny] == 0) {
					tomatos[nx][ny] = tomatos[x][y] + 1;
					visited[nx][ny] = true;
					queue.add(new Node(nx,ny));
				}
			}
		}
	}
}
