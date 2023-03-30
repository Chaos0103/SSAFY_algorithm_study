package Algorithm_230321;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2133 {
    public static void main(String args[]) throws IOException {
        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int arr[] = new int[N+1];

        arr[0] = 1;
        for(int i = 2; i <= N; i += 2){
            arr[i] = arr[i-2] * 3;
            for(int j = i-4; j >= 0; j -= 2){
                arr[i] += arr[j] * 2;
            }
        }
        
        System.out.println(arr[N]);
    }
}