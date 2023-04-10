package day31;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * SSAFY 알고리즘 스터디 50일차: 소수경로
 */
public class BOJ_1963 {
    static class Number {
        int num;
        int cnt;

        public Number(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }

    static int T, source, target, min;
    static boolean[] prime, used;
    static final int MAX_VAL = 10000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            init(br);
            process(sb);
        }
        print(sb);
    }

    private static void init(BufferedReader br) throws IOException {
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        source = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());
        min = Integer.MAX_VALUE;
        prime = new boolean[MAX_VAL + 1];
        used = new boolean[MAX_VAL + 1];
    }

    private static void process(StringBuilder sb) {
        primeNumberChecked(prime);
        bfs(source);
        setResult(sb);
    }

    static void primeNumberChecked(boolean[] b) {
        b[0] = true; // 소수면 false, 소수가 아니면 true
        b[1] = true;

        for (int i = 2; i < Math.sqrt(MAX_VAL); i++) {
            if (!b[i]) {
                // 2를 예를 들면 2를 제외한 2의 배수는 2로 나뉘어지니 소수가 아님
                for (int j = i * i; j <= MAX_VAL; j += i) {
                    b[j] = true;
                }
            }
        }
    }

    private static void bfs(int start) {
        Queue<Number> q = new LinkedList<>();
        used[start] = true;
        q.add(new Number(start, 0));
        while (!q.isEmpty()) {
            Number now = q.poll();
            int nowNum = now.num;
            int cnt = now.cnt;
            if (nowNum == target) {
                min = Math.min(min, cnt);
            }
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 10; j++) {
                    if (i == 0 && j == 0) continue;
                    int nextNum = change(now.num, i, j);
                    if (prime[nextNum] || used[nextNum]) continue;
//                    System.out.println("n: " + i + " nowNum: " + nowNum + " nextNum: " + nextNum + " cnt: " + cnt);
                    used[nextNum] = true;
                    q.add(new Number(nextNum, cnt + 1));
                }
            }
        }
    }

    private static void setResult(StringBuilder sb) {
        if (min != Integer.MAX_VALUE) sb.append(min).append("\n");
        else sb.append("impossible\n");
    }

    private static int change(int num, int i, int j) {
        StringBuilder sb = new StringBuilder(String.valueOf(num));
        sb.setCharAt(i, (char) (j + '0'));
        return Integer.parseInt(sb.toString());
    }

    private static void print(StringBuilder sb) {
        System.out.println(sb);
    }
}
