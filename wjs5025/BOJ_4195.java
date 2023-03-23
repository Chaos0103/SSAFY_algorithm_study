import java.io.*;
import java.util.*;

// https://youngest-programming.tistory.com/458 참고

// map으로 인덱스랑 이름 매핑시키기
public class Main {
	static int N, M;
	static int[] parent; // 반드시 map String 키값으로만 접근할 것.
	static HashMap<String, Integer> map;
	static ArrayList<Edge> edges;
	static StringBuilder sb = new StringBuilder();
	static int[] friends;

	static class Edge {
		int nodeA;
		int nodeB;

		public Edge(int a, int b) {
			this.nodeA = a;
			this.nodeB = b;
		}

		@Override
		public String toString() {
			return "Edge [nodeA=" + nodeA + ", nodeB=" + nodeB + "]";
		}

	}

	// find
	static int findParent(int target) {
		if (target == parent[target]) {
			return target;
		}
		return parent[target] = findParent(parent[target]);
	}

	// union
	static int unionParent(int nodeA, int nodeB) {
		int a = findParent(nodeA);
		int b = findParent(nodeB);

		if (a != b)
			if (a < b) {
				parent[b] = a;
				friends[a] += friends[b];
				return friends[a];
			} else {
				parent[a] = b;
				friends[b] += friends[a];
				return friends[b];
			}

		return friends[a];
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		// 부분집합의 크기를 구해야하므로,
		// 부모테이블의 값이 같은 얘들이 몇명인지 찾아주면됨.

		N = sc.nextInt();

		// tc별 수행
		for (int tc = 0; tc < N; tc++) {

			M = sc.nextInt();
			int idx = 0;
			edges = new ArrayList<>();
			map = new HashMap<>();

			for (int i = 0; i < M; i++) {
				String a = sc.next();
				String b = sc.next();

				// 맵 정보가 없다면 매핑
				if (!map.containsKey(a)) {
					map.put(a, idx++);
				}
				if (!map.containsKey(b)) {
					map.put(b, idx++);
				}
				edges.add(new Edge(map.get(a), map.get(b)));
			}

			// 부모테이블 생성
			parent = new int[idx];

			// 부모테이블 초기화
			for (int i = 0; i < idx; i++) {
				parent[i] = i;
			}

			friends = new int[idx];
			Arrays.fill(friends, 1); // 최초 친구 수는 기본값으로 한명이다

			// edges 순회
			for (int i = 0; i < M; i++) {
				if (findParent(edges.get(i).nodeA) != findParent(edges.get(i).nodeB)) {
					sb.append(unionParent(edges.get(i).nodeA, edges.get(i).nodeB)).append("\n");
				} else {
					sb.append(unionParent(edges.get(i).nodeA, edges.get(i).nodeB)).append("\n");
				}
			}
		}

		System.out.println(sb);
	}
}