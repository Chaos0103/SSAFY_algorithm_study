import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * SSAFY 알고리즘 스터디 20일차 - 네트워크 연결
 * 15분
 * @author SSAFY
 *
 */
public class BOJ_1922 {
	static int n, m, answer; // 컴퓨터의 개수, 선의 개수, 최소비용(최종 결과)
	static int[] parent; // 부모 테이블
	static ArrayList<Edge> edges = new ArrayList<>(); // 간선 리스트

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 컴퓨터의 개수, 선의 개수 입력
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());

		// 부모 테이블 생성 및 초기화
		parent = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			parent[i] = i;
		}

		// 간선 정보 입력
		StringTokenizer st = null;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			edges.add(new Edge(a, b, c));
		}

		// 크루스칼 알고리즘 수행
		Collections.sort(edges); // 간선의 길이를 기준으로 정렬 후 수행
		for (int i = 0; i < edges.size(); i++) {
			int nodeA = edges.get(i).getNodeA();
			int nodeB = edges.get(i).getNodeB();
			int dist = edges.get(i).getDistance();
			if (findParent(nodeA) != findParent(nodeB)) {
				unionParent(nodeA, nodeB);
				answer += dist;
			}
		}
		System.out.println(answer);
	}

	// Find 연산
	private static int findParent(int x) {
		// 루트 노드일 경우 그냥 그대로 반환
		if (x == parent[x])
			return x;
		// 루트 노드가 아닐 경우 루트 노드를 찾을 때까지 재귀 수행
		return parent[x] = findParent(parent[x]);
	}

	// Union 연산
	private static void unionParent(int a, int b) {
		a = findParent(a);
		b = findParent(b);
		// 둘 중 작은 것이 부모가 됨
		if (a < b) {
			parent[b] = a;
		} else {
			parent[a] = b;
		}
	}
}

// 간선 정보 클래스
class Edge implements Comparable<Edge> {
	private int nodeA;
	private int nodeB;
	private int distance;

	/**
	 * @param nodeA
	 * @param nodeB
	 * @param distance
	 */
	public Edge(int nodeA, int nodeB, int distance) {
		super();
		this.nodeA = nodeA;
		this.nodeB = nodeB;
		this.distance = distance;
	}

	/**
	 * @return the nodeA
	 */
	public int getNodeA() {
		return nodeA;
	}

	/**
	 * @return the nodeB
	 */
	public int getNodeB() {
		return nodeB;
	}

	/**
	 * @return the distance
	 */
	public int getDistance() {
		return distance;
	}

	@Override
	public int compareTo(Edge o) {
		return Integer.compare(distance, o.distance);
	}
}