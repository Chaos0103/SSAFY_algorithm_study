package com.yoojin.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2623 {
	static int N,M; // 출연가수 수, 보조 PD 수 
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	static int[] degree; // 진입차수 수
	static ArrayList<Integer> results = new ArrayList<>();
	static Queue<Integer> queue = new LinkedList<>(); // 큐
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		for(int i =0;i<N+1;i++) {
			graph.add(new ArrayList<>()); // graph 배열 초기화
		}
		
		degree= new int[N+1];
		
		for(int i =0;i<M;i++) {
			// 보조 pd 수만큼 돌면서
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			for(int j =1;j<num;j++) {
				int to = Integer.parseInt(st.nextToken());
				graph.get(from).add(to);
				degree[to]++;
				
				from = to;
			}
		}
		
		getResult();		
		
		
		if (results.size() != N) {
			System.out.println(0);
		} else {
			for(int i=0;i<results.size();i++) {
				System.out.println(results.get(i));
			}
		}

	}
	
	private static void getResult() {
		// 1. 진입차수가 0인 애들 찾기
		for(int i =1;i<N+1;i++) {
			if(degree[i] == 0) {
				queue.offer(i); // 큐에 삽입
			}
		}
		
		// 2. 큐가 빌떄까지 반복
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			results.add(cur);
			for(int i = 0;i<graph.get(cur).size();i++) {
				if(--degree[graph.get(cur).get(i)] == 0) {
					queue.offer(graph.get(cur).get(i));
				}
			}
		}
	}
}
