import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] dp = new int[k+1];
        int[] coin= new int[n+1];
        for(int i = 1; i <= n; i++){
            coin[i] = sc.nextInt();
        }
        for(int i =1; i<=k; i++){
            dp[i] = 100001;
        }
        for(int i = 1; i <= n; i++){
            for(int j = coin[i]; j <= k; j++){
                dp[j] = Math.min(dp[j], dp[j - coin[i]] + 1);
//                        j / coin[i] + dp[j % coin[i]]);




//                dp[j] = Math.min(dp[j], j / coin[i] + dp[j % coin[i]]);
//                System.out.print(dp[j] + " ");
            }
//            System.out.println();
        }
        if(dp[k] > 100000){
            System.out.println(-1);
        }else{
            System.out.println(dp[k]);
        }
    }
}
