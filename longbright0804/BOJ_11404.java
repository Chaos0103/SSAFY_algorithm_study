import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11404 {
	static final int INF = (int) 1e9;
	static int n, m;
	static int[][] graph;
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 도시의 개수, 버스의 개수 입력, 그래프 생성
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		graph = new int[n + 1][n + 1];
		// 자기 자신으로 가는 거리는 0으로 초기화
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				graph[i][j] = INF;
				if (i == j) {
					graph[i][j] = 0;
				}
			}
		}
		// 버스 정보 입력
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken()); // 출발도시 A
			int b = Integer.parseInt(st.nextToken()); // 도착도시 B
			int c = Integer.parseInt(st.nextToken()); // 비용 C
			// a b 가 중복 입력 될 수 있으므로 기존 값과 비교하여 둘 중 작은 값으로 초기화
			graph[a][b] = Math.min(graph[a][b], c);
		}
		// 플로이드 워셜 알고리즘 수행
		for (int k = 1; k <= n; k++) {
			for (int a = 1; a <= n; a++) {
				for (int b = 1; b <= n; b++) {
					graph[a][b] = Math.min(graph[a][b], graph[a][k] + graph[k][b]);
				}
			}
		}

		// 결과 출력
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (graph[i][j] == INF) {
					System.out.print("0 ");
				} else {
					System.out.print(graph[i][j] + " ");
				}
			}
			System.out.println();
		}
	}
}
