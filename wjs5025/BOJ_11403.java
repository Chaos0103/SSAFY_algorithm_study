import java.io.*;
import java.util.*;

/**
 * 아직 완벽히 외우지는 못해서,
 * 적어놓았던거 참고해가면서 작성했습니다.
 * 
 * 최대한 코드를 안보려했고,
 * 방법만 보고 작성하면서 연습했습니다!!
 * 
 */
public class Main {
	public static int N;
	public static int[][] graph;
	public static final int INF = (int) 1e9;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		graph = new int[N][N];

		// 우선 무한대로 초기화
		for (int i = 0; i < N; i++) {
			Arrays.fill(graph[i], INF);
		}

		// 입력받기.
		for (int i = 0; i < N; i++) {
			String[] tmp = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				// 간선이 있으면
				if (Integer.parseInt(tmp[j]) != 0) {
					// 간선 넣기
					graph[i][j] = Integer.parseInt(tmp[j]);
				}

			}
		}

		for (int k = 0; k < N; k++) {
			for (int a = 0; a < N; a++) {
				for (int b = 0; b < N; b++) {
					graph[a][b] = Math.min(graph[a][b], graph[a][k] + graph[k][b]);
				}
			}
		}
		
		StringBuilder sb= new StringBuilder();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (graph[i][j] == (int) 1e9)
					graph[i][j] = 0;
				else
					graph[i][j] = 1;
				sb.append(graph[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}