import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int n, result;
    private static int[] arr;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[2][n + 1];
        for (int i = 1; i <= n; i++) {
            dp[0][i] = 1;
            for (int j = 1; j < i; j++) {
                if (arr[i] > arr[j]) {
                    dp[0][i] = Math.max(dp[0][i], dp[0][j] + 1);
                }
            }
        }

        for (int i = n; i > 0; i--) {
            dp[1][i] = 1;
            for (int j = n; j > i; j--) {
                if (arr[i] > arr[j]) {
                    dp[1][i] = Math.max(dp[1][i], dp[1][j] + 1);
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            result = Math.max(result, dp[0][i] + dp[1][i]);
        }

        System.out.println(result - 1);
    }
}
