package BOJ__12;

import java.io.*;
import java.util.*;

public class BOJ_2138 {
	public static int[] dx = {-1, 0, 1};
    public static int n;
    public static char[] arr1;
    public static char[] arr2;
    public static char[] ansArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        String input1 = br.readLine();
        String input2 = br.readLine();
        arr1 = new char[n];
        arr2 = new char[n];
        ansArr = new char[n];

        for(int i = 0; i < n; i++){
            arr1[i] = input1.charAt(i);
            arr2[i] = arr1[i];
            ansArr[i] = input2.charAt(i);
        }

        int ans = Integer.MAX_VALUE;

        int cnt1 = 0;
        int cnt2 = 0;

        arr1 = swap(arr1, 0);
        cnt1++;

        for(int i = 1; i < n; i++){
            if(arr1[i - 1] != ansArr[i - 1]){
                arr1 = swap(arr1, i);
                cnt1++;
            }
        }

        if(arr1[n - 1] == ansArr[n - 1]){
            ans = Math.min(ans, cnt1);
        }

        for(int i = 1; i < n; i++){
            if(arr2[i - 1] != ansArr[i - 1]){
                arr2 = swap(arr2, i);
                cnt2++;
            }
        }

        if(arr2[n - 1] == ansArr[n - 1]){
            ans = Math.min(ans, cnt2);
        }


        if(ans == Integer.MAX_VALUE){
            System.out.println(-1);
        } else{
            System.out.println(ans);
        }
    }

    public static char[] swap(char[] data, int i){
        for (int d = 0; d < 3; d++) {
            int nx = i + dx[d];
            if (nx < 0 || nx > n - 1) {
                continue;
            }

            if (data[nx] == '0') {
                data[nx] = '1';
            } else {
                data[nx] = '0';
            }
        }
        return data;
    }
}
