package Algorithm_230315;

import java.io.*;
import java.util.*;

public class BOJ_2294 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        int[] count = new int[k + 1];
        for (int i = 0; i < k + 1; i++) {
            count[i] = 100001;
        }
        count[0] = 0;

        int[] coin = new int[n];
        for (int i = 0; i < n; i++) {
            coin[i] = sc.nextInt();
        }

        for (int i = 0; i < n; i++) {
            for (int j = coin[i]; j <= k; j++) {
                count[j] = Math.min(count[j], count[j - coin[i]] + 1);
            }
        }

        System.out.println(count[k] == 100001 ? -1 : count[k]);
    }
}