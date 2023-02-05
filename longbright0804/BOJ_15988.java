import java.util.Scanner;

/**
 * SSAFY 알고리즘 스터디 5일차 - DP
 * 23.02.05 1, 2, 3 더하기 3
 * 16:50 시작
 * 1시간 소요
 * 
 * ### 버벅인 이유
 * n=6 인 경우를 검증하던 중에 숫자를 잘못 세어버리는 바람에
 * 점화식 맞춰놓고 30분을 헤맴
 * 앞으로는 나를 믿어보는 것도 좋을듯
 * @author YoungHwan
 *
 */
public class BOJ_15988 {
	static int n, t;
	static long[] dp;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		t = sc.nextInt();
		for (int tc = 0; tc < t; tc++) {
			n = sc.nextInt();
			dp = new long[n+1];
			dp[1] = 1;
			dp[2] = 2;
			dp[3] = 4;
			
			for (int i = 4; i <= n; i++) {
				dp[i] = (dp[i-1] + dp[i-2] + dp[i-3]) % 1000000009;
			}
			System.out.println(dp[n]);
		}
	}
}
