package Algorithm_230225;

import java.io.*;
import java.util.*;

public class BOJ_2146 {
    static int n, cnt;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    
    public static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
        	st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    dfs(i, j, ++cnt);
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int k = 1; k < cnt; k++) {
            Queue<Node> queue = new LinkedList<>();
            int[][] dist = new int[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(dist[i], -1);
            }


            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j] == k) {
                        queue.add(new Node(i, j));
                        dist[i][j] = 0;
                    }
                }
            }

            while (!queue.isEmpty()) {
                Node node = queue.poll();
                int x = node.x;
                int y = node.y;
                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                    if (dist[nx][ny] != -1) {
                    	continue;
                    }
                    if (map[nx][ny] != 0 && map[nx][ny] != k) {
                        ans = Math.min(ans, dist[x][y] + dist[nx][ny]);
                        break;
                    }
                    dist[nx][ny] = dist[x][y] + 1;
                    queue.add(new Node(nx, ny));
                }
            }
        }

        System.out.println(ans + 1);
    }

    public static void dfs(int x, int y, int num) {
        visited[x][y] = true;
        map[x][y] = num;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
            	continue;
            }
            if (map[nx][ny] == 1 && !visited[nx][ny]) {
                dfs(nx, ny, num);
            }
        }
    }
}

