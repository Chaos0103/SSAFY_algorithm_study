import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SSAFY 알고리즘 스터디 14일차 - 경로 찾기
 * 30분
 * @author YoungHwan
 *
 */
public class BOJ_11403 {
	static final int INF = (int) 1e9;
	static int n;
	static int[][] graph;
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		graph = new int[n][n];
		
		// 자기 자신으로 가는 거리는 0으로 초기화
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				if (i == j | graph[i][j] == 0) {
					graph[i][j] = INF;
				}
			}
		}
		
		// 플로이드 워셜 알고리즘 수행
		for (int k = 0; k < n; k++) {
			for (int a = 0; a < n; a++) {
				for (int b = 0; b < n; b++) {
					graph[a][b] = Math.min(graph[a][b], graph[a][k] + graph[k][b]);
				}
			}
		}
		// 결과 출력
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (graph[i][j] == INF) {
					System.out.print("0 ");
				} else {
					System.out.print("1 ");
				}
			}
			System.out.println();
		}
	}
	
}
