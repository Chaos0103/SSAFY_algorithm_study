package com.yoojin.swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_활주로건설 {
	static int N,X;
	static boolean[] install;
	static int[][] maps;
	static int[] currentCheck;
	static int cnt;
	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("swea_활주로.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int testNum =  1;testNum<=T;testNum++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			maps = new int[N][N];
			for(int i =0;i<N;i++) {
				maps[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
			currentCheck = new int[N];
			cnt = 0;
			for(int i =0;i<N;i++ ) {
				for(int j = 0;j<N;j++) {
					currentCheck[j] = maps[i][j];
				}
				install = new boolean[N]; // 현재 설치 정보 갱신
				if(checkCurrent()) {
					// 현재 활주로를 체크하기
					cnt++;
				}
			}
			
			for(int j = 0;j<N;j++) {
				for(int i = 0;i<N;i++) {
					currentCheck[i] = maps[i][j];
				}
				install = new boolean[N];
				if(checkCurrent()) {
					cnt++;
				}
			}
			
			
			System.out.printf("#%d %d%n",testNum, cnt);
			
		}
	}
	private static boolean checkCurrent() {
		
		for(int i=0;i<N-1;i++) {
			// 왼쪽에서 오른쪽을 확인하며
			if(currentCheck[i] < currentCheck[i+1]) {
				// 값이 크면 왼쪽의 경사로 설치 여부를 확인한다. 
				if(Math.abs(currentCheck[i] - currentCheck[i+1]) != 1) return false; // 경사로가 1 이상 차이나는 경우 
				for(int x = 0;x<X;x++) {
					if(i-x < 0) return false; // 경사를 설치하기 전에 범위를 벗어나면 경사 설치 불가 
					if(!check(currentCheck[i], i-x)) return false;
				}
			}
		}
		
		for(int i=N-1;i>=1;i--) {
			// 오른쪽에서 왼쪽으로 값을 확인하며
			if(currentCheck[i] < currentCheck[i-1]) {
				//값이 크면 오른쪽 경사로 설치 여부를 확인한다. 
				if(Math.abs(currentCheck[i] - currentCheck[i-1]) != 1) return false; // 경사로가 1이상 차이나는 경우
				for(int x = 0;x<X;x++) {
					if(i+x >= N) return false; // 경사 설치 전에 범위 벗어나면 경사 설치 불가
					if(!check(currentCheck[i], i+x)) return false;
				}
			}
		}
		
		return true;
	}
	
	
	private static boolean check(int value, int i) {
		if(install[i]) return false; // 이미 설치되어있는 경우 설치 못함 
		if(currentCheck[i] != value) return false; // 경사로를 설치하는 곳의 높이가 다른 경우 설치 못함
		
		install[i] = true; // 전부 아니면 경사 설치
		
		return true;
	}
}
