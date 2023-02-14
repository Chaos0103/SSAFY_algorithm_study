package bj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_18352 {
	
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
	static int[] dist;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(); // 도시 개수
		int m = sc.nextInt(); // 도로 개수
		int k = sc.nextInt(); // 거리정보 k
		int x = sc.nextInt(); // 출발 도시 번호
		for(int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}
		for(int i = 0; i < m; i++) {
			int s = sc.nextInt();
			int e = sc.nextInt();
			graph.get(s).add(e);
		}
		dist = new int[n+1];
		Arrays.fill(dist, -1);
		
		bfs(x);
		boolean chk = false;
		for(int i = 1; i <= n; i++) {
			if(dist[i] == k) {
				System.out.println(i);
				chk = true;
			}
		}
		if(!chk) {
			System.out.println(-1);
		}
	}
	
	static void bfs(int x) {
		dist[x] = 0;
		Queue<Integer> q = new LinkedList<>();
		q.offer(x);
		while(!q.isEmpty()) {
			int no = q.poll();
			for(int i = 0; i < graph.get(no).size(); i++) {
				int next = graph.get(no).get(i);
				if(dist[next] == -1) {
					dist[next] = dist[no] + 1;
					q.offer(next);
				}
			}
			
		}
	}
	

	

}
