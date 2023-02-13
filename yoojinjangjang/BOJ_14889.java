package com.yoojin.bfsearch;

import java.util.Scanner;

public class BOJ_14889 {
	static int minValue = Integer.MAX_VALUE;
	static int[][] capacity;
	static boolean[] visited;
	static int N;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		capacity = new int[N][N];
		visited = new boolean[N];
		
		for(int i = 0;i<N;i++) {
			for(int j = 0;j<N;j++) {
				capacity[i][j] = sc.nextInt();
			}
		}
		

		combi(0, 0);
		
		System.out.println(minValue);
	}
	
	static void combi(int idx, int depth) {
		if(depth == N / 2) {
			// 각 팀 별로 계산 수행
			getMin();
			return;
		}
		
		for(int i = idx;i<N;i++) {
			
			if(!visited[i]) {
				visited[i] = true;
				combi(i+1, depth+1);
				visited[i] = false;
			}
		}
	}
	
	static void getMin() {
		int startTeam = 0;
		int linkTeam = 0;
		
		for(int i = 0;i<N-1;i++) {
			for (int j = i+1;j<N;j++) {
				if (visited[i] == true && visited[j] == true) {
					// startTeam
					startTeam += capacity[i][j];
					startTeam += capacity[j][i];
				} else if (visited[i] == false && visited[j] == false) {
					// linkTeam
					linkTeam += capacity[i][j];
					linkTeam += capacity[j][i];
				}
			}
		}
		
		int absVal = Math.abs(startTeam-linkTeam);
		
		if (absVal == 0) {
			System.out.println(absVal);
			System.exit(0);
		}
		
		minValue = Math.min(absVal, minValue);
	}
}
