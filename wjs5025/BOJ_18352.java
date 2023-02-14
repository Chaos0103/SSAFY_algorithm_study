package BOJ_18352;

/**
 * 이제 살짝씩 이해가 되기 시작했습니다익스트라.
 * 처음에 큐 안썼더니 시간초과 맞아서,
 * 이제부터 큐 있는 버전만 쓰기로 마음먹었습니다.
 */

import java.io.*;
import java.util.*;

public class Main {
	static class Node implements Comparable<Node> {
		int index;
		int distance;

		public Node(int index, int distance) {
			this.index = index;
			this.distance = distance;
		}

		// 디버깅용 투스트링
		@Override
		public String toString() {
			return "Node [index=" + index + ", distance=" + distance + "]";
		}

		@Override
		public int compareTo(Node o) {
			if (this.distance < o.distance)
				return -1;
			return 1;
		}

	}

	public static int N, M, K, X;
	public static final int INF = (int) 1e9;
	public static int[] table; // 최단거리 테이블
	public static ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();

	// 큐를쓰자 !
	public static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();

		table[start] = 0;
		pq.offer(new Node(start, 0));
		
		while (!pq.isEmpty()) {
			Node node = pq.poll();
			int dist = node.distance;
			int smallCostIdx = node.index;

			
			// 현재 노드가 이미 처리된 적 있는 노드면 (다른 최단거리가 있는 애면)
            if (table[smallCostIdx] < dist) {
                continue;
            }
            
			// 현재 노드와 인접한 다른 노드 확인
			for (int i = 0; i < graph.get(smallCostIdx).size(); i++) {
				int cost = table[smallCostIdx] + graph.get(smallCostIdx).get(i).distance;

				if (cost < table[graph.get(smallCostIdx).get(i).index]) {
					table[graph.get(smallCostIdx).get(i).index] = cost;
					pq.offer(new Node(graph.get(smallCostIdx).get(i).index, cost));
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 입력받기
		String tmp = br.readLine();
		StringTokenizer st = new StringTokenizer(tmp, " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		// 테이블
		table = new int[N + 1];

		// 최단거리 테이블 초기화
		Arrays.fill(table, INF);

		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Node>());
		}

		// 도로 추가 (노드와 간선 연결 정보 추가)
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph.get(from).add(new Node(to, 1));
		}

		dijkstra(X);

		StringBuilder sb = new StringBuilder();
		int cnt = 0;
		for (int i = 1; i < table.length; i++) {
			if (table[i] == K) {
				sb.append(i).append("\n");
				cnt++;
			}
		}

		if (cnt == 0) {
			sb.append(-1);
		}
		System.out.println(sb);

	}

//	static void print() {
//		for (ArrayList<Node> al : graph) {
//			System.out.println(graph);
//		}
//	}
}
