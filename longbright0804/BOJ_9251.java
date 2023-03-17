package algorithm_study.day37;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * SSAFY 알고리즘 스터디 36일차: LCS
 * 최장 공통 부분 수열
 * LCS 알고리즘 사용
 * 솔루션
 */
public class BOJ_9251 {
    static String a, b;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        a = br.readLine();
        b = br.readLine();
        int lenA = a.length();
        int lenB = b.length();
        dp = new int[lenA+1][lenB+1];

        for (int i = 1; i <= lenA; i++) {
            for (int j = 1; j <= lenB; j++) {
                if (a.charAt(i-1) == b.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        System.out.println(dp[lenA][lenB]);
    }
}
