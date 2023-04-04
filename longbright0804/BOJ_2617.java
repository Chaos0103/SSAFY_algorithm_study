package day26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2617 {
    static final int INF = 101;
    static int N, M, result;
    static int[][] dist;
    static int[] rowCnt, colCnt;

    public static void main(String[] args) throws IOException {
        init();
        process();
        print();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        rowCnt = new int[N+1];
        colCnt = new int[N+1];
        dist = new int[N+1][N+1];

        for (int[] row : dist) {
            Arrays.fill(row, INF);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            dist[a][b] = 1; // 큰 구슬 표시
        }
    }

    private static void process() {
        floyd();
        count();
        getResult();
    }

    private static void floyd() {
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (i == j) continue;
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
    }

    private static void count() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j || dist[i][j] == INF) continue;
                rowCnt[i]++;
                colCnt[j]++;
            }
        }
    }

    private static void getResult() {
        int half = (N+1) / 2;
        for (int i = 1; i <= N; i++) {
            if (rowCnt[i] >= half) result++;
            if (colCnt[i] >= half) result++;
        }
    }

    private static void print() {
        System.out.println(result);
    }
}
