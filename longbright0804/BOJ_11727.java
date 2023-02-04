/**
 * SSAFY 알고리즘 스터디 5일차 - DP
 * 23.02.03 2*N 타일링 2
 * 1시간 소요, 솔루션
 * 
 * ### 틀린 이유
 * - 점화식 : 2 * dp[i-2] + dp[i-1] + dp[i-3] 까지 접근했는데
 * - i-3 은 고려할 요소가 아니었음
 * - 그리고 10007 연산을 미리 해줬어야함.
 * @author YoungHwan
 *
 */
import java.util.Scanner;

public class BOJ_11727 {
	static int n;
	static int[] dp = new int[1001];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		dp[0] = 0;
		dp[1] = 1;
		dp[2] = 3;

		for (int i = 3; i <= n; i++) {
			dp[i] = (2 * dp[i - 2] + dp[i - 1]) % 10007;
		}
		System.out.println(dp[n]);
	}
}