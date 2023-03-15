import java.util.Scanner;

/*
백준 2293 동전 1
dp
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] coin = new int[101];
        int[] dp = new int[100001];
        int n = sc.nextInt();
        int k = sc.nextInt();
        for(int i = 1; i <= n; i++){
            coin[i] = sc.nextInt();
        }
        dp[0] = 1;

        for(int i = 1; i <= n; i++){
            for(int j = coin[i]; j <= k; j++){
                dp[j] = dp[j] + dp[j - coin[i]];
//                System.out.println(dp[j]);
            }
        }
        System.out.println(dp[k]);
    }
}
