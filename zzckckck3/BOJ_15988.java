package BOJ_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_15988 {
    static final int divNum = 1000000009;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int [] dp = new int[1000001];
        dp[1] = 1; 
        dp[2] = 2; 
        dp[3] = 4;
        
        int testCase = Integer.parseInt(br.readLine());
        int[] ans = new int[testCase];
        for(int i = 0; i < testCase; i++){
            int n = Integer.parseInt(br.readLine());
            for(int j = 4; j <= n; j++){
                dp[j] = ((dp[j - 3] + dp[j - 2]) % divNum + dp[j - 1]) % divNum;
            }
            ans[i] = dp[n];
        }
        
        for (int i = 0; i < testCase; i++) {
			System.out.println(ans[i]);
		}
    }
}