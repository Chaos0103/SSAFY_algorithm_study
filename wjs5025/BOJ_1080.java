import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static int[][] from;
	static int[][] to;
	static int cnt = 0;

	public static void change(int startI, int startJ) {
		cnt++;
		for (int i = startI; i < startI + 3; i++) {
			for (int j = startJ; j < startJ + 3; j++) {
				from[i][j] = (from[i][j] == 0) ? 1 : 0;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tmp = br.readLine().split(" ");

		N = Integer.parseInt(tmp[0]);
		M = Integer.parseInt(tmp[1]);

		// 입력 받기
		from = new int[N][M];
		to = new int[N][M];

		for (int i = 0; i < N; i++) {
			tmp = br.readLine().split("");

			for (int j = 0; j < M; j++) {
				from[i][j] = Integer.parseInt(tmp[j]);
			}
		}

		for (int i = 0; i < N; i++) {
			tmp = br.readLine().split("");

			for (int j = 0; j < M; j++) {
				to[i][j] = Integer.parseInt(tmp[j]);
			}
		}

		// 결과 처리하기

		// 처음부터 같으면 0 처리
		
		if (N < 3 || M < 3) { // 만약 둘중하나라도 3보다 작으면
			for (int i =0; i<N; i++) {
				if(!Arrays.equals(from[i], to[i])){
					cnt = -1;
					break;
				}
				cnt = 0;
			}
		} else {
			for (int i = 0; i <= N - 3; i++) {
				// 해당 줄의 내용이 같으면 진행
				if (Arrays.equals(from[i], to[i]))
					continue;
				// 내용이 다르면 탐색하면서 바꿔준다.
				for (int j = 0; j <= M - 3; j++) {
					if (from[i][j] == to[i][j])
						continue;
					change(i, j);
				}
				// j 탐색 후에도 다르면 -1 처리
				if (!Arrays.equals(from[i], to[i])) {
					cnt = -1;
					break;
				}
			}
			// 다 바꿨는데 행렬이 다르면 -1 처리
			for (int i = 0; i < N; i++) {
				if (!Arrays.equals(from[i], to[i])) {
					cnt = -1;
					break;
				}
			}
		}
		System.out.println(cnt);
	}
}