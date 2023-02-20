package algorithm_study.day19;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SSAFY 알고리즘 스터디 19일차 - 집합의 표현
 * 서로소 집합 알고리즘을 이용하여 구현
 * 10분
 * @author YoungHwan
 *
 */
public class BOJ_1717 {
	static int n, m;
	static int[] parent;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		// n,m 입력 및 집합 배열 생성
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		parent = new int[n + 1];
		// 부모 테이블 초기화(자기자신으로 초기화)
		for (int i = 1; i <= n; i++) {
			parent[i] = i;
		}

		// 연산 입력
		int op = 0, a = 0, b = 0;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			op = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());

			// Union 연산 수행
			if (op == 0) {
				unionParent(a, b);
			}
			// Find 연산 수행
			else {
				if (findParent(a) == findParent(b)) {
					sb.append("YES\n");
				} else {
					sb.append("NO\n");
				}
			}
		}
		System.out.println(sb);
	}
	// Union 연산
	private static void unionParent(int a, int b) {
		// 루트노드를 찾음
		a = findParent(a);
		b = findParent(b);
		// 둘 중 작은 값이 부모노드가 됨
		if (a < b)
			parent[b] = a;
		else
			parent[a] = b;

	}
	// Find 연산
	private static int findParent(int x) {
		// 루트 노드인 경우 바로 반환
		if (parent[x] == x)
			return x;
		// 루트 노드가 아닌 경우 찾을 때까지 재귀호출
		return parent[x] = findParent(parent[x]);
	}
}
