package algorithm_study.day44;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * SSAFY 알고리즘 스터디 44일차: 쉬운 계단 수
 * 솔루션
 * 2차원 DP 배열 사용은 접근했으나 더 나아가질 못함
 */
public class BOJ_10844 {

    private static final long MOD = 1000000000;
    static int N;
    static long[][] dp;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new long[N+1][10];
        // 한자리 수는 전부 1개 만 존재할 수 있음
        for (int i = 1; i < 10; i++) {
            dp[1][i] = 1;
        }
        // 각 지랏수에 대하여
        for (int i = 2; i <= N; i++) {
            // 0~9 의 수가 자릿값
            for (int j = 0; j < 10; j++) {
                // 0인 경우 다음 자릿수의 자릿값은 1만 올 수 있음
                if (j == 0) {
                    dp[i][0] = dp[i-1][1] % MOD;
                }
                // 9인 경우 다음 자릿수의 자릿값은 8만 올 수 있음
                else if (j == 9) {
                    dp[i][9] = dp[i-1][8] % MOD;
                }
                // 그 외의 경우 이전 자릿수의 자릿값 +1, -1 의 합이 됨
                else {
                    dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1]) % MOD;
                }
            }
        }

        // N번째 자릿수의 모든 자릿값을 더함
        long result = 0;
        for(int i = 0; i < 10; i++) {
            result += dp[N][i];
        }
        System.out.println(result % MOD);
    }
}
