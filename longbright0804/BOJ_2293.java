package algorithm_study.day35;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SSAFY 알고리즘 스터디 34일차 : 동전1
 * 솔루션
 */
public class BOJ_2293 {
    static int N, K;
    static int[] coins, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        coins = new int[N];
        dp = new int[K+1];
        for (int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        dp[0] = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 1; j <= K; j++) {
                if (j >= coins[i])
                    dp[j] += dp[j - coins[i]];
            }
        }
        System.out.println(dp[K]);
    }
}
