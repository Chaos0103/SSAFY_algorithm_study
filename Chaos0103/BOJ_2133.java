import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        if (n % 2 == 1) {
            System.out.println(0);
            return;
        }

        dp = new int[Math.max(n / 2, 2)];
        dp[0] = 3;
        dp[1] = 11;
        int temp = 0;
        for (int i = 2; i < n / 2; i++) {
            temp += dp[i - 2] * 2;
            dp[i] = dp[i - 1] * 3 + 2 + temp;
        }
        System.out.println(dp[n / 2 - 1]);

    }
}
