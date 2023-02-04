package BOJ_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11057 {
	public static void main(String[] args) throws NumberFormatException, IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		
		int [][] dp = new int[n+1][10];
        for(int i = 0; i <= 9; i++){
            dp[1][i] = 1;
        }

        for(int i=2 ; i<=n; i++){
            for(int j=0; j<=9; j++){
                for(int k=0; k<=j; k++){
                    dp[i][j] += dp[i-1][k] % 10007;
                }
            }
        }

        int ans = 0;

        for(int i=0; i<= 9; i++){
        	ans += dp[n][i] % 10007;
        }
        
        System.out.print(ans % 10007);

    }
}

