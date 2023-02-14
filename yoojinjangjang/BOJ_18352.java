package com.yoojin.shortest_path;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node>{
	int index;
	int distance;
	
	public Node(int index, int distance) {
		this.index = index;
		this.distance = distance;
	}
	public int getIndex() {
		return index;
	}
	public int getDistance() {
		return distance;
	}
	@Override
	public int compareTo(Node o) {
		if(this.distance < o.distance) {
			return -1;
		}
		return 1;
	}
	
}

public class BOJ_18352 {
	public static final int INF = (int)1e9;
	public static int n,m,k,start;
	public static int[] d;
	public static ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
	
	public static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		d[start] = 0;
		pq.add(new Node(start,0));
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			int now = node.getIndex();
			int dist = node.getDistance();
			if(d[now] < dist) {
				continue;
			}
			for(int i =0;i<graph.get(now).size();i++) {
				int cost = d[now] + graph.get(now).get(i).getDistance();
				if(cost < d[graph.get(now).get(i).getIndex()] ) {
					d[graph.get(now).get(i).getIndex()] = cost;
					pq.offer(new Node(graph.get(now).get(i).getIndex(),cost));
				}
			}
		}
		
	}
	
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(st.nextToken());
		
		d = new int[n+1];
		
		// 최단 거리 배열 갱신
		Arrays.fill(d, INF);
		
		for(int i =0;i<=n;i++) {
			graph.add(new ArrayList<Node>());
		}
		
		
		
		
		// 간선 정보 받기
		for(int i =0;i<m;i++) {
			st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph.get(a).add(new Node(b,1));
		}
		
		
		dijkstra(start);
		
		
		boolean flag = true;
		for(int i = 1;i<=n;i++) {
			if(d[i] == k) {
				flag = false;
				System.out.println(i);
			}
		}
		if(flag) {
			System.out.println(-1);
		}
	}
}