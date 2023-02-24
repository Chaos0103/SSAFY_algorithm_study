package com.yoojin.swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_벽돌깨기 {
	static class Point{
		int x;
		int y;
		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + "]";
		}
		
	}
	
	
	static int N,W,H; // 폭탄수, 열, 행
	static int[][] bricks; // 벽돌정보
	static int[] output; // 중복순열을 담을 output 배열
	static int[][] tempBricks; // 벽돌정보의 임시 배열 (복사본)
	static int MIN = Integer.MAX_VALUE; // 답
	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("swea_벽돌.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		for(int testNum = 1;testNum<=T;testNum++) {
			MIN = Integer.MAX_VALUE;
			StringTokenizer st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			bricks = new int[H][W];
			output = new int[N];
			for(int i =0;i<H;i++) {
				bricks[i] = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
			permutation(0);
			
			System.out.printf("#%d %d%n",testNum,MIN);
			
		}
	}
	
	static void permutation(int depth) {
		if(depth == N) {
			// 수행 전 임시배열 복사
			tempBricks = new int[H][W];
			for(int i =0;i<H;i++) {
				tempBricks[i] = Arrays.copyOf(bricks[i], W);
			}
			// bfs 수행 
			bfs();
			// 수행 후 벽돌 개수 구하기
			int cnt = 0;
			for(int i =0;i<H;i++) {
				for(int j = 0;j<W;j++) {
					if(tempBricks[i][j] != 0) {
						cnt++;
					}
				}
			}
			// 수행 후 최솟값 갱신
			MIN = Math.min(MIN, cnt);
			return;
		}
		
		for(int i =0;i<W;i++) {
			output[depth] = i;
			permutation(depth+1);
		}
	}
	static int[][] locs = {{1,0},{-1,0},{0,1},{0,-1}};
	static void bfs() {
		for(int n = 0;n<N;n++) { // 중복순열로 구한 총알을 쏠 위치를 하나씩 선택해서
			// 총알을 쏠 곳의 가장 위 벽돌 찾기
			Point start = null;
			for(int i = 0;i<H;i++) {
				if(tempBricks[i][output[n]] != 0) {
					start = new Point(i,output[n]);
					break;
				}
			}
			
			if(start == null) continue; // 벽돌 없음
			
			Queue<Point> queue = new ArrayDeque<>();
			queue.add(start);
			while(!queue.isEmpty()) {
				Point current = queue.poll();
				int num = tempBricks[current.x][current.y]; // 현재 폭탄 터트린위치의 num을 저장 (값)
				if(num==0) continue;
				tempBricks[current.x][current.y]= 0; // 벽돌 없애기 
				for(int k = 1;k<num;k++) {
					for(int[] loc : locs) {
						int nx = current.x + loc[0]*k;  
						int ny = current.y + loc[1]*k;
						if(nx < 0 || nx>=H||ny<0||ny>=W) continue; // 범위검사
						if(tempBricks[nx][ny] == 0) continue;
						queue.offer(new Point(nx,ny));
					}
					
				}
			} // while 종료
			
			// 해당 위치의 폭탄을 모두 터트렸다. 
			// 배열을 갱신해준다.		=> 솔루션 봄	
			for(int i = 0;i<W;i++) { // 열을 하나씩 돌면서
				Queue<Integer> bricks = new LinkedList<>();
				int x  = H-1;
				while(x>=0) {
					if(tempBricks[x][i] > 0) {
						bricks.offer(tempBricks[x][i]);
						tempBricks[x][i] =0;
					}
					x--;
				}
				
				x = H-1;
				while(!bricks.isEmpty()) {
					tempBricks[x][i] = bricks.poll();
					x--;
				}
				
				
			}
		}
	}
}
