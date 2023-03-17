import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int n, k;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        dp = new int[201][201];
        for (int i = 1; i <= k; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= k; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = (dp[i][j - 1] + dp[i - 1][j]) % 1_000_000_000;
            }
        }

        bw.write(dp[k][n] + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
