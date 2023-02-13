import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] cards = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            cards[i] = sc.nextInt();
        }

        int[] dp = new int[1001];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] = Math.max(dp[i], dp[i - j] + cards[j]);
            }
        }

        System.out.println(dp[n]);
    }
}
