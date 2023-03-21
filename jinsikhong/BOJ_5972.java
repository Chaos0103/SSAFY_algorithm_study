package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_5972 {
	static int N, M;
	static ArrayList<Node>[] graph;
	static int[] d;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new ArrayList[N+1];
		d = new int[N+1];
		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<Node>();
			d[i] = Integer.MAX_VALUE;
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int distance = Integer.parseInt(st.nextToken());
			graph[from].add(new Node(to, distance));
			graph[to].add(new Node(from, distance));
		}
		dijkstra();
		System.out.println(d[N]);
		
		
		
					
	}
	static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(1, 0));
		d[1] = 0;
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			if(d[cur.to] < cur.d) {
				continue;
			}
			for(int i = 0; i < graph[cur.to].size(); i++) {
				Node nd = graph[cur.to].get(i);
				if(d[nd.to] > d[cur.to] + nd.d) {
					d[nd.to] = d[cur.to] + nd.d;
					pq.offer(new Node(nd.to, d[nd.to]));
				}
			
			}
			
		}
	}
	
	
	static class Node implements Comparable<Node>{
		int to, d;
		public Node(int to, int d) {
			this.to = to;
			this.d = d;
		}
		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.d - o.d;
		}
		
	}
}
