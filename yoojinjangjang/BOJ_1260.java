package com.yoojin.dfsbfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_1260 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); // 정점의 개수
		int M = sc.nextInt(); // 간선의 개수
		int V = sc.nextInt(); // 시작할 정점의 번호
		
		ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
		
		for(int i = 0;i<N+1;i++) {
			graph.add(new ArrayList<Integer>());
		}
		for(int i = 0;i<M;i++) {
			int v1 = sc.nextInt();
			int v2 = sc.nextInt();
			graph.get(v1).add(v2);
			graph.get(v2).add(v1);
			
		}
		for(int i = 1;i<N+1;i++) {
			Collections.sort(graph.get(i));
		}
		dfs(graph, new boolean[N+1],V);
		System.out.println();
		bfs(graph, new boolean[N+1],V);
	}
	

	static void dfs(ArrayList<ArrayList<Integer>> graph, boolean[] visited, int start) {
		visited[start] = true; 
		System.out.print(start+ " ");
		
		Deque<Integer> stack = new LinkedList<>();
		stack.push(start);
		
		while(!stack.isEmpty()) {
			int now = stack.peek();
			
			boolean hasNearNode = false;
			
			for(int i:graph.get(now)) {
				if (!visited[i]) {
					stack.push(i);
					visited[i] = true;
					hasNearNode = true;
					System.out.print(i+" ");
					break;
				}
			}
			
			if(!hasNearNode) {
				stack.pop();
			}
		}
	}
	
	static void bfs(ArrayList<ArrayList<Integer>> graph, boolean[] visited, int start) {
		visited[start] = true;
		Queue<Integer> queue = new LinkedList<>();
		queue.add(start);
		
		while(!queue.isEmpty()) {
			int v = queue.poll(); 
			System.out.print(v+" ");
			for(int i : graph.get(v)) {
				if(!visited[i]) {
					visited[i] = true;
					queue.add(i);
				}
			}
		}
		
	}
}