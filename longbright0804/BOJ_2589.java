package algorithm_study.day45;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * SSAFY 알고리즘 스터디 44일차: 보물섬
 * 20분
 * @author SSAFY
 *
 */
public class BOJ_2589 {

    static class Pos {
        int r;
        int c;
        int cost;

        public Pos(int r, int c, int cost) {
            super();
            this.r = r;
            this.c = c;
            this.cost = cost;
        }
    }

    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    static int N, M, max;
    static char[][] map;
    static boolean[][] used;

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
        max = Integer.MIN_VALUE;
        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j);
            }
        }
    }

    private static void process() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'L') {
                    used = new boolean[N][M];
                    used[i][j] = true;
                    bfs(new Pos(i, j, 0));
                }
            }
        }
    }

    private static void bfs(Pos start) {
        Queue<Pos> q = new LinkedList<>();
        q.add(start);
        while (!q.isEmpty()) {
            Pos now = q.poll();
            int r = now.r;
            int c = now.c;
            int cost = now.cost;
            max = Math.max(max, cost);

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (isOutOfRange(nr, nc)) continue;
                if (isInvalid(nr, nc)) continue;
                used[nr][nc] = true;
                q.add(new Pos(nr, nc, cost+1));
            }
        }
    }

    private static boolean isOutOfRange(int nr, int nc) {
        return nr < 0 || nc < 0 || nr >= N || nc >= M;
    }

    private static boolean isInvalid(int nr, int nc) {
        return used[nr][nc] || map[nr][nc] == 'W';
    }

    private static void print() {
        System.out.println(max);
    }
}