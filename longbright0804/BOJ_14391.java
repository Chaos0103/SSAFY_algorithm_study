package algorithm_study.day50;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14391 {
    static int N, M, max;
    static int[][] map;
    static boolean[][] used;

    public static void main(String[] args) throws IOException {
        init();
        dfs(0,0);
        print();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        max = Integer.MIN_VALUE;
        map = new int[N][M];
        used = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }
    }

    private static void dfs(int row, int col) {
        // 선택 완료 -> 최댓값 갱신
        if (row == N) {
            updateMax(getSum());
            return;
        }
        // 다음행으로 이동
        if (col == M) {
            dfs(row+1, 0);
            return;
        }
        // 가로, 세로 선택 가지 뻗기
        used[row][col] = true;
        dfs(row, col+1);
        used[row][col] = false;
        dfs(row, col+1);
    }

    private static void updateMax(int sum) {
        max = Math.max(max, sum);
    }

    private static int getSum() {
        int sum = 0;
        // 가로 계산
        sum = getRowSum(sum);
        // 세로 숫자 계산
        sum = getColSum(sum);
        return sum;
    }

    private static int getRowSum(int sum) {
        for (int i = 0; i < M; i++) {
            int temp = 0;
            for (int j = 0; j < N; j++) {
                if (used[j][i]) {
                    temp *= 10;
                    temp += map[j][i];
                } else {
                    sum += temp;
                    temp = 0;
                }
            }
            sum += temp;
        }
        return sum;
    }

    private static int getColSum(int sum) {
        for (int i = 0; i < N; i++) {
            int temp = 0;
            for (int j = 0; j < M; j++) {
                if (!used[i][j]) {
                    temp *= 10;
                    temp += map[i][j];
                } else {
                    sum += temp;
                    temp = 0;
                }
            }
            sum += temp;
        }
        return sum;
    }

    private static void print() {
        System.out.println(max);
    }
}
