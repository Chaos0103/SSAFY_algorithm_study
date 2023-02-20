import java.util.*;
import java.io.*;

/**
 * (솔루션)
 * 외우고 싶지만 이해도 너무나도 벅차요...
 * 
 */
public class Main {
	public static final int INF = (int) 1e9; // 무한을 의미하는 값으로 10억을 설정
	// 노드의 개수(N), 간선의 개수(M)
	// 노드의 개수는 최대 100개라고 가정.

	public static int n, m;
	public static int[][] graph = new int[101][101];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		m = sc.nextInt();

		// 100 X 100 최단거리 테이블을 모두 무한으로 초기화
		for (int i = 0; i < 101; i++) {
			Arrays.fill(graph[i], INF);
		}

		// 자기 자신에게서 자기자신으로 가는 비용은 0으로 초기화
		// 2중포문돌려서 a==b 이면 0으로 만들기
		for (int a = 1; a <= n; a++) {
			for (int b = 1; b <= n; b++) {
				if (a == b)
					graph[a][b] = 0;
			}
		}

		// 각 간선에 대한 정보를 입력 받아, 그 값으로 초기화
		for (int i = 0; i < m; i++) {
			// A에서 B로 가는 비용은 C라고 설정
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();
			graph[a][b] = Math.min(graph[a][b], c); 
		}

		// 점화식에 따라 플로이드 워셜 알고리즘을 수행
		// 점화식 : Dab = min (Dab, Dak + Dkb) ***
		// 3중포문 점화식
		for (int k = 1; k <= n; k++) {
			for (int a = 1; a <= n; a++) {
				for (int b = 1; b <= n; b++) {
					graph[a][b] = Math.min(graph[a][b], graph[a][k] + graph[k][b]);
				}
			}
		}

		// 수행된 결과를 출력
		for (int a = 1; a <= n; a++) {
			for (int b = 1; b <= n; b++) {
				// 도달할 수 없는 경우, 무한(INFINITY)이라고 출력
				if (graph[a][b] == INF) {
					System.out.print("0 ");
				}
				// 도달할 수 있는 경우 거리를 출력
				else {
					System.out.print(graph[a][b] + " ");
				}
			}
			System.out.println();
		}
	}
}