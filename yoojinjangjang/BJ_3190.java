package com.yoojin.boj;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_3190 {
	public static int n;
	public static int k;
	public static int[][] maps;
	public static int l;
	public static int[][] rotations;
	public static int seconds;
	public static int[][] locs = {{0,1},{1,0},{0,-1},{-1,0}};
	
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(in.readLine());
		k = Integer.parseInt(in.readLine());
		StringTokenizer st;
		maps = new int[n][n];
		for(int i =0;i<k;i++) {
			st = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			maps[x-1][y-1] = 1;
		}
		
		l = Integer.parseInt(in.readLine());
		rotations = new int[l][2];
		for(int i = 0;i<l;i++) {
			st = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(st.nextToken());
			int c = st.nextToken().equals("D") ? 1:-1;
			rotations[i][0] = x;
			rotations[i][1] = c;
		}
		
		Queue<Integer[]> queue = new LinkedList<>(); // 뱀의 좌푱
		maps[0][0] = 2;
		queue.offer(new Integer[] {0,0}); // 뱀 꼬리 넣기 
		int x = 0;
		int y = 0;
		int d = 0;
		int loc = 0;
		int dx = 0;
		int dy = 1;
		seconds = 0;
		while(true) {
			
			if(d < l && seconds == rotations[d][0]) {
				// 시간초과가 해당 시간에 도달하는 경우
				
				loc = (loc+rotations[d][1]) % 4;
				if(loc==-1) {
					loc = 3;
				}
				dx = locs[loc][0];
				dy = locs[loc][1];
				d++;
			}
			// 범위 밖인 경우
			
			if(x+dx < 0 || x+dx >= n || y +dy < 0 || y+dy >= n) break;
			
			// 2인 경우 몸에서 부딪힘 탈출
			x += dx;
			y += dy;
			
			if(maps[x][y] == 2) break;
			
			if(maps[x][y] == 1) {
				// 사과이면
				maps[x][y] = 2;
				seconds++;
				queue.offer(new Integer[] {x,y});
				continue;
			} 
			
			// 사과가 아니면 
			maps[x][y] = 2;
			seconds++;
			queue.offer(new Integer[] {x,y});
			Integer[] tail = queue.poll();
			maps[tail[0]][tail[1]] = 0;

					
		}
		
		System.out.println(seconds+1);
	}
}