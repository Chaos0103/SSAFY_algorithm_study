package algorithm_study.day34;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SSAFY 알고리즘 스터디 33일차 : 궁금한 민호
 * 1시간
 * 접근
 * WOW!
 * 솔루션 봤음
 */
public class BOJ_1507 {
    private static final int INF = 9999;
    static int N, result;
    static int[][] map, dist;
    static boolean[][] check;

    public static void main(String[] args) throws IOException {
        init();
        if (process()) return;
        getResult();
        print();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dist = new int[N][N];
        check = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                dist[i][j] = Integer.parseInt(st.nextToken());
                map[i][j] = dist[i][j];
            }
        }
    }

    private static boolean process() {
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    // 거쳐가지 않는 간선은 제거하면 안됨
                    if (i == j || i == k || k == j) continue;
                    // 이미 플로이드 워셜 알고리즘이 수행된 상태인데 또 수행해야하는 경우는 모순
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        System.out.println(-1);
                        return true;
                    }
                    // 거쳐가는 지점을 통해 최단거리가 초기화된 부분이 있다면 해당 간선 제거
                    if (dist[i][j] == dist[i][k] + dist[k][j]) {
                        map[i][j] = INF;
                    }
                }
            }
        }
        return false;
    }

    private static void getResult() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] != INF && i != j && !check[i][j]) {
                    result += map[i][j];
                    check[i][j] = check[j][i] = true;
                }
            }
        }
    }

    private static void print() {
        System.out.println(result);
    }
}
