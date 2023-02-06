import static java.util.Collections.sort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * SSAFY 알고리즘 스터디 3일차 - DFS 와 BFS
 * 30분 소요
 * @author SSAFY
 *
 */
public class BOJ_1260 {
	
	static int n, m, v;
	static ArrayList<ArrayList<Integer>> graph;
	static boolean[] visited;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		v = sc.nextInt();
		graph = new ArrayList<>();
		visited = new boolean[n+1];
		
		// 노드별 리스트 생성
		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<Integer>());
		}
		
		// 간선 정보 입력
		for (int i = 0; i < m; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		// 숫자가 낮은 노드부터 접근을 위해 정렬
		for (ArrayList<Integer> list: graph) {
			sort(list);
		}
		
		dfs(v);
		System.out.println();
		visited = new boolean[n+1];
		bfs(v);
	}
	static void dfs(int x) {
        // 현재 노드 방문 처리
        visited[x] = true;
        System.out.print(x + " ");

        // 현재 노드와 연결된 다른 노드를 재귀적으로 방문
        for (int i = 0; i < graph.get(x).size(); i++) {
            int y = graph.get(x).get(i);
            if (!visited[y]) dfs(y);
        }
    }
	
	static void bfs(int start) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		visited[start] = true;
		
		while(!q.isEmpty()) {
			int x = q.poll();
			System.out.print(x + " ");
			
			for (int i = 0; i < graph.get(x).size(); i++) {
				int y = graph.get(x).get(i);
				if (!visited[y]) {
					q.offer(y);
					visited[y] = true;
				}
			}
		}
	}
}
