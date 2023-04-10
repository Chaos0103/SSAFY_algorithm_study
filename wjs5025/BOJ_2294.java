
import java.io.*;
import java.util.*;

/**
 * 뭔가 솔루션을 안보고 할 수 있을 것 같았는데
 * 크나큰 착각이었습니다.
 */
public class Main {
	static int n, k;
	static int[] coins;
	static int[] dp;
	static int INF = (int) 1e9;

	// 조합?
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		k = sc.nextInt();

		coins = new int[n];
		dp = new int[k + 1];
		Arrays.fill(dp, INF);
		dp[0] = 0;

		for (int i = 0; i < n; i++) {
			coins[i] = sc.nextInt();
		}

		// 점화식 도출
//		dp[i] = min(dp[i], dp[i-동전의크기])
		for (int i = 0; i < n; i++) {
			for (int j = coins[i]; j <= k; j++) {
				dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
			}
		}

		if (dp[k] == INF)
			System.out.println(-1);
		else
			System.out.println(dp[k]);
	}
}
