import java.io.*;
import java.util.*;

/**
 * https://velog.io/@wotj7687/BOJ-1507-%EA%B6%81%EA%B8%88%ED%95%9C-%EB%AF%BC%ED%98%B8-Java
 * 플로이드 워샬 응용 Too.. hard...
 * 
 * 1시간 정도 도전 후 솔루션 참고했습니다.
 * 추후에 다시 공부하겠습니다.
 * 
 * @author Jeon
 *
 */
public class BOJ_1507 {
	static int N;
	static int[][] graph;
	static int[][] copy_graph;
	static int sum = 0;

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int answer = 0;

		N = sc.nextInt();

		graph = new int[N][N];
		copy_graph = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				graph[i][j] = sc.nextInt();
				copy_graph[i][j] = graph[i][j];
			}
		}

		loop1: for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					if (i == j || j == k || i == k) {
						continue;
					}

					if (graph[j][k] == graph[j][i] + graph[i][k]) {
						copy_graph[j][k] = 0;
					}

					if (graph[j][k] > graph[j][i] + graph[i][k]) {
						answer = -1;
						break loop1;
					}
				}
			}
		}

		for (int[] i : copy_graph) {
			for (int j : i) {
				sum += j;
			}
		}

		if (answer != -1) {
			System.out.println(sum / 2);
		} else {
			System.out.println(-1);
		}
	}

	static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(graph[i][j] + " ");
			}
			System.out.println();
		}
	}
}