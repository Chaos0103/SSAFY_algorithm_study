package algorithm_study.day45;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SSAFY 알고리즘 44일차: 리모컨
 * 솔루션 봤음(1시간)
 */
public class BOJ_1107 {

    static final int MAX = 999999;
    static int N, M;
    static boolean[] button;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        button = new boolean[10];
        if (M != 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                int num = Integer.parseInt(st.nextToken());
                button[num] = true;
            }
        }

        if (N == 100) {
            System.out.println(0);
            return;
        }

        int min = Math.abs(100 - N);
        for (int i = 0; i <= MAX; i++) {
            int cnt = check(i);
            if (cnt != 0) {
                min = Math.min(min, Math.abs(N - i) + check(i));
            }
        }
        System.out.println(min);
    }

    private static int check(int n) {
        int cnt = 0;
        if (n == 0) {
            if (button[n]) return 0;
            else return 1;
        }
        while (n > 0) {
            if (button[n % 10]) return 0;
            cnt++;
            n /= 10;
        }
        return cnt;
    }
}
