package Algorithm_230305;

import java.util.*;
import java.io.*;

public class BOJ_11066 { 

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int testCase = 0; testCase < T; testCase++) {
            int K;
            int[] novel;
            int[] sum;
            int[][] dp;

            K = Integer.parseInt(br.readLine());
            novel = new int[K + 1];
            dp = new int[K + 1][K + 1];
            sum = new int[K + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= K; i++) {
                novel[i] = Integer.parseInt(st.nextToken());
                sum[i] = sum[i - 1] + novel[i];
            }

            for (int n = 1; n <= K; n++) {
                for (int i = 1; i + n <= K; i++) {
                    int to = i + n;
                    dp[i][to] = Integer.MAX_VALUE;
                    for (int j = i; j < to; j++) {
                        dp[i][to] = Math.min(dp[i][to], dp[i][j] + dp[j + 1][to] + sum[to] - sum[i - 1]);
                    }
                }
            }

            System.out.println(dp[1][K]);
        }

    }
}
