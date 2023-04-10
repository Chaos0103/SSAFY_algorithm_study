import java.io.*;
import java.util.*;

public class BOJ_1719 {
	static int n, m;
	static int[][] graph;
	static int[][] matrix;
	static int INF = (int) 1e9;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] tmp = br.readLine().split(" ");
		n = Integer.parseInt(tmp[0]);
		m = Integer.parseInt(tmp[1]);

		graph = new int[n + 1][n + 1];
		matrix = new int[n + 1][n + 1];

		for (int i = 1; i <= n; i++) {
			Arrays.fill(graph[i], INF);
		}

		for (int i = 1; i <= n; i++) {
			graph[i][i] = 0;
			matrix[i][i] = 0;
		}

		for (int i = 0; i < m; i++) {
			tmp = br.readLine().split(" ");
			int s = Integer.parseInt(tmp[0]);
			int e = Integer.parseInt(tmp[1]);
			int t = Integer.parseInt(tmp[2]);
			graph[s][e] = t;
			graph[e][s] = t;

			// 양방향이니까
			matrix[s][e] = e;
			matrix[e][s] = s;
		}

		// 플로이드 워샬 점화식
		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					int temp = graph[i][k] + graph[k][j];
					// k 를 거쳐가는 경우가 더 짧은 경우
					if (graph[i][j] > temp) {
						graph[i][j] = temp;
						matrix[i][j] = matrix[i][k];
					}
				}
			}
		}
		printMatrix();
	}

	static void printMatrix() {
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (i == j) {
					System.out.print("-" + " ");
				} else
					System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
}
