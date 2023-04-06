package algorithm_study.day49;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * SSAFY 알고리즘 스터디 48일차: 용액
 * 솔루션
 * 또.
 */
public class BOJ_2467 {

    static int N;
    static int[] arr, result;

    public static void main(String[] args) throws IOException {
        init();
        process();
        printResult();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        result = new int[2];
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
    }

    private static void process() {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            int start = i + 1;
            int end = N-1;
            min = binarySearch(min, i, start, end);
        }
    }

    private static int binarySearch(int min, int i, int start, int end) {
        while (start <= end) {
            int mid = (start + end) / 2;
            int sum = arr[i] + arr[mid];
            int absSum = Math.abs(sum);
            if (absSum < min) {
                min = absSum;
                result[0] = arr[i];
                result[1] = arr[mid];
            }

            if (sum < 0) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return min;
    }

    private static void printResult() {
        for (int i : result) {
            System.out.print(i + " ");
        }
    }
}
