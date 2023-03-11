package algorithm_study.day32;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <pre>
 * SSAFY 알고리즘 스터디 31일차: 내리막길
 * 로직은 설명을 듣고 짰음
 * 구현만 내가 함
 * 사실상 솔루션
 * </pre>
 *
 * @author SSAFY
 */
public class BOJ_1520 {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static int N, M, result;
    static int[][] map, dp;

    public static void main(String[] args) throws IOException {
        init();
        process();
        print();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        dp = new int[M][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            Arrays.fill(dp[i], -1); // dp 테이블 초기화
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void process() {
        dp[M-1][N-1] = 1;
        result = dfs(0, 0);
    }

    private static int dfs(int r, int c) {
        // 이미 방문한 지역이면 종료
        if (dp[r][c] != -1) {
            return dp[r][c];
        }
        // 4방 탐색
        dp[r][c] = 0;
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            // 범위를 벗어나면 탐색 x
            if (isOutOfRange(nr, nc)) {
                continue;
            }
            // 갈 수 있으면 dfs 수행
            if (map[r][c] > map[nr][nc]) {
                dp[r][c] += dfs(nr, nc);
            }
        }
        return dp[r][c];
    }

    private static boolean isOutOfRange(int nr, int nc) {
        return nr < 0 || nc < 0 || nr >= M || nc >= N;
    }

    private static void print() {
        System.out.println(dp[0][0]);
    }
}
