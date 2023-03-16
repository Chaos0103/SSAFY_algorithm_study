import java.io.*;
import java.util.*;

/**
 * 약간의 솔루션 
 * 
 * 점화식 도출
 * 표를 그려 생각해보기
 * 
 * @author jeon
 *
 */
public class Main {
	static int N, K;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		K = sc.nextInt();

		dp = new int[N + 1][K + 1];

		for (int i = 1; i <= N; i++) {
			dp[i][1] = 1;
		}

		for (int i = 1; i <= K; i++) {
			dp[1][i] = i;
		}

		for (int i = 2; i <= N; i++) {
			for (int j = 2; j <= K; j++) {
				dp[i][j] = (dp[i - 1][j] + dp[i][j - 1])%1000000000;
			}
		}
		
		System.out.println(dp[N][K]);
	}
}

// 0부터 N까지의 정수 K개를 더해서
// 그 합이 N이 되는 경우?
