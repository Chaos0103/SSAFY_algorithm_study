import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SSAFY 알고리즘 스터디 20일차 - 여행가자
 * 15분
 * @author SSAFY
 *
 */
public class BOJ_1976 {
	static int n, m;
	static int[] plan, parent;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 도시의 개수, 여행계획 입력
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		// 여행계획 배열, 도시 정보 배열 생성
		plan = new int[m + 1];
		map = new int[n + 1][n + 1];

		// 부모 테이블 생성 및 자기 자신으로 초기화
		parent = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			parent[i] = i;
		}

		// 도시의 정보 입력
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// 두 도시가 연결되어있을 경우 Union 연산 수행
				if (map[i][j] == 1) {
					unionParent(i, j);
				}
			}
		}

		// 여행 계획 입력
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			plan[i] = Integer.parseInt(st.nextToken());
		}

		// 두 노드의 부모가 같으면 -> 연결되어있는것(사이클 발생)
		for (int i = 0; i < m - 1; i++) {
			if (findParent(plan[i]) != findParent(plan[i+1])) {
				System.out.println("NO");
				return;
			}
		}
		System.out.println("YES");

	}

	private static int findParent(int x) {
		if (x == parent[x])
			return x;
		return findParent(parent[x]);
	}

	private static void unionParent(int a, int b) {
		a = findParent(a);
		b = findParent(b);

		if (a < b) {
			parent[b] = a;
		} else {
			parent[a] = b;
		}
	}
}
