package BJ;

import java.util.Scanner;

public class BOJ_2133 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		if(n % 2 != 0) {
			System.out.println(0);
		}else {
			int[] dp = new int[31];
			dp[2] = 3;
			dp[0] = 1;
			for (int i = 4; i <= 30; i += 2) {
                dp[i] = dp[i - 2] * dp[2];
 
                for (int j = i - 4; j >= 0; j -= 2) {
                	dp[i] += (dp[j] * 2);
                }
            }
			System.out.println(dp[n]);
		}
	}
}
