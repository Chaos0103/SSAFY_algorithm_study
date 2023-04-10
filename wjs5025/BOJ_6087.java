package BOJ_6087;

import java.io.*;
import java.util.*;

class Node {
    int r, c;
    int moveCnt;
    int cornerCnt;
    boolean direction;

    @Override
    public String toString() {
        return "Node [r=" + r + ", c=" + c + ", moveCnt=" + moveCnt + ", cornerCnt=" + cornerCnt + ", direction="
                + direction + "]";
    }

    public Node(int r, int c, int moveCnt, int cornerCnt, boolean direction) {
        super();
        this.r = r;
        this.c = c;
        this.moveCnt = moveCnt;
        this.cornerCnt = cornerCnt;
        this.direction = direction;
    }
}

public class Main {
    static int N, M;
    static String[][] map;
    static boolean[][] visited;
    static int[] dx = { 1, 0, -1, 0 }; // 0,2이면 방향 r & 1,3 이면 방향 c
    static int[] dy = { 0, 1, 0, -1 };
    static int[] start = new int[2];
    static int min = Integer.MAX_VALUE;

    static void bfs(Node start) {
        Queue<Node> q = new ArrayDeque<>();
        q.offer(start);
        visited[start.r][start.c] = true;

        while (!q.isEmpty()) {
            Node current = q.poll();

            visited[current.r][current.c] = true;

            if (map[current.r][current.c].equals("C")) {
                min = Math.min(min, current.cornerCnt);
            }

            for (int i = 0; i < 4; i++) {
                int nx = current.r + dx[i];
                int ny = current.c + dy[i];

                boolean nd = i % 2 == 0 ? true : false;

                if (!(nx >= 0 && ny >= 0 && nx < M && ny < N))
                    continue;

                if (visited[nx][ny])
                    continue;

                if (!map[nx][ny].equals("*")) {
                    // 최초 방향 지정
                    if (current.moveCnt < 1) {
                        visited[nx][ny] = true;
                        q.offer(new Node(nx, ny, current.moveCnt + 1, current.cornerCnt, nd));
                    }

                    // 이동횟수가 1이상일 때, direction 지정
                    else {
                        if (current.direction == nd) {
                            visited[current.r][current.c] = true;
                            q.offer(new Node(nx, ny, current.moveCnt + 1, current.cornerCnt, nd));
                        } else {
                            visited[current.r][current.c] = true;
                            q.offer(new Node(nx, ny, current.moveCnt + 1, current.cornerCnt + 1, nd));
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] tmp = br.readLine().split(" ");

        N = Integer.parseInt(tmp[0]);
        M = Integer.parseInt(tmp[1]);

        map = new String[M][N];
        visited = new boolean[M][N];

        for (int i = 0; i < M; i++) {
            tmp = br.readLine().split("");
            for (int j = 0; j < N; j++) {
                map[i][j] = tmp[j];
                if (map[i][j].equals("C")) {
                    start[0] = i;
                    start[1] = j;
                }
            }
        }

        map[start[0]][start[1]] = "S";
        bfs(new Node(start[0], start[1], 0, 0, false));
        System.out.println(min);
    }

    static void print() {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}