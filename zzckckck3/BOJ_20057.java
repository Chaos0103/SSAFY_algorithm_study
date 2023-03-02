package Algorithm_230303;

import java.io.*;
import java.util.*;

public class BOJ_20057 {
    static int N;
    static int[][] map;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {-1, 0, 1, 0};
    static int[][][] ratio = {{{0, 0, 2, 0, 0}, {0, 10, 7, 1, 0}, {5, 0, 0, 0, 0}, {0, 10, 7, 1, 0}, {0, 0, 2, 0, 0}},
            {{0, 0, 0, 0, 0}, {0, 1, 0, 1, 0}, {2, 7, 0, 7, 2}, {0, 10, 0, 10, 0}, {0, 0, 5, 0, 0}},
            {{0, 0, 2, 0, 0}, {0, 1, 7, 10, 0}, {0, 0, 0, 0, 5}, {0, 1, 7, 10, 0}, {0, 0, 2, 0, 0}},
            {{0, 0, 5, 0, 0}, {0, 10, 0, 10, 0}, {2, 7, 0, 7, 2}, {0, 1, 0, 1, 0}, {0, 0, 0, 0, 0}}};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N + 4][N + 4];
        for (int i = 0; i < N; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; ++j) {
                map[i + 2][j + 2] = Integer.parseInt(st.nextToken());
            }
        }

        Tornado tornado = new Tornado((N + 4) / 2, (N + 4) / 2, 0, 0);
        int len = 1;
        int cnt = 0;
        while (tornado.r != 2 || tornado.c != 2) {
            tornado.cnt++;
            tornado.r += dr[tornado.d];
            tornado.c += dc[tornado.d];
            int val = map[tornado.r][tornado.c];
            int init = val;
            map[tornado.r][tornado.c] = 0;
            for (int i = 0; i < 5; ++i) {
                for (int j = 0; j < 5; ++j) {
                    if (ratio[tornado.d][i][j] != 0) {
                        int r = val * ratio[tornado.d][i][j] / 100;
                        map[tornado.r + i - 2][tornado.c + j - 2] += r;
                        init -= r;
                    }
                }
            }
            map[tornado.r + dr[tornado.d]][tornado.c + dc[tornado.d]] += init;
            if (tornado.cnt == len) {
                cnt++;
                tornado.cnt = 0;
                tornado.d = (tornado.d + 1) % 4;

                if (cnt == 2) {
                    cnt = 0;
                    len++;
                }
            }
        }
        System.out.println(getOutArea());
    }

    public static int getOutArea() {
        int answer = 0;
        for (int j = 0; j < N + 4; ++j) {
            for (int i = 0; i < 2; ++i) {
                answer += map[i][j];
            }
            for (int i = N + 2; i < N + 4; ++i) {
                answer += map[i][j];
            }
        }
        for (int i = 2; i < N + 2; ++i) {
            for (int j = 0; j < 2; ++j) {
                answer += map[i][j];
            }
            for (int j = N + 2; j < N + 4; ++j) {
                answer += map[i][j];
            }
        }
        return answer;
    }


    public static class Tornado {
        int r, c, d, cnt;

        public Tornado(int r, int c, int d, int cnt) {
            this.r = r;
            this.c = c;
            this.d = d;
            this.cnt = cnt;
        }
    }
}