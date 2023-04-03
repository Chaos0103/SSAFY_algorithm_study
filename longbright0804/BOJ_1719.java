package day26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 11:10 시작 -> 11:51 종료(풀이)
 * 모든 쌍 최단 경로 -> 플로이드 알고리즘 적용
 * n, m 입력
 * a, b, c 입력
 * 이외에는 INF 로 초기화, 자기 자신은 0
 *
 * 1. 정점 k 를 거쳐가는 비용이 최소일 때, 해당 정점을 가장 먼저 방문해야함
 *    map[i][j] = map[i][k];
 *    - 문제점 : 이미 다른 곳을 거쳐간 경우, 해당 정점에 대한 값을 모름
 *    -> 2번을 통해 해결되었음
 * 2. i 에서 j 로 가는 경로 : 연결되어있는 경우 이미 처리 되어있어야함
 *    -> 정보를 입력 받을 때 같이 입력
 *    -> i 에서 j 로 가는 경로가 더 짧은 경우에 대한 처리 필요 없음(어차피 j 가 저장되어있음)
 */
public class BOJ_1719 {
    static final int INF = (int) 1e9;
    static int N, M;
    static int[][] dp, map;

    public static void main(String[] args) throws IOException {
        init();
        floyd();
        printArray(map);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dp = new int[N + 1][N + 1];
        map = new int[N + 1][N + 1];
        // 배열 초기화
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                dp[i][j] = INF;
            }
        }
        for (int i = 1; i <= N; i++) {
            dp[i][i] = 0;
        }
        // 그래프 정보 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            dp[a][b] = cost;
            dp[b][a] = cost;
            map[a][b] = b;
            map[b][a] = a;
        }
    }

    private static void floyd() {
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    // k 를 거쳐서 가는 비용
                    int temp = dp[i][k] + dp[k][j];
                    if (dp[i][j] > temp) {
                        dp[i][j] = temp;
                        map[i][j] = map[i][k];
                    }
                }
            }
        }
    }

    private static void printArray(int[][] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) sb.append("- ");
                else sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
