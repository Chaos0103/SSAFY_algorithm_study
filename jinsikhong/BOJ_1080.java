package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BOJ_1080 {
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        int[][] result = new int[n][m];
        for(int i = 0; i < n; i++){
            String num = br.readLine();
            char[] num_arr = num.toCharArray();
            for(int j = 0; j< m; j++){
                arr[i][j] = num_arr[j]-'0';
            }
        }
        for(int i = 0; i < n; i++){
            String num = br.readLine();
            char[] num_arr = num.toCharArray();
            for(int j = 0; j< m; j++){
                result[i][j] = num_arr[j]-'0';
            }
        }
//        for(int[] ar : arr){
//            for(int x : ar){
//                System.out.print(x + " ");
//            }
//            System.out.println();
//        }

        int cnt = 0;
        for(int i = 0; i+2 < n; i++){
            for(int j = 0; j+2 < m; j++){
                if(arr[i][j] != result[i][j]){ //같지 않으면 swap
                    swap(i, j);
                    cnt++;
                }
            }
        }
        for(int i = 0; i < n; i++){
            for(int j = 0; j< m; j++){
                if(result[i][j] != arr[i][j]){
                    cnt = -1;
                    break;
                }
            }
        }
        System.out.println(cnt);
    }
    public static void swap(int i, int j){
        for(int k = 0; k < 3; k++){
            for(int z = 0; z < 3;z++){
                if(arr[i+k][j+z] == 1){
                    arr[i+k][j+z] = 0;
                }else{
                    arr[i+k][j+z] = 1;
                }
            }
        }
    }
}
