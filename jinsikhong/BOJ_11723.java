import java.util.Scanner;

/**
 * 문제 : 2 x n 타일링2 / DP
 * 풀이시간 : 20분
 */
public class BOJ_11723 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;

        for(int i = 2; i <= n; i++){
            dp[i] = (dp[i-1] + dp[i-2] * 2) % 10007 ;
        }
        System.out.println(dp[n]);
    }
}
