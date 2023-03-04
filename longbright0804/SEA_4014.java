package algorithm_study.day28;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 솔루션 확인했음
 * 저녁 먹고 다시 공부해서 수정할 예정
 * @author YoungHwan
 *
 */
public class SEA_4014 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			// 경사로길이 = X
			int X = Integer.parseInt(st.nextToken());
			int map[][] = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int answer = 0;
			answer = sovle(map, N, X, answer);
			System.out.println("#" + test_case + " " + answer);
		}
	}

	private static int sovle(int[][] map, int N, int X, int answer) {
		// 가로,세로 검증
		int row[] = new int[N];
		int col[] = new int[N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				row[j] = map[i][j];
			}
			if (check(row, N, X)) {
				answer++;
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				col[j] = map[j][i];
			}
			if (check(col, N, X)) {
				answer++;
			}
		}

		return answer;
	}

	private static boolean check(int[] ary, int N, int X) {
//		System.out.println(Arrays.toString(ary));
		boolean is[] = new boolean[N]; // 이미 활주로가 있는지
		for (int i = 0; i < N - 1; i++) {
			if (Math.abs(ary[i] - ary[i + 1]) >= 2) {
//				System.out.println(false);
				return false;
			}

			if (ary[i] - ary[i + 1] == 1) { // 현재 지형의 높이가 1 더높으면
				for (int j = i + 1; j < i + 1 + X; j++) { // 다음 X길이 만큼의 지형은
					if (j >= N || ary[j] != ary[i + 1] || is[j] == true) { // 계속 같아야 한다.
//						System.out.println(false);
						return false;
					}
					is[j] = true;
				}
			} else if (ary[i] - ary[i + 1] == -1) { // 현재 지형의 높이가 1 더낮으면
				for (int j = i; j > i - X; j--) { // 이전 X길이 만큼의 지형은
					if (j < 0 || ary[j] != ary[i] || is[j] == true) { // 계속 같아야 한다.
//						System.out.println(false);
						return false;
					}
					is[j] = true;
				}
			}
		}

//		System.out.println(true);
		return true;
	}

}
