package algorithm_study.day36;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SSAFY 알고리즘 스터디 35일차: 가장 긴 바이토닉 부분 수열
 * LIS 알고리즘을 공부해야겠음
 * 솔루션
 */
public class BOJ_11054 {
    static int n, max;
    static int[] arr, dpLR, dpRL;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];
        dpLR = new int[n + 1];
        dpRL = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 왼쪽에서 오른쪽으로 LIS 구하기
        for (int i = 1; i <= n; i++) {
            dpLR[i] = 1;
            for (int j = 1; j < i; j++) {
                if (arr[i] > arr[j]) {
                    dpLR[i] = Math.max(dpLR[j] + 1, dpLR[i]);
                }
            }
        } // 오른쪽에서 왼쪽으로 LIS 구하기
        for (int i = n; i > 0; i--) {
            dpRL[i] = 1;
            for (int j = n; j > i; j--) {
                if (arr[i] > arr[j]) {
                    dpRL[i] = Math.max(dpRL[j] + 1, dpRL[i]);
                }
            }
        }
        // 두 dp 배열 합의 최댓값 탐색
        for (int i = 1; i <= n; i++) {
            max = Math.max(max, dpLR[i] + dpRL[i]);
        }
        System.out.println(max - 1); // 해당 원소 중복되므로 -1
    }
}
