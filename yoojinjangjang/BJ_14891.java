package com.yoojin.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_14891 {
	static int[][] gears; // 톱니바퀴 배열
	static int[] idx; // 톱니바퀴 배열의 각 12시를 가르킬 인덱스 배열
	static boolean[] visited;
	static int K;
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		gears = new int[4][8];
		idx = new int[4];
		
		for(int i =0;i<4;i++) {
			gears[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
		}
		
		K = Integer.parseInt(br.readLine());
		for(int i = 0;i<K;i++) {
			st = new StringTokenizer(br.readLine());
			int gearNum = Integer.parseInt(st.nextToken()) -1;
			int rotation = Integer.parseInt(st.nextToken());
			
			visited = new boolean[4];
			dfs(gearNum,rotation);
		}
		
		// 각 톱니바퀴의 12시 방향 *1,2,4,8
		int[] scores = {1,2,4,8};
		int ans = 0;
		for(int i =0;i<4;i++) {
			ans += (scores[i] * gears[i][idx[i]]);
		}
		
		System.out.println(ans);
	}
	
	static void dfs(int currentGear, int rotation) {
		visited[currentGear] = true; // 현재 톱니 바퀴 방문 처리해주고
		
		if(currentGear-1 >= 0 && !visited[currentGear-1]) { // 왼쪽 톱니 확인 
			if(gears[currentGear-1][(idx[currentGear-1]+2)%8] != gears[currentGear][(idx[currentGear]+6)%8]) { // 왼쪽 톱니와의 결합하는 위치가 일치하지 않는 경우  
				dfs(currentGear-1,rotation*-1);
			}
		}
		
		if(currentGear+1 < 4 && !visited[currentGear+1]) {
			if(gears[currentGear+1][(idx[currentGear+1]+6)%8] != gears[currentGear][(idx[currentGear]+2)%8]) {
				dfs(currentGear+1,rotation*-1);
			}
		}
		
		// 현재 바퀴 돌리기 
		int newIdx = idx[currentGear] + (rotation*-1);
		if(newIdx == -1) newIdx = 7;
		else newIdx %= 8;
		
		idx[currentGear] = newIdx;
	}
}
