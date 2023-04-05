package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;


/**
 * 문제 : 해킹
 * 문제 유형 : 다익스트라
 * 걸린 시간 : 2시간
 * 풀이 방법 :
 * 첫 시도 : bfs depth 활용 -> 실패,
 * bfs사용 시 이전의 결과값을 사용하긴 하지만, 갱신을 제대로 못함(최단거리를 못 구함)
 * bfs를 사용해서 최단거리를 구하려면 노드간 거리가 "1"일때만
 * 
 * 다른 풀이 방법 : 다익스트라 사용.
 * 최소힙을 사용하기 떄문에 최단 거리를 구할 수 있음
 * 
 * 
 * @author 홍진식
 *
 */

public class BOJ_10282 {
	
	static int n, d, c;
	static ArrayList<Node>[] graph;
	static int[] infection;
	
	static class Node implements Comparable<Node>{
		int to, depth;
		public Node(int to, int depth) {
			this.to = to;
			this.depth = depth;
		}
		@Override
		public int compareTo(Node o) {
			return this.depth - o.depth;
		}
	}
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스
		StringTokenizer st;
		for(int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			graph = new ArrayList[10001];
			infection = new int[10001];
			for(int i = 1; i <= 10000; i++) {
				graph[i] = new ArrayList<>();
			}
			
			for(int i = 0; i < d; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				graph[b].add(new Node(a, s));
			}
			Arrays.fill(infection, Integer.MAX_VALUE);
			dijkstra(new Node(c, 0));
			
			int result = 0;
			int max = 0;
			
			for(int i = 1; i <= 10000; i++) {
				if(infection[i] != Integer.MAX_VALUE) {
					result++;
					max = Math.max(max, infection[i]);
				}
			}
			System.out.println(result + " " + max);
			
		}
		
	}
	
	
	static void dijkstra(Node node) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(node);
		infection[node.to] = 0;
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			if(infection[cur.to] < cur.depth) {
				continue;
			}
			for(int i = 0; i < graph[cur.to].size(); i++) {
				Node next = graph[cur.to].get(i);
				if(infection[next.to] > infection[cur.to] + next.depth) {
					infection[next.to] = infection[cur.to] + next.depth;
					pq.offer(new Node(next.to, infection[next.to]));
				}
			}
			
			
		}
	}
}
