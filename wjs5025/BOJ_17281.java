import java.io.*;
import java.util.*;

public class BOJ_17281 {
	static int N;
	static int[][] results;
	static boolean[] visited = new boolean[9];
	static int[] permutation = new int[9];
	static ArrayList<int[]> permutations = new ArrayList<>();
	static boolean[] RU;
	static int maxScore = Integer.MIN_VALUE;

	public static void getPermutation(int idx) {
		if (idx == 9) {
			permutation[3] = 0;
			permutations.add(permutation.clone());
			return;
		}
		for (int i = 0; i < 9; i++) {
			if (visited[i] || i == 0)
				continue;
			visited[i] = true;
			permutation[idx] = i;
			if (idx == 2) {
				getPermutation(idx + 2);
			} else {
				getPermutation(idx + 1);
			}
			visited[i] = false;
		}
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		results = new int[N][9];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 9; j++) {
				results[i][j] = sc.nextInt();
			}
		}

		getPermutation(0);

		for (int[] p : permutations) {

//		int[] p = { 3, 7, 2, 0, 5, 1, 6, 8, 4 };

		RU = new boolean[3];

		int ining = 0;
		int score = 0;
		int nowHeaterIdx = 0;
		int outCnt = 0;

		while (ining < N) {
			outCnt = 0;
			Arrays.fill(RU, false);
//			System.out.println("이닝 스타트 nowHeaterIdx" + p[nowHeaterIdx % 9]);
			while (true) {
//				System.out.println("outcnt " + outCnt);
				if (outCnt == 3) {
					ining++;
//					System.out.println("out 다음이닝");
					break;
				}
//				System.out.println("이번에 뭐쳤냐" + p[(nowHeaterIdx) % 9] + "번 선수가" + results[ining][p[nowHeaterIdx % 9]]);

				// 0이면 아웃. 아웃카운트 늘려주기
				if (results[ining][p[nowHeaterIdx % 9]] == 0) {
					outCnt++;
				} else if (results[ining][p[nowHeaterIdx % 9]] == 1) {
					// 1이면 안타. 배열 돌면서 요소 있으면 한칸씩 인덱스 밀어주기
					// arraylist[0]에 요소 넣기
					for (int i = 2; i >= 0; i--) {
						if (i == 2) {
							if (RU[i]) {
								score++;
								RU[i] = false;
							}
						} else {
							RU[i + 1] = RU[i];
						}
					}
					RU[0] = true;
				} else if (results[ining][p[nowHeaterIdx % 9]] == 2) {
					// 2이면 2루타. 배열 돌면서 요소 있으면 두 칸씩 인덱스 밀어주기
					for (int i = 2; i >= 0; i--) {
						if (i >= 1) {
							if (RU[i]) {
								score++;
								RU[i] = false;
							}
						} else {
							RU[i + 2] = RU[i];
							RU[i] = false;
						}
					}
					RU[1] = true;
				} else if (results[ining][p[nowHeaterIdx % 9]] == 3) {
					// 3이면 3루타. 배열 돌면서 요소 있으면 세 칸씩 인덱스 밀어주기
					for (int i = 2; i >= 0; i--) {
						if (RU[i]) {
							score++;
							RU[i] = false;
						}
					}
					RU[2] = true;
				} else if (results[ining][p[nowHeaterIdx % 9]] == 4) {
					// 4이면 홈런. 배열 돌면서 요소 있으면 개수 세어서 score에 넣기
					for (int r = 2; r >= 0; r--) {
						if (RU[r]) {
							score++;
							RU[r] = false;
						}
					}
					score++; // 본인도 들어가니까
				}
//				System.out.println(Arrays.toString(RU));
				nowHeaterIdx++;
			}
		}

//		System.out.println("현재 순열에서의 스코어" + score);
		maxScore = Math.max(maxScore, score);
		
		}
//		print();

		System.out.println(maxScore);
//		System.out.println(Arrays.toString(test));
	}

	static int[] test = new int[9];

	static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(results[i][j] + " ");
			}
			System.out.println();
		}
	}
}
