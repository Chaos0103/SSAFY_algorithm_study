package day31;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SSAFY 알고리즘 스터디 50일차: 레이저 통신
 */
public class BOJ_6087 {
    static class Pos {
        int r;
        int c;
        int cnt;
        int direction;

        public Pos(int r, int c, int cnt, int direction) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.direction = direction;
        }
    }
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int W, H;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new char[W][H];
        for (int i = 0; i < W; i++) {
            String s = br.readLine();
            for (int j = 0; j < H; j++) {
                map[i][j] = s.charAt(j);
            }
        }
        /* 어찌합니까 */
    }
}
