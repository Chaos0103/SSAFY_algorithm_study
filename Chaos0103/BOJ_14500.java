import java.io.*;
import java.util.*;

public class Main {

    private static int n, m, result;
    private static int[][] map;
    private static int[] dx = {0, 0, -1, 1};
    private static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) {
        FastReader fr = new FastReader();
        //구현부
        n = fr.nextInt();
        m = fr.nextInt();
        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = fr.nextInt();
            }
        }

        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = true;
                dfs(i, j, 1, map[i][j], visited);
                visited[i][j] = false;
                check(i, j);
            }
        }

        System.out.println(result);
    }

    private static void check(int x, int y) {
        if (x < n - 2 && y < m - 1)
            result = Math.max(result, map[x][y] + map[x + 1][y] + map[x + 2][y] + map[x + 1][y + 1]);

        if (x < n - 2 && y > 0)
            result = Math.max(result, map[x][y] + map[x + 1][y] + map[x + 2][y] + map[x + 1][y - 1]);

        if (x < n - 1 && y < m - 2)
            result = Math.max(result, map[x][y] + map[x][y + 1] + map[x][y + 2] + map[x + 1][y + 1]);

        if (x > 0 && y < m - 2)
            result = Math.max(result, map[x][y] + map[x][y + 1] + map[x][y + 2] + map[x - 1][y + 1]);
    }

    private static void dfs(int x, int y, int count, int sum, boolean[][] visited) {
        if (count >= 4) {
            result = Math.max(result, sum);
            return;
        }
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (!(0 <= nx && nx < n && 0 <= ny && ny < m) || visited[nx][ny]) {
                continue;
            }
            visited[nx][ny] = true;
            dfs(nx, ny, count + 1, sum + map[nx][ny], visited);
            visited[nx][ny] = false;

        }
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
