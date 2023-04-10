import java.io.*;
import java.util.*;

public class BOJ_2096 {
	static int N;
	static int[][] nums;
	static int[][] lastMin;
	static int[][] lastMax;
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();

		nums = new int[N][3];

		lastMin = new int[N][3];
		lastMax = new int[N][3];

		// 배열 초기화
		for (int i = 0; i < N; i++) {
			Arrays.fill(lastMin[i], Integer.MAX_VALUE);
			Arrays.fill(lastMax[i], Integer.MIN_VALUE);
		}

		// 입력받기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 3; j++) {
				nums[i][j] = sc.nextInt();
				if (N == 1) {
					max = Math.max(max, nums[i][j]);
					min = Math.min(min, nums[i][j]);
				}
			}
		}

		// 탐색
		for (int i = 0; i < N - 1; i++) {
			for (int j = 0; j < 3; j++) {
				if (i == 0) {
					lastMin[i][j] = nums[i][j];
					lastMax[i][j] = nums[i][j];
				}
				for (int k = j - 1; k <= j + 1; k++) {
					if (k < 0 || k >= 3)
						continue;
					lastMin[i + 1][k] = Math.min(lastMin[i + 1][k], lastMin[i][j] + nums[i + 1][k]);
					lastMax[i + 1][k] = Math.max(lastMax[i + 1][k], lastMax[i][j] + nums[i + 1][k]);
					if (i == N - 2) {
						min = Math.min(min, lastMin[i + 1][k]);
						max = Math.max(max, lastMax[i + 1][k]);
					}
				}
			}
		}

		System.out.println(String.format("%d %d", max, min));
	}
}
