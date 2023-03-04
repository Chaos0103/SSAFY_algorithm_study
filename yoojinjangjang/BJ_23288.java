package com.yoojin.boj;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import javax.swing.ViewportLayout;

public class BJ_23288 {
	static int N,M,K;
	static int[][] maps;
	
	
	static int[] dices = {1,6,4,3,5,2};
	static int[][] RBLT = { 
			{2,3,1,0,4,5}, // 우
			{5,4,2,3,0,1}, // 하
			{3,2,0,1,4,5}, // 좌
			{4,5,2,3,1,0}  // 상 
	}; 
	
	static int d = 0; // 현재 방향 정보
	static int dx[] = {0,1,0,-1};
	static int dy[] = {1,0,-1,0}; // 우하좌상
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		maps = new int[N][M];
		for(int i =0;i<N;i++) {
			maps[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		
		result = 0;
		// k번 이동
		doMove();
		
		System.out.println(result);
	}
	
	static int score; // 현재 점수 
	static int result; // 점수의 총합
	private static void doMove() {
		int x = 0;
		int y = 0;
		for(int k = 0;k<K;k++) { // k번 반복하며
			// 1. 이동방향으로 이동
			// 1-1. 이동방향 이동 전에 범위탐색 진행
			if(x + dx[d] < 0 || x + dx[d] >= N || y + dy[d] < 0 || y + dy[d] >= M) {
				// 범위를 벗어난 경우 d를 반대 방향으로 변경
				d = (d+2) % 4;
			}
			x = x + dx[d];
			y = y + dy[d]; // 좌표 변경하기
			changeDice();
			
			//점수 계산하기
			score = 0;
			calcScore(x,y);
			result += score; // 총 점수 합하기
			
			int bottomS = dices[1]; // 아래 숫자
			int xyS = maps[x][y]; // 현재 좌표 점수
			if(bottomS > xyS) {
				d = (d+1) % 4;
			} else if(bottomS < xyS) {
				d = (d-1);
				if (d == -1) d = 3;
			}
			
		}
		
	}

	private static void calcScore(int x, int y) {
		Point start = new Point(x,y);
		Queue<Point> queue = new LinkedList<>();
		queue.offer(start);
		boolean[][] visited = new boolean[N][M];
		visited[x][y] = true;
		int cnt = 1;
		int s = maps[x][y];
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			for(int l = 0;l<4;l++) {
				int nx = cur.x + dx[l];
				int ny = cur.y + dy[l];
				if(nx <0 || nx>=N||ny<0||ny>=M) continue;
				if(visited[nx][ny]) continue;
				if(maps[nx][ny] == s) {
					visited[nx][ny] = true;
					cnt++;
					queue.offer(new Point(nx,ny));
				}
				
			}
		}
		
		score = (s*cnt);
	}
	static int[] tempDices = new int[6];
	private static void changeDice() { // 주사위의 현재 상태 변경
		tempDices = Arrays.copyOf(dices, 6);
		for(int i=0;i<6;i++) {
			dices[i] = tempDices[RBLT[d][i]];
		}
	}
}