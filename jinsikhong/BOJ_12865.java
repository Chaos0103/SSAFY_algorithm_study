import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[][] dp = new int[n+1][k+1];
        int[] weight = new int[n+1]; // 가치를 담는 배열
        int[] weightarr = new int[n+1]; //순서대로 물건 개수 넣는 배열
        for(int i = 1; i <= n; i++){
            weightarr[i] = sc.nextInt();
            weight[i] = sc.nextInt();
        }
        // 0 1 2 3
        // 6 4 3 5
//        for(int i = weightarr[0]; i <= k; i++){
//            dp[0][i] = weight[weightarr[0]] + dp[0][i - weightarr[0]];
//        }

        for(int i = 1; i <= n; i++){
            int w = weightarr[i]; // 6 4 3 5
            for(int j = 1; j <= k; j++){
//                dp[i][j] = Math.max(dp[i-1][j], weight[w] + dp[i-1][j-w]);
                if(j < w){
                    dp[i][j] = dp[i-1][j];
                }else{
                    dp[i][j] = Math.max(dp[i-1][j], weight[i] + dp[i-1][j-w]);
                }
            }
        }
//        for(int[] arr : dp){
//            for(int x : arr){
//                System.out.print(x + " ");
//            }
//            System.out.println();
//        }
        System.out.println(dp[n][k]);

    }
    static class Product{
        int weigh, value;
        public Product(int weigh, int value){
            this.weigh = weigh;
            this.value = value;
        }
    }
}
