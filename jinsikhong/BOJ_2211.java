package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


/**
 * 문제 : 네트워크 복구
 * 걸린 시간 : 40분
 * 사용 알고리즘 : 다익스트라
 * @author 홍진식
 *
 */


public class BOJ_2211 {
	static int N, M;
	static ArrayList<Node>[] graph;
	static Node[] dist;
	static final int maxVal = 100000;
	static ArrayList<Node> result = new ArrayList<>();
	static StringBuilder sb = new StringBuilder();
	
	static class Node implements Comparable<Node>{
		int to, cost;
		public Node(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.cost - o.cost;
		}
		@Override
		public String toString() {
			return "Node [to=" + to + ", cost=" + cost + "]";
		}
		
		
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		dist = new Node[N+1]; 
		graph = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
			dist[i] = new Node(-1 , maxVal);
		} // 인접 리스트 초기화
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			graph[A].add(new Node(B, C));
			graph[B].add(new Node(A, C));
		}
		Dijkstra(new Node(1, 0));
		System.out.println(N-1);
//		System.out.println(sb);
//		System.out.println(Arrays.toString(dist));
		for(int i = 2; i <= N; i++) {
			System.out.println(i + " " + dist[i].to);
		}
	}
	
	static void Dijkstra(Node node) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(node);
		dist[node.to].cost = 0;
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			if(dist[cur.to].cost < cur.cost) {
				continue;
			}
			for(int i = 0; i < graph[cur.to].size(); i++) {
				Node next = graph[cur.to].get(i);
				if(dist[next.to].cost > dist[cur.to].cost + next.cost) {
					sb.append(cur.to + " " + next.to + " " + "\n");
					dist[next.to].cost = dist[cur.to].cost + next.cost;
					dist[next.to].to = cur.to;
					pq.offer(new Node(next.to, dist[next.to].cost));
					
				}
			}
		}
		
	}
	
	
	
	
}
