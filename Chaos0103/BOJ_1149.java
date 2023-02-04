import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[][] dp = new int[n][3];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                dp[i][j] = sc.nextInt();
            }
        }

        int[][] index = {{1, 2}, {0, 2}, {0, 1}};
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                dp[i][j] = Math.min(dp[i - 1][index[j][0]], dp[i - 1][index[j][1]]) + dp[i][j];
            }
        }

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            result = Math.min(result, dp[n - 1][i]);
        }

        System.out.println(result);
    }
}
