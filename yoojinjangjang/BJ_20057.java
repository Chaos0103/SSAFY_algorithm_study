package com.yoojin.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ_20057 {
	static int N;
	static int[][] sands;
	static int garbageSands;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {-1,0,1,0};
	static int[][][] checkArr = new int[][][]{
		{	{-1,0,7},{-2,0,2},{-1,-1,10},{-1,1,1},{0,-2,5},{1,-1,10},{1,0,7},{1,1,1},{2,0,2},{0,-1,0}  },
		{   {-1,-1,1},{-1,1,1},{0,-1,7},{0,-2,2},{0,1,7},{0,2,2},{1,-1,10},{1,1,10},{2,0,5},{1,0,0} },
		{	{-1,0,7},{-2,0,2},{-1,-1,1},{-1,1,10},{0,2,5},{1,-1,1},{1,0,7},{1,1,10},{2,0,2},{0,1,0} }, 
		{ 	{-2,0,5}, {-1,-1,10},{-1,1,10},{0,-2,2},{0,-1,7},{0,1,7},{0,2,2},{1,-1,1},{1,1,1},{-1,0,0}	}
	};
	static int d = 0;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		sands = new int[N][N];
		for(int i=0;i<N;i++) {
			sands[i]  = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		
		int repeatCnt = 1;
		int x = N/2;
		int y = N/2;
		
		garbageSands = 0;
		while(true) {
			for(int i =0;i<2;i++) {
				for(int j = 0;j<repeatCnt;j++) {
					x = x + dx[d];
					y = y + dy[d];
					doWind(d,x,y);
					if(x == 0 && y == 0) break;
				}
				d = (d+1)%4;
				if(x == 0 && y == 0) break;
			}
			repeatCnt++;
			if(x == 0 && y == 0) break;
		}
		
		System.out.println(garbageSands);
	}

	private static void doWind(int num, int x, int y) {
		int extra = sands[x][y];
		if(extra == 0) return;
		for(int i =0;i<10;i++) {
			int nx = x + checkArr[num][i][0];
			int ny = y + checkArr[num][i][1];
			double ratio = 0.01 * checkArr[num][i][2];
			if(nx < 0 || nx >= N || ny < 0 || ny >= N ) {
				// 범위밖인 경우
				if(ratio == 0) {
					// 남은 것들을 더해주기
					garbageSands += extra;
				} else {
					garbageSands += Math.floor(sands[x][y] * ratio);
					extra -= Math.floor(sands[x][y] * ratio);
				}
			} else {
				if(ratio == 0) {
					sands[nx][ny] += extra;
				} else {
					sands[nx][ny] += Math.floor(sands[x][y] * ratio);
					extra -= Math.floor(sands[x][y] * ratio);
				}
			}
		}
		sands[x][y] = 0;
	}
}
