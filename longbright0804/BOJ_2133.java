package algorithm_study.day40;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * SSAFY 알고리즘 스터디 39일차: 타일 채우기
 * DP - 1시간
 * 솔루션
 */
public class BOJ_2133 {
    static int n;
    static int[] dp;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        // n(1 <= n <= 30)
        int n = Integer.parseInt(br.readLine());

        // n은 1부터 시작하기 때문에 n + 1의 크기를 갖는 배열을 선언한다.
        int[] dp = new int[n + 1];


        // n이 홀수일 경우, 2x1 or 1x2의 타일로 채울 수 없기 때문에 0을 출력하고 return
        if (n % 2 != 0) {
            System.out.println(0);
            return;
        }

        // 타일이 없을 경우 2x1, or 1x2의 타일로 채울 수 있는 경우의 수는 아무것도 채우지 않는 경우이다
        dp[0] = 1;

        // 3x1 타일을 채울 수 있는 경우의 수는 0개이다.
        dp[1] = 0;

        // 3x2 타일을 채울 수 있는 경우의 수는 3개이다.
        dp[2] = 3;

        // n은 홀수가 될 수 없고 짝수만 될 수 있기 때문에 2씩 증가를 한다.
        for (int i = 4; i <= n; i += 2) {
            dp[i] = dp[i - 2] * dp[2];
            for (int j = i - 4; j >= 0; j -= 2) {
                dp[i] = dp[i] + (dp[j] * 2);
            }
        }

        // 결과값 출력
        System.out.println(dp[n]);
    }
}
