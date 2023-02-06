import java.util.Scanner;

/**
 * SSAFY 알고리즘 스터디 5일차 - DP
 * 23.02.05 오르막 수
 * 15:20 시작 -> 1시간 소요
 * 
 * ### 틀린 이유
 * @author YoungHwan
 *
 */
public class BOJ_11057 {
	static int n;
	static int[][] dp;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		dp = new int[n+1][10];
		// dp 테이블 초기화
		for (int i = 0; i < 10; i++) {
			dp[0][i] = 1;
		}
		
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j < 10; j++) {
				for (int k = j; k < 10; k++) {
					dp[i][j] += dp[i - 1][k];
					dp[i][j] %= 10007;			// 누적합 계산 후 모듈러 연산을 해줘야함
				}
			}
		}
		
		System.out.println(dp[n][0] % 10007);
	}
}
