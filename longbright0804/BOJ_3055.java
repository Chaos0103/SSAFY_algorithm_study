package algorithm_study.day41;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3055 {
    // 내일 다시 풀이
    static class Node {
        int r;
        int c;
        int type;

        public Node(int r, int c, int type) {
            this.r = r;
            this.c = c;
            this.type = type;
        }
    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int R, C, result;
    static char[][] map;
    static int[][] used;
    static Queue<Node> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        // 초기화
        init();
        // BFS 수행
        bfs();
        // 결과 출력
        print();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        used = new int[R][C];
        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = input.charAt(j);
                // 고슴도치는 1로, 물은 2로 표시
                if (map[i][j] == 'S') {
                    used[i][j] = 1;
                    q.add(new Node(i, j, 1));
                } else if (map[i][j] == '*') {
                    used[i][j] = 2;
                    q.add(new Node(i, j, 2));
                }
            }
        }
    }

    private static void bfs() {
        while (!q.isEmpty()) {
            Node now = q.poll();
            int r = now.r;
            int c = now.c;
            int type = now.type;
            // 도착점이면 종료
            if (map[r][c] == 'D') return;
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (nr < 0 || nc < 0 || nr >= R || nc >= C) continue;
                if (used[nr][nc] != 0 || map[nr][nc] == 'X') continue;
                // 고슴도치인 경우
                if (type == 1) {
                    used[nr][nc] = 1;
                    result++;
                    q.add(new Node(nr, nc, 1));
                } else {
                    used[nr][nc] = 2;
                    q.add(new Node(nr, nc, 2));
                }
            }
        }
    }

    private static void print() {
        System.out.println(result);
    }
}
