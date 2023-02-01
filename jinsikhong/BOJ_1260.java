package BJ;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * 참고 : 이코테 강의 참고 :  https://www.youtube.com/watch?v=7C9RgOcvkvo&list=PLRx0vPvlEmdAghTr5mXQxGpHjWqSz0dgC&index=5
 * 풀이시간 : 40분
 */

public class BOJ_1260 { //DFS와 BFS
	static boolean[] visited;
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt(); //정점의 개수
		int m = sc.nextInt(); //간선의 개수
		int v = sc.nextInt(); //시작 정점
		
		visited = new boolean[n+1];
		for(int i = 0; i <= n; i++) {
			graph.add(new ArrayList<Integer>());
		}
		
		for(int i = 0; i < m; i++) {
			int node1 = sc.nextInt();
			int node2 = sc.nextInt();	
			graph.get(node1).add(node2);
			graph.get(node2).add(node1);
		}
		dfs(v);
		System.out.println("");
		visited = new boolean[n+1];
		for(int i = 0; i <= n; i++) {
			graph.add(new ArrayList<Integer>());
		}
		bfs(v);
		
	}
	
	static void dfs(int v) {
		visited[v] = true;
		System.out.print(v + " ");
		Collections.sort(graph.get(v));
		for(int i = 0; i < graph.get(v).size(); i++) {
			int x = graph.get(v).get(i);
			if(!visited[x]) dfs(x);
		}
	}
	
	static void bfs(int v) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(v);
		visited[v] = true;
		
		while(!q.isEmpty()) {
			int x = q.poll();
			System.out.print(x + " ");
			Collections.sort(graph.get(x));
			for(int i = 0; i < graph.get(x).size(); i++) {
				int y = graph.get(x).get(i);
				if(!visited[y]) {
					q.offer(y);
					visited[y] = true;
				}
			}

		}
	}

}
