package com.yoojin.boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ_2096 {
	static int[][] maps;
	static int[][] dpMins;
	static int[][] dpMaxs;
	static int N;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		maps = new int[N][3];
		dpMins = new int[N][3];
		dpMaxs = new int[N][3];
		for(int i=0;i<N;i++) {
			maps[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			if(i == N-1) {
				dpMins[i] = maps[i];
				dpMaxs[i] = maps[i];
			} else {
				Arrays.fill(dpMins[i], Integer.MAX_VALUE);
				Arrays.fill(dpMaxs[i], Integer.MIN_VALUE);
			}
		}
		
		int[][] locs = {{-1,-1},{-1,0},{-1,1}};
		for(int i =N-1;i>0;i--) {
			for(int j = 0;j<3;j++) {
				for(int[] loc:locs) {
					int nx = i+loc[0];
					int ny = j+loc[1];
					if(nx <0 || ny >= 3 || ny < 0) continue;
					dpMaxs[nx][ny] = Math.max(dpMaxs[nx][ny], dpMaxs[i][j] + maps[nx][ny]);
					dpMins[nx][ny] = Math.min(dpMins[nx][ny], dpMins[i][j] + maps[nx][ny]);
				}
			}
		}
		
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for(int i=0;i<3;i++) {
			min = Math.min(min, dpMins[0][i]);
			max = Math.max(max, dpMaxs[0][i]);
		}
		
		System.out.println(max + " " + min);
	}
}
