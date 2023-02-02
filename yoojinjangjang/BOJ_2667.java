package com.yoojin.dfsbfs;


import java.util.Arrays;
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
public class BOJ_2667 {
	
	static boolean[][] visited;
	static int[][] map;
	static int N;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];
		for (int i = 0;i<N;i++) {
			String mapI = sc.next();
			for(int j = 0;j<N;j++) {
				map[i][j] = (mapI.charAt(j)-'0');
			}
		}

		visited = new boolean[N][N];
		int cnt = 0;
		for(int i = 0;i<N;i++) {
			for(int j = 0;j<N;j++) {
				if(!visited[i][j] && map[i][j] != 0) {
					cnt++;
					bfs(i,j,cnt);
				}
			}
		}


		int[] apartCnt = new int[cnt];
		for(int i = 0;i<N;i++) {
			for(int j = 0;j<N;j++) {
				if(map[i][j] != 0) {
					apartCnt[map[i][j]-1]++;
				}
			}
		}
		Arrays.sort(apartCnt);
		System.out.println(cnt);
		for(int c: apartCnt) {
			System.out.println(c);
		}
	}
	private static void bfs(int i, int j, int cnt) {
		visited[i][j] = true;
		map[i][j] = cnt;
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(i,j));
		
		int[] dx = {-1,1,0,0};
		int[] dy = {0,0,-1,1};
		
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			int x = cur.getX();
			int y = cur.getY();
			for(int loc = 0 ; loc < 4;loc++) {
				int nx = x + dx[loc];
				int ny = y + dy[loc];
				if (nx <0 || nx >= N || ny <0 || ny >= N) continue;
				if(!visited[nx][ny] && map[nx][ny] != 0) {
					visited[nx][ny] = true;
					map[nx][ny] = cnt;
					queue.add(new Node(nx,ny));
				}
			}
		}
		
	}
}