package algorithm_study.day26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * SSAFY 알고리즘 스터디 26일차 - 야구
 * 1시간 반 소요
 * 접근
 * 순열을 생성하고, 순열 마다 최대 득점을 갱신
 * 
 * 경기 진행(세부)
 * 1. 1~3루: 방문 배열로 체크
 * 2. 타순: 인덱스 변수를 사용
 * 
 * 코드 리팩토링 하고 싶은데 시간이 늦어서 지금 상태로는 이게 최선일듯
 * </pre>
 * 
 * @author YoungHwan
 *
 */
public class BOJ_17281 {

	static int N, maxScore;	// 이닝 수, 최대 득점
	static int[] numbers = new int[9]; // 순열 결과(타순)
	static int[][] players; // 타자의 결과
	static boolean[] used = new boolean[9], bases = new boolean[3];	// 방문배열

	// 입출력
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	public static void main(String[] args) throws NumberFormatException, IOException {
		init();
		startGame();
		print();
	}

	// 입력 및 초기화
	private static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		players = new int[N][9];
		maxScore = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				players[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	// 경기 시작
	private static void startGame() {
		// 4번타자는 첫번째타자로 고정
		numbers[3] = 0;
		used[3] = true;
		setSequence(1);
	}

	// 타순 순열 생성 후 결과 값 도출
	private static void setSequence(int depth) {
		if (depth == 9) {
			maxScore = Math.max(maxScore, getScore());
			return;
		}
		for (int i = 0; i < 9; i++) {
			if (used[i]) {
				continue;
			}
			used[i] = true;
			numbers[i] = depth;
			setSequence(depth + 1);
			used[i] = false;
		}
	}

	// 이닝을 수행하며 현재 타순에서의 득점 계산
	private static int getScore() {
		int totalScore = 0;
		// N이닝 수행
		int index = 0; // 타자 번호
		for (int inning = 0; inning < N; inning++) {
			int inningScore = 0;
			int outCount = 0;
			bases = new boolean[3];

			while (outCount < 3) {
				switch (players[inning][numbers[index]]) {
				// 아웃
				case 0:
					outCount++;
					break;
				// 1루타
				case 1:
					if (bases[2]) {
						inningScore++;
						bases[2] = false;
					}
					if (bases[1]) {
						bases[2] = true;
						bases[1] = false;
					}
					if (bases[0]) {
						bases[1] = true;
					}
					bases[0] = true;
					break;
				// 2루타
				case 2:
					if (bases[2]) {
						inningScore++;
						bases[2] = false;
					}
					if (bases[1]) {
						inningScore++;
					}
					if (bases[0]) {
						bases[2] = true;
						bases[0] = false;
					}
					bases[1] = true;
					break;
				// 3루타
				case 3:
					if (bases[2]) {
						inningScore++;
					}
					if (bases[1]) {
						inningScore++;
						bases[1] = false;
					}
					if (bases[0]) {
						inningScore++;
						bases[0] = false;
					}
					bases[2] = true;
					break;
				// 홈런
				case 4:
					if (bases[2]) {
						inningScore++;
						bases[2] = false;
					}
					if (bases[1]) {
						inningScore++;
						bases[1] = false;
					}
					if (bases[0]) {
						inningScore++;
						bases[0] = false;
					}
					inningScore++;
					break;
				}
				index = (index + 1) % 9;
			}
			totalScore += inningScore;
		}
		return totalScore;
	}

	// 출력
	private static void print() {
		System.out.println(maxScore);
	}
}