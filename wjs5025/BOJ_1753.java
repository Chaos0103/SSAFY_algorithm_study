import java.util.*;
import java.io.*;

/*
 *  너무 어려워요.
 */
class Node implements Comparable<Node> {
	private int index;
	private int distance;

	public Node(int index, int distance) {
		this.index = index;
		this.distance = distance;
	}

	public int getIndex() {
		return this.index;
	}

	public int getDistance() {
		return this.distance;
	}

	@Override
	public int compareTo(Node o) {
		if (this.distance < o.getDistance()) {
			return -1;
		}
		return 1;
	}
}

public class Main {
	// INF
	public static final int INF = (int) 1e9;

	// N, E, start
	public static int N, E, start;

	// 각 노드에 연결된 노드에 대한 정보를 담는 배열.
	public static ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();

	// 최단거리 테이블
	public static int[] d = new int[20001];

	// 다익스트라
	public static void dijkstra(int start) {
		// 큐만들기.
		PriorityQueue<Node> pq = new PriorityQueue<>();

		// 시작노드 초기화
		d[start] = 0; // start까지 가는 최단거리는 0
		pq.offer(new Node(start, 0));

		while (!pq.isEmpty()) {
			// 우선순위가 제일 높은 얘(무조건 비용이 제일 낮은 얘) 빼기
			Node node = pq.poll();
			int dist = node.getDistance(); // 비용 제일 낮은애의 거리
			int now = node.getIndex(); // 비용 제일 낮은 얘의 인덱스

			// 현재 노드가 이미 처리된 적 있는 노드면 (다른 최단거리가 있는 애면)
			if (d[now] < dist) {
				continue;
			}

			// 현재 노드와 인접한 다른 노드 확인
			for (int i = 0; i < graph.get(now).size(); i++) {
				int cost = d[now] + graph.get(now).get(i).getDistance();

				if (cost < d[graph.get(now).get(i).getIndex()]) {
					d[graph.get(now).get(i).getIndex()] = cost;
					pq.offer(new Node(graph.get(now).get(i).getIndex(), cost));
				}
			}

		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] tmp = br.readLine().split(" ");
		N = Integer.parseInt(tmp[0]);
		E = Integer.parseInt(tmp[1]);
		start = Integer.parseInt(br.readLine());

		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Node>());
		}

		for (int i = 0; i < E; i++) {
			tmp = br.readLine().split(" ");
			int u = Integer.parseInt(tmp[0]);
			int v = Integer.parseInt(tmp[1]);
			int w = Integer.parseInt(tmp[2]);
			
			graph.get(u).add(new Node(v, w));
		}

		
		Arrays.fill(d, INF);
		
		dijkstra(start);
		
		for (int i =1; i<= N; i++) {
			if (d[i] ==INF) {
				System.out.println("INF");
			} else {
				System.out.println(d[i]);
			}
		}
	}
}