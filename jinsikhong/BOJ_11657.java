package 최단경로;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class BOJ_11657 {
	static long[] dist;
	static ArrayList<Node> graph = new ArrayList<>();
	static final int INF = Integer.MAX_VALUE;
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		dist = new long[n+1];
		Arrays.fill(dist, INF);
		dist[1] = 0;
		
		for(int i = 0; i < m; i ++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();
			graph.add(new Node(a,b,c));
		}
		
		boolean result = BellmanFord(n, m, 1);
		StringBuilder sb = new StringBuilder();
		if (result) {
			for(int i = 2; i <= n; i++) {
				 sb.append(dist[i] == INF ? "-1\n" : dist[i] + "\n");
			}
		}else {
			sb.append("-1\n");
		}
		bw.write(sb.toString());
		bw.close();

		
	}
	public static boolean BellmanFord(int n, int m, int start) {
		
		for(int i = 1; i < n; i++) {
			for(int j = 0; j < m; j++) {
				Node no = graph.get(j);
				if(dist[no.v] != INF && dist[no.w] > dist[no.v] + no.cost) {
					dist[no.w] = dist[no.v] + no.cost;
				}
			}
		}
		
		for(int i = 0; i < m; i++) {
			Node no = graph.get(i);
			if(dist[no.v] != INF && dist[no.w] > dist[no.v] + no.cost) {
				return false;
			}
		}
		return true;
		
	}
	
	
	static class Node{
		int v;
		int w;
		int cost;
		
		public Node(int v, int w, int cost) {
			this.v = v;
			this.w = w;
			this.cost = cost;
		}
	}
}
