package com.yoojin.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_14503 {
	static class Robot{
		int x;
		int y;
		int d;
		public Robot(int x, int y, int d) {
			super();
			this.x = x;
			this.y = y;
			this.d = d;
		}
		@Override
		public String toString() {
			return "Robot [x=" + x + ", y=" + y + ", d=" + d + "]";
		}
		
	}
	static int[][] rooms;
	static int[][] locs = {{-1,0},{0,-1},{1,0},{0,1}};
	static int[] firstLocs = {0,3,2,1};
	static int N,M;
	static Robot current; // 현재 로봇의 위치와 상태정보
	static int count; // 청소하는 칸의 수
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		rooms = new int[N][M];
		
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		current = new Robot(r,c,firstLocs[d]);
		
		for(int i =0;i<N;i++) {
			rooms[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		
		while(true) {
			if(rooms[current.x][current.y] == 0) {
				rooms[current.x][current.y] = 2;
				count++;
			}
			
			// 사방 탐색
			boolean isCanClean = false;
			for(int[] loc:locs) {
				int nx = current.x + loc[0];
				int ny = current.y + loc[1];
				if(nx<0||nx >=N||ny<0||ny>=M) continue;
				
				if(rooms[nx][ny] == 0) {
					isCanClean = true;
					break;
				}
			}
			
			if(isCanClean) {
				// 청소하지 않은 칸이 있는 경우 
				current.d = (current.d+1) % 4;
				int nx = current.x + locs[current.d][0];
				int ny = current.y + locs[current.d][1];
				if(nx<0||nx>=N||ny<0||ny>=M) continue;
				if(rooms[nx][ny] == 0) {
					current.x = nx;
					current.y = ny;
				}
			}
			
			else {
				// 청소하지 않은 칸이 없는 경우
				int nx = current.x + (locs[current.d][0] * -1);  // 후진하기 
				int ny = current.y + (locs[current.d][1] * -1);
				if(nx<0|nx>=N||ny<0||ny>=M) {
					break;
				}
				if(rooms[nx][ny] == 1) break;
				
				current.x = nx;
				current.y = ny;
			}
		}
		
		System.out.println(count);
	}
}