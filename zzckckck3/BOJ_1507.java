package Algorithm_230314;

import java.io.*;
import java.util.*;

public class BOJ_1507 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][n];
        int[][] arrCopy = new int[n][n];

        for (int i = 0; i < n; i++) {
            st  = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                arrCopy[i][j] = arr[i][j];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (i == j || j == k || i == k) {
                        continue;
                    }

                    if (arr[j][k] == arr[j][i] + arr[i][k]) {
                        arrCopy[j][k] = 0;
                    }

                    if (arr[j][k] > arr[j][i] + arr[i][k]) {
                        System.out.println(-1);
                        System.exit(0);
                    }
                }
            }
        }

        int result = 0;

        for (int i = 0; i < arrCopy.length; i++) {
			for (int j = 0; j < arrCopy[i].length; j++) {
				result += arrCopy[i][j];
			}
		}
        
        System.out.println(result / 2);
    }
}