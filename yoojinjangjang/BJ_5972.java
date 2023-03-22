package com.yoojin.boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_5972 {
	static class Node {
		int dis;
		int pos;
		public Node(int pos,int dis ) {
			super();
			this.dis = dis;
			this.pos = pos;
		}
		@Override
		public String toString() {
			return "Node [dist=" + dis + ", pos=" + pos + "]";
		}

		
	}
	static final int INF = (int)1e9;
	static int N,M;
	static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
	static int[] dist;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		dist = new int[N];
		Arrays.fill(dist, INF);
		
		for(int i=0;i<N;i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int to = Integer.parseInt(st.nextToken())-1;
			int from = Integer.parseInt(st.nextToken())-1;
			int dis = Integer.parseInt(st.nextToken());
			graph.get(to).add(new Node(from,dis));
			graph.get(from).add(new Node(to,dis));
		}
		
		dist[0] = 0;
		dijkstra(new Node(0,0));
		
		System.out.println(dist[N-1]);
		
		
	}
	private static void dijkstra(Node start) {
		Queue<Node> queue = new LinkedList<>();
		queue.offer(start);
		
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			int dis = cur.dis;
			int pos = cur.pos;
			
			if(dist[pos] < dis) {
				continue;
			}
			
			for(int i=0;i<graph.get(pos).size();i++) {
				int cost = dist[pos] + graph.get(pos).get(i).dis;
				if(dist[graph.get(pos).get(i).pos] > cost) {
					dist[graph.get(pos).get(i).pos]= cost;
					queue.offer(new Node(graph.get(pos).get(i).pos,cost));
				}
			}
		}
	}
}
