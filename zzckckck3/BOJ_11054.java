package Algorithm_230315;

import java.io.*;
import java.util.*;
 
public class BOJ_11054 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
 
        int [] arr = new int[n];
 
        int [] dp = new int[n+1];
        int [] dp2 = new int[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
 
        int result = 0;
        for(int i = 0; i < n; i++){
            dp[i] = 1;
            for(int j = 0; j < i; j++){
                if(arr[i] > arr[j] && dp[j] >= dp[i]){
                    dp[i] = dp[j] + 1;
                }
            }
        }
 
 
        for(int i = n-1; i >= 0; i--){
            dp2[i] = 1;
            for(int j = n-1; j > i; j--){
                if(arr[i] > arr[j] && dp2[j] >= dp2[i]){
                    dp2[i] = dp2[j] + 1;
                }
            }
        }
 
        for(int i = 0; i<n; i++){
            result = Math.max(dp[i] + dp2[i]-1, result);
        }
 
        System.out.println(result);
    }
}