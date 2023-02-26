import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {

    private static int n, islandCount = 2, now;
    private static int[][] map;
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};
    private static int result = Integer.MAX_VALUE;

    public static void main(String[] args) {
        FastReader fr = new FastReader();
        //구현부
        n = fr.nextInt();
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = fr.nextInt();
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(i, j)) {
                    islandCount++;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 0) {
                    continue;
                }
                now = map[i][j];
                int count = bfs(i, j);
                if (count <= 0) {
                    continue;
                }
                result = Math.min(result, count);
            }
        }

        System.out.println(result);
    }

    private static boolean dfs(int x, int y) {
        if (!(0 <= x && x < n && 0 <= y && y < n)) {
            return false;
        }
        if (map[x][y] == 1) {
            map[x][y] = islandCount;
            for (int i = 0; i < 4; i++) {
                dfs(x + dx[i], y + dy[i]);
            }
            return true;
        }
        return false;
    }

    private static int bfs(int x, int y) {
        int[][] temp = new int[n][n];
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(x, y));
        while (!q.isEmpty()) {
            Point p = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if (0 <= nx && nx < n && 0 <= ny && ny < n) {
                    if (temp[nx][ny] != 0) {
                        continue;
                    }
                    if (map[nx][ny] == 0) {
                        temp[nx][ny] = temp[p.x][p.y] + 1;
                        q.offer(new Point(nx, ny));continue;
                    }
                    if (map[nx][ny] != now) {
                        return temp[p.x][p.y];
                    }
                }
            }
        }
        return -1;
    }

    private static int[][] deepCopy() {
        int[][] res = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res[i][j] = map[i][j];
            }
        }
        return res;
    }

    public static class FastReader {
        BufferedReader br;
        StringTokenizer st;
        public FastReader() { br = new BufferedReader(new InputStreamReader(System.in)); }
        public FastReader(String s) throws FileNotFoundException { br = new BufferedReader(new FileReader(new File(s))); }
        String next() {
            while (st == null || !st.hasMoreElements()) {
                try { st = new StringTokenizer(br.readLine()); }
                catch (IOException e) { e.printStackTrace(); }
            }
            return st.nextToken();
        }
        int nextInt() { return Integer.parseInt(next()); }
        long nextLong() { return Long.parseLong(next()); }
        double nextDouble() { return Double.parseDouble(next()); }
        String nextLine() {
            String str = "";
            try { str = br.readLine(); }
            catch (IOException e) { e.printStackTrace(); }
            return str;
        }
    }
}
