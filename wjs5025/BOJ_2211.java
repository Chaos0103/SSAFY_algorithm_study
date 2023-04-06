import java.io.*;
import java.util.*;

class Node implements Comparable<Node> {
	int num;
	int dist;

	@Override
	public String toString() {
		return "Node [num=" + num + ", dist=" + dist + "]";
	}

	public Node(int num, int dist) {
		super();
		this.num = num;
		this.dist = dist;
	}

	@Override
	public int compareTo(Node o) {
		return this.dist - o.dist;
	}
}

public class BOJ_2211 {
	static int N, M;
	static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
	static int[] distance;
	static int INF = (int) 1e9;
	static int cnt = -1;
	static int[] path;

	static void diskstra(Node start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		distance[start.num] = 0;
		pq.offer(start);

		while (!pq.isEmpty()) {
			Node current = pq.poll();

			ArrayList<Node> child = graph.get(current.num);
			if (distance[current.num] < current.dist)
				continue;

			for (int i = 0; i < child.size(); i++) {
				int dist = current.dist + child.get(i).dist;

				if (distance[child.get(i).num] > dist) {
					distance[child.get(i).num] = dist;
					pq.offer(new Node(child.get(i).num, dist));
					path[child.get(i).num] = current.num;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] tmp = br.readLine().split(" ");
		N = Integer.parseInt(tmp[0]);
		M = Integer.parseInt(tmp[1]);

		distance = new int[N + 1];
		path = new int[N + 1];
		Arrays.fill(distance, INF);

		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < M; i++) {
			tmp = br.readLine().split(" ");
			int a = Integer.parseInt(tmp[0]);
			int b = Integer.parseInt(tmp[1]);
			int c = Integer.parseInt(tmp[2]);

			graph.get(a).add(new Node(b, c));
			graph.get(b).add(new Node(a, c));
		}
		diskstra(new Node(1, 0));
		System.out.println(N-1);
		for (int i = 2; i <= N; i++)
			System.out.println(i + " " + path[i]);

	}

	static void print() {
		for (int i = 1; i <= N; i++) {
			System.out.println(graph.get(i));
		}
	}
}
