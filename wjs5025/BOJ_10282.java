import java.io.*;
import java.util.*;

public class BOJ_10282 {
	static int N, D, C;
	static ArrayList<ArrayList<Pos>> graph;
	static int[] distances;
	static int INF = (int) 1e9;
	static int sum = 0;
	static int cnt = 0;

	static class Pos implements Comparable<Pos> {
		int idx;
		int dist;

		@Override
		public String toString() {
			return "Pos [idx=" + idx + ", dist=" + dist + "]";
		}

		public Pos(int idx, int dist) {
			super();
			this.idx = idx;
			this.dist = dist;
		}

		@Override
		public int compareTo(Pos o) {
			return this.dist - o.dist;
		}
	}

	static void djikstra(Pos start) {
		PriorityQueue<Pos> pq = new PriorityQueue<>();
		pq.offer(start);
		distances[start.idx] = 0;

		while (!pq.isEmpty()) {
			Pos current = pq.poll();
			ArrayList<Pos> child = graph.get(current.idx);

			// 이미 거리 갱신되었으면 패스 ~
			if (distances[current.idx] < current.dist)
				continue;

			cnt++;
			sum = current.dist;
			// 거리갱신 안됐으면 해야지.
			for (int i = 0; i < child.size(); i++) {
				int cost = distances[current.idx] + child.get(i).dist;

				if (distances[child.get(i).idx] > cost) {
					distances[child.get(i).idx] = cost;
					pq.offer(new Pos(child.get(i).idx, cost)); // 다음 놈 추가
				}
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int tc = Integer.parseInt(br.readLine());

		for (int t = 0; t < tc; t++) {
			String[] tmp = br.readLine().split(" ");
			N = Integer.parseInt(tmp[0]);
			D = Integer.parseInt(tmp[1]);
			C = Integer.parseInt(tmp[2]);
			sum = 0;
			cnt = 0;
		
			graph = new ArrayList<>();
			distances = new int[N + 1];

			Arrays.fill(distances, INF);

			// 그래프 초기화
			for (int i = 0; i <= N; i++) {
				graph.add(new ArrayList<>());
			}

			for (int i = 0; i < D; i++) {
				tmp = br.readLine().split(" ");
				int a = Integer.parseInt(tmp[0]);
				int b = Integer.parseInt(tmp[1]);
				int s = Integer.parseInt(tmp[2]);

				graph.get(b).add(new Pos(a, s));
			}

			djikstra(new Pos(C, 0));

			System.out.println(String.format("%d %d", cnt, sum));
		}

	}

	static void print() {
		System.out.println();
		for (int i = 0; i <= N; i++) {
			System.out.println(graph.get(i));
		}
	}
}
