package algorithm_study.day38;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SSAFY 알고리즘 스터디 37일차: 캠프준비
 * 1. 부분집합 알고리즘 사용
 * 2. 부분집합이 구해지면 검사
 * 30분 걸렸음
 */
public class BOJ_16938 {
    static int n, l, r, x, result;
    static int[] A, nums;

    public static void main(String[] args) throws IOException {
        init();
        getSubset(0, 0, 0);
        print();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        A = new int[n];
        nums = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static void getSubset(int depth, int count, int sum) {
        if (depth == n) {
            getResult(count, sum);
            return;
        }
        // 현재 값 사용
        nums[count] = A[depth];
        getSubset(depth + 1, count + 1, sum + A[depth]);
        // 현재값 사용 X
        getSubset(depth + 1, count, sum);
    }

    private static void getResult(int count, int sum) {
        if (l <= sum && sum <= r) {
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < count; i++) {
                max = Math.max(max, nums[i]);
                min = Math.min(min, nums[i]);
            }
            if (max - min >= x) {
                result++;
            }
        }
    }

    private static void print() {
        System.out.println(result);
    }
}
