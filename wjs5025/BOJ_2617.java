package BOJ_2617;

import java.io.*;
import java.util.*;

public class Main {
	static int[][] map;
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] tmp = br.readLine().split(" ");
		N = Integer.parseInt(tmp[0]);
		M = Integer.parseInt(tmp[1]);

		map = new int[N + 1][N + 1];

		for (int i = 0; i < M; i++) {
			tmp = br.readLine().split(" ");
			int a = Integer.parseInt(tmp[0]);
			int b = Integer.parseInt(tmp[1]);

			map[a][b] = 1; // a가 b보다 큼 a<-b
			map[b][a] = -1; // b가 a보다 작음 b->a
		}

		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (map[j][k] != 0 && map[i][k] == map[k][j])
						map[i][j] = map[i][k];
				}
			}
		}

		int half = (N / 2) + 1;
		int[] big = new int[N + 1];
		int[] small = new int[N + 1];
		// 뒤에 오는 조건, 앞에 오는 조건의 개수를 각각 세어 저장
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {

				if (map[i][j] == 1)
					big[i]++;

				if (map[i][j] == -1)
					small[i]++;

			}
		}
		int ans = 0;
		// 조건이 총구슬의 반이 넘으면 답의 개수를 늘려준다
		for (int i = 1; i <= N; i++) {
			if (big[i] >= half)
				ans++;
			if (small[i] >= half)
				ans++;
		}
		System.out.println(ans);

	}

	static void print() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}
