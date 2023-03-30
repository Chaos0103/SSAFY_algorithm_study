/*
 * 해설 참고(테이블)
 * https://moonsbeen.tistory.com/18
 * */

import java.io.*;
import java.util.*;

public class BOJ_10844 {
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();

		dp = new int[N + 1][10];

		Arrays.fill(dp[1], 1);
		dp[1][0] = 0;

		for (int i = 2; i <= N; i++) {
			for (int j = 0; j < 10; j++) {
				if (j == 0) {
					dp[i][j] = dp[i - 1][j + 1];
				} else if (j == 9) {
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % 1000000000;
				}
			}
		}
		int sum = 0;
		for (int i = 0; i <= 9; i++) {
			sum = (sum + dp[N][i]) % 1000000000;
		}
		System.out.println(sum);
	}
}
