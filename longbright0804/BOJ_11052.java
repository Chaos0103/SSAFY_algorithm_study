import java.util.Scanner;

/**
 * SSAFY 알고리즘 스터디 5일차 - DP
 * 23.02.04 카드 구매하기
 * 21:32 시작 - 21:54 종료, 솔루션
 * 
 * ### 틀린 이유
 * 어느 부분을 놓친건지 아직 모르겠음
 * @author YoungHwan
 *
 */
public class BOJ_11052 {
	static int n;
	static int[] p;
	static int[] dp = new int[1001];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		p = new int[n+1];
		for (int i = 1; i <= n; i++) {
			p[i] = sc.nextInt();
		}

		/**
		 * 틀린 코드
		 * 테케는 다 통과하는데 제출하면 오답으로 뜸
		 * 어느 부분을 놓친건지 모르겠음
		 */
//		for (int i = 1; i <= n; i++) {
//			if (n % i == 0) {
//				dp[i] = Math.max(dp[i - 1], p[i] * n / i);
//			} else {
//				dp[i] = Math.max(dp[i - 1], p[i] + p[n - i]);
//			}
//		}

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= i; j++) {
				dp[i] = Math.max(dp[i], dp[i - j] + p[j]);
			}
		}
		
		System.out.println(dp[n]);
	}
}
