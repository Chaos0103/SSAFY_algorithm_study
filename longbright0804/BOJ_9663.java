package algorithm_study.day31;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <pre>
 * SSAFY 알고리즘 스터디 31일차: N-Queen
 * 
 * 접근
 * 1. 재귀
 * 2. 퀸을 배치한 뒤 그것이 가능한지 확인
 * </pre>
 * 
 * @author YoungHwan
 *
 */
public class BOJ_9663 {

	static int N, result;
	static int[] col;

	public static void main(String[] args) throws NumberFormatException, IOException {
		init();
		setQueen(1);
		print();
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		col = new int[N + 1];
	}

	// 각 행에 배치
	private static void setQueen(int row) {
		// 이전에 놓은 것 확인
		if (!isValid(row - 1)) {
			return;
		}
		// 퀸을 다 놓은 경우
		if (row > N) {
			result++;
			return;
		}
		// 퀸 배치(가지)
		for (int i = 1; i <= N; i++) {
			col[row] = i;
			setQueen(row + 1);
		}
	}

	private static boolean isValid(int row) {
		for (int i = 1; i < row; i++) {
			if (col[i] == col[row] || Math.abs(col[i] - col[row]) == row - i) {
				return false;
			}
		}
		return true;
	}

	private static void print() {
		System.out.println(result);
	}
}
