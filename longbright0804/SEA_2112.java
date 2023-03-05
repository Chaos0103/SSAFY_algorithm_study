package algorithm_study.day28;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <pre>
 * 접근
 * 알고리즘: 조합, 중복순열 사용
 * 14:30 ~ 16:00
 * 16:00 ~ 16:25 리팩토링 간단하게 수행
 * 
 * 솔루션
 * 조합, 중복순열 사용 X
 * </pre>
 * 
 * @author YoungHwan
 *
 */
public class SEA_2112_Sol {

	static int T, D, W, K, minCount; // 테스트 케이스 개수, 필름 크기, 통과 기준, 약품 투입 최소 횟수
	static int[] comb, perm; // 조합, 순열 결과 배열
	static boolean isDone; // 검사 통과 여부 확인
	static int[][] film, temp; // 필름, 복사본

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder result = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			init(br);
			process();
			setResult(result, tc);
		}
		print(result);
	}

	private static void init(BufferedReader br) throws IOException {
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine());
		D = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		minCount = Integer.MAX_VALUE;
		film = new int[D][W];
		temp = new int[D][W];
		for (int i = 0; i < D; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				film[i][j] = Integer.parseInt(st.nextToken());
				temp[i][j] = film[i][j];
			}
		}
	}

	private static void process() {
		// 약품을 투여하지 않아도 되는 경우
		if (check()) {
			minCount = 0;
		}
		// 약품을 투여해야하는 경우
		else {
			injection(0,0);
		}
	}

	private static void injection(int depth, int count) {
		if (count > minCount) {
			return;
		}
		if (depth == D) {
			if (check()) {
				minCount = Math.min(minCount, count);
			}
			return;
		}
		
		// 약품 주입을 하지 않는 경우
		injection(depth+1, count);
		
		// A 약품 주입
		injectA(depth);
		injection(depth+1, count+1);
		
		// B약품 주입
		injectB(depth);
		injection(depth+1, count+1);
		
		// 배열 초기화
		initFilm(depth);
	}

	private static void injectA(int depth) {
		Arrays.fill(film[depth], 0);
	}

	private static void injectB(int depth) {
		Arrays.fill(film[depth], 1);
	}

	private static void initFilm(int depth) {
		for (int c = 0; c < W; c++) {
			film[depth][c] = temp[depth][c];
		}
	}

	private static boolean check() {
		boolean isValid = true;
		for (int col = 0; col < W; col++) {
			// 값 갱신
			isValid = true;
			int num = film[0][col];
			int count = 1;
			for (int row = 1; row < D; row++) {
				if (num != film[row][col]) {
					num = film[row][col];
					count = 1;
				} else {
					count++;
				}
				// 연속되는 동일한 특성이 K개 있으면 유효함
				if (count == K) {
					break;
				}
			}
			// 연속되는 동일한 특성이 K개가 없으면 유효하지 않음
			if (count < K) {
				isValid = false;
				break;
			}
		}
		return isValid;
	}

	private static void setResult(StringBuilder result, int tc) {
		result.append("#").append(tc).append(" ").append(minCount).append("\n");
	}

	private static void print(StringBuilder result) {
		System.out.println(result);
	}
}
