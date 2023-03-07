package BOJ_11066;

import java.io.*;
import java.util.*;

/**
 * 2시간 도전 후 솔루션 참고
 * 
 * @author jeon
 *
 * <DP문제> 
 * - 점화식
 * - 메모이제이션
 * 
 * 1. 인접한 파일끼리 합쳐야 한다. 
 * 2. 마지막 연산은 전체 합을 더해야 한다?
 * 
 * 
 */
public class Solution {
	static int N;
	static int[] file;

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		int T = sc.nextInt();
		for (int t = 0; t < T; t++) {
			// 입력 받기
			N = sc.nextInt();
			file = new int[N];

			for (int i = 0; i < N; i++) {
				int size = sc.nextInt();
				file[i] = size;
			}

			sb.append(dp(file)+"\n");
		}
		System.out.println(sb);

	}

	static int dp(int[] file) {
		int size = file.length;
		int[][] dpTable = new int[size][size];
		int[] sum = new int[size];

		sum[0] = file[0];

		// 앞에서부터 순서대로 파일을 합친 후 저장한다.
		// 40 30 30 50 의 경우
		// => 40 70 100 150
		for (int i = 1; i < sum.length; i++) {
			sum[i] = sum[i - 1] + file[i];
		}

		// 앞에서부터 인접한 두 파일을 합친 값
		for (int i = 0; i < file.length-1; i++) {
			dpTable[i][i + 1] = file[i] + file[i + 1];
		}

		// 파일의 개수가 3개 이상이라면,
		for (int g = 2; g < size; g++) {
			for (int i = 0; i + g < size; i++) {
				int j = i + g;
				dpTable[i][j] = Integer.MAX_VALUE;

				// i와 j사이의 값 k
				for (int k = i; k < j; k++) {
					dpTable[i][j] = Math.min(dpTable[i][j], dpTable[i][k] + dpTable[k + 1][j] + sumDist(sum, i, j));
				}
			}
		}
		return dpTable[0][size - 1];
	}

	static int sumDist(int[] sum, int start, int end) {
		if (start == 0) {
			return sum[end];

		} else {
			return sum[end] - sum[start - 1];
		}
	}
}
