package algorithm_study.day37;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * SSAFY 알고리즘 스터디 36일차: 합분해
 * 솔루션
 */
public class BOJ_2225 {
    static final int MOD = (int) 1e9;
    static int n, k;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        dp = new int[k+1][n+1];
        Arrays.fill(dp[1], 1);
        for (int i = 1; i <= k; i++) dp[i][0] = 1;

        for (int i = 2; i <= k; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
                dp[i][j] %= MOD;
            }
        }

        System.out.println(dp[k][n]);
    }
}
