package Bj;


import java.util.Scanner;

public class BOJ_2225 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        long[][] dp = new long[k+1][n+1];
        dp[0][0] = 1l;
        for(int i = 1; i <= k; i++){
            for(int j = 0; j <= n; j++){
                for(int z = 0; z <= j; z++){
                    dp[i][j] += dp[i-1][j-z];
                }
                dp[i][j] %= 1000000000;
            }
        }
//        for(long[] arr : dp){
//            for(long x : arr){
//                System.out.print(x  + " ");
//            }
//            System.out.println();
//        }
        System.out.println(dp[k][n]);
    }
}
