package com.yoojin.boj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_2617 {
	static ArrayList<ArrayList<Integer>> heavy = new ArrayList<>();
	static ArrayList<ArrayList<Integer>> light = new ArrayList<>();
	static int[] heavyCnt;
	static int[] lightCnt;
	static int N,M;
	static boolean[] visited;
	static int result = 0;
	static int idx;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		heavyCnt = new int[N];
		lightCnt = new int[N];
		for(int i=0;i<N;i++) {
			heavy.add(new ArrayList<>());
			light.add(new ArrayList<>());
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int to = Integer.parseInt(st.nextToken())-1;
			int from = Integer.parseInt(st.nextToken())-1;
			heavy.get(to).add(from);
			light.get(from).add(to);
		}
		
		
		for(int i=0;i<N;i++) {
			visited = new boolean[N];
			idx = i;
			heavyDfs(i);
		}
		
		for(int i=0;i<N;i++) {
			visited = new boolean[N];
			idx = i;
			lightDfs(i);
		}
		

		
		for(int i=0;i<N;i++) {
			if(heavyCnt[i] >= (N+1)/2 || lightCnt[i] >= (N+1)/2) {
				result++;
			}
		}
		
		System.out.println(result);
	}
	
	private static void heavyDfs(int i) {
		for(int n = 0;n<heavy.get(i).size();n++) {
			if(visited[heavy.get(i).get(n)]) continue;
			visited[heavy.get(i).get(n)] = true;
			heavyCnt[idx]++;
			heavyDfs(heavy.get(i).get(n));
		}
	}
	
	private static void lightDfs(int i) {
		for(int n = 0;n<light.get(i).size();n++) {
			if(visited[light.get(i).get(n)]) continue;
			visited[light.get(i).get(n)] = true;
			lightCnt[idx]++;
			lightDfs(light.get(i).get(n));
		}
	}
	
}
