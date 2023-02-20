package algorithm_study.day19;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * SSAFY 알고리즘 스터디 19일차 - 도시 분할 계획
 * 30분
 * @author YoungHwan
 *
 */
public class BOJ_1647 {
	static int n, m, answer; // 집의 개수, 길의 개수, 유지비 합의 최솟값
	static int[] parent;
	static ArrayList<Edge> edgeList = new ArrayList<>(); // 간선 정보 리스트

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 도시의 개수, 길의 개수 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		// 부모 배열 생성 및 값 초기화
		parent = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			parent[i] = i;
		}

		// 간선 정보 입력
		int a, b, cost;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			// 노드 번호, 유지비 입력
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());
			edgeList.add(new Edge(a, b, cost));
		}

		// 간선리스트 정렬
		Collections.sort(edgeList);

		/**
		 * 크루스칼 알고리즘 수행 
		 * 1. 정렬된 간선 리스트를 살펴본다. 
		 * 2. 사이클이 발생하지 않은 경우 집합에 포함 시킨다. 
		 * 3. 모든 간선에 대해수행한다.
		 * 4. 누적된 비용의 합이 곧 최소 비용이다.
		 */
		// 사이클 여부 판별
		int max = Integer.MIN_VALUE;
		for (int index = 0; index < edgeList.size(); index++) {
			a = edgeList.get(index).getNodeA();
			b = edgeList.get(index).getNodeB();
			cost = edgeList.get(index).getDistance();
			// 사이클이 발생하지 않은 경우 집합에 포함
			if (findParent(a) != findParent(b)) {
				unionParent(a, b);
				if (cost > max) max = cost;
				answer += cost;
			}
		}
		// 2개의 MST를 만드는 것 -> 완성된 MST 에서 최댓값 간선을 제거하기
		System.out.println(answer-max);
	}

	private static void unionParent(int a, int b) {
		// 루트노드 찾아오기
		a = findParent(a);
		b = findParent(b);
		// 둘 중 작은 값이 부모노드가 됨
		if (a < b)
			parent[b] = a;
		else
			parent[a] = b;
	}

	private static int findParent(int x) {
		// 루트 노드인 경우 바로 반환
		if (x == parent[x])
			return x;
		// 루트 노드가 아니라면 찾을 때까지 재귀 호출
		return parent[x] = findParent(parent[x]);
	}
}

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object) 거리기준 정렬
	 */
	@Override
	public int compareTo(Edge o) {
		return Integer.compare(distance, o.distance);
	}
}