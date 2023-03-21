package BOJ_5972;

import java.io.*;
import java.util.*;

class Node implements Comparable<Node> {
	int idx;
	int distance;

	@Override
	public String toString() {
		return "Node [idx=" + idx + ", distance=" + distance + "]";
	}

	public Node(int idx, int distance) {
		super();
		this.idx = idx;
		this.distance = distance;
	}

	// 우선순위 큐 거리순 정렬을 위한
	@Override
	public int compareTo(Node o) {
		return this.distance - o.distance;
	}
}

public class Main {
	// INF
	static final int INF = (int) 1e9;
	static int N, M;

	// 그래프
	static ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();

	// 최단거리 테이블
	public static int[] d = new int[50001];
	
	// 다익스트라
	public static void dijkstra() {
		// 1. pq 만들기.
		PriorityQueue<Node> pq = new PriorityQueue<>();

		// 2. 시작노드 초기화 / 큐에 넣기
		d[1] = 0; // start까지 가는 최단거리는 0
		pq.offer(new Node(1, 0));

		while (!pq.isEmpty()) {
            Node node = pq.poll();
            int dist = node.distance;
            int now = node.idx;

            if (d[now] < dist) {
                continue;
            }

            for (int i=0; i<graph.get(now).size(); i++) {
                int cost = d[now] + graph.get(now).get(i).distance;

                if (cost < d[graph.get(now).get(i).idx]) {
                    d[graph.get(now).get(i).idx] = cost;
                    pq.add(new Node(graph.get(now).get(i).idx, cost));
                }
            }

		}
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		// 그래프 생성
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Node>());
		}

		for (int i = 1; i <= M; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			int distance = sc.nextInt();
			graph.get(from).add(new Node(to, distance));
			graph.get(to).add(new Node(from, distance));
		}

		// 최단거리 테이블 초기화
		Arrays.fill(d, INF);
		
		
		dijkstra();

        System.out.println(d[N]);
	}
}
