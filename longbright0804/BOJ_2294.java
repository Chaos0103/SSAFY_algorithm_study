package algorithm_study.day36;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * SSAFY 알고리즘 스터디 35일차: 동전2
 * 점화식 dp[k] = min(dp[k], dp[k - coin[i]] + 1)
 * 약 1시간
 * @author SSAFY
 */
public class BOJ_2294 {
    static final int INF = 100001;
    static int N, K;
    static int[] coin, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        coin = new int[N];
        dp = new int[K + 1];
        Arrays.fill(dp, INF);
        for (int i = 0; i < N; i++) {
            coin[i] = Integer.parseInt(br.readLine());
        }

        dp[0] = 0;
        for (int i = 0; i < N; i++) {
            for (int k = coin[i]; k <= K; k++) {
                dp[k] = Math.min(dp[k], dp[k - coin[i]] + 1);
            }
        }
        if (dp[K] == INF)
            System.out.println(-1);
        else
            System.out.println(dp[K]);
    }

}
