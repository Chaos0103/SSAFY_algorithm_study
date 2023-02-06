package BJ;

import java.util.Scanner;
/*
 * 풀이시간 : 20분
 * 문제 : 매 test case마다 dp를 반복문돌면서 채우면 outofindex뜸.
 * -> test case 반복문 돌기 전에 dp 배열을 완성O(n)이니 시간복잡도는 상관없음.
 *  
 */

public class BOJ_15988 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for (int tc = 0; tc < t; tc++) {
			int n = sc.nextInt();
			
			long[] dp = new long[1000001];
			dp[1] = 1;
			dp[2] = 2;
			dp[3] = 4;
			for(int i = 4; i <= 1000000; i++) {
				dp[i] = (dp[i-1] + dp[i-2] + dp[i-3]) % 1000000009;
			}
			
			
			System.out.println(dp[n]);
		}
	}
}
