package BOJ_11048;

import java.util.*;
import java.io.*;

/*
 * DP 연습
 * 50분 / 자율
*/
public class Main {
	static int N, M;
	static int[][] sugars;
	static int[][] results;

	public static void dp() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (j > 0 && j < M) {
					int tmp = sugars[i][j] + results[i][j - 1];
					results[i][j] = Math.max(results[i][j], tmp);

				}
				if (i > 0 && i < N) {
					int tmp = sugars[i][j] + results[i - 1][j];
					results[i][j] = Math.max(results[i][j], tmp);
				}
				if (j > 0 && j < M && i > 0 && i < N) {
					int tmp = sugars[i][j] + results[i - 1][j - 1];
					results[i][j] = Math.max(results[i][j], tmp);
				}

			}
		}
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		sugars = new int[N][M];
		results = new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sugars[i][j] = sc.nextInt();
				results[i][j] = sugars[i][j];
			}
		}
		dp();
		
		System.out.println(results[N-1][M-1]);

	}
}
