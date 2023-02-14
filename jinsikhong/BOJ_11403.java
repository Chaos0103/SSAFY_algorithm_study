package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_11403 {
	static int[][] graph;
	static int[][] result;
//	static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;
		int n = Integer.parseInt(br.readLine());
//		for(int i = 0; i < n; i ++) {
//			graph.add(new ArrayList<>());
//		}
//		result = new int[n][n];
		graph = new int[n][n];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < n; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		} // 그래프 입력 받기
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				for(int k = 0; k < n; k++) {
					if(graph[j][i] == 1 && graph[i][k] == 1)
						graph[j][k] = 1; 
				}
			}
		}
		
		for(int[] arr : graph) {
			for(int x : arr) {
				System.out.print(x + " ");
			}
			System.out.println();
		}
	}
	
//	public static Stack<Integer> stack = new Stack<>();
//	
//	static void dfs(int x) {
//		
//		stack.add(x);
//		
//		
//		// 방문 노드 출력
//		
//		while(!stack.isEmpty()) {
//			int no = stack.pop();
//			for(int i = 0; i < graph.get(no).size(); i++) {
//				int next = graph.get(no).get(i);
//				if(result[no][next] == 0) {
//					stack.push(next);
//					result[no][next] = 1;
//				}
//			}
//		}
//	}
//	
	
//	static void bfs(int x) {
////		dist[x] = 0;
//		Queue<Integer> q = new LinkedList<>();
//		q.offer(x);
//		while(!q.isEmpty()) {
//			int no = q.poll();
//			for(int i = 0; i < graph.get(no).size(); i++) {
//				int next = graph.get(no).get(i);
//				if(result[no][i] == 0) {
//					result[no][i] = 1;
//				}
//			}
//			
//			
//			for(int i = 0; i < graph.get(no).size(); i++) {
//				int next = graph.get(no).get(i);
//				if(dist[next] == -1) {
//					dist[next] = dist[no] + 1;
//					q.offer(next);
//				}
//			}
//			
//		}
//	}
}
