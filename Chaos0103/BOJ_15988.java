import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int t = sc.nextInt();
        int[] numbers = new int[t];
        int max = 0;
        for (int i = 0; i < t; i++) {
            numbers[i] = sc.nextInt();
            max = Math.max(max, numbers[i]);
        }

        long[] dp = new long[1000001];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        for (int i = 4; i <= max; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]) % 1000000009;
        }

        for (int num : numbers) {
            System.out.println(dp[num]);
        }
    }
}
