package algorithm_study.day39;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * SSAFY 알고리즘 스터디 38일차: 빙산
 * 1. melt(bfs) 수행 -> 빙산의 높이 감소
 * 2. 빙산 높이를 감소시킨 뒤 분리 여부 확인
 * 솔루션 -> 다시 풀어볼 문제
 */
public class BOJ_2573 {
    static class Pos {
        int r;
        int c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Pos{" +
                    "r=" + r +
                    ", c=" + c +
                    '}';
        }
    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int n, m, result;
    static int[][] map;
    static boolean[][] used;
    static Queue<Pos> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // melt 수행
        while (countArea() < 2) {
            if (countArea() == 0) {
                result = 0;
                break;
            }
            melt();
            result++;
        }
        print();
    }

    private static int countArea() {
        used = new boolean[n][m];
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] != 0 && !used[i][j]) {
                    dfs(i, j);
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private static void dfs(int r, int c) {
        used[r][c] = true;
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (isOutOfRange(nr, nc)) continue;
            if (used[nr][nc]) continue;
            if (map[nr][nc] != 0) {
                dfs(nr, nc);
            }
        }
    }

    private static void melt() {
        q = new LinkedList<>();
        used = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] != 0) {
                    used[i][j] = true;
                    q.add(new Pos(i, j));
                }
            }
        }
        while (!q.isEmpty()) {
            Pos now = q.poll();
            int r = now.r;
            int c = now.c;
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (isOutOfRange(nr, nc)) continue;
                if (used[nr][nc]) continue;
                // 인접한 지역이 바다면 높이 1감소
                if (map[nr][nc] == 0) {
                    if (map[r][c] > 0) {
                        map[r][c]--;
                    }
                }
            }
        }
    }

    private static boolean isOutOfRange(int nr, int nc) {
        return nr < 0 || nc < 0 || nr >= n || nc >= m;
    }

    private static void print() {
        System.out.println(result);
    }
}
