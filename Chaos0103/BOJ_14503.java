import java.io.*;
import java.util.*;

public class Main {

    private static int n, m, x, y, d;
    private static int[][] map;
    private static int[][] visited;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) {
        FastReader fr = new FastReader();
        //구현부
        n = fr.nextInt();
        m = fr.nextInt();
        x = fr.nextInt();
        y = fr.nextInt();
        d = fr.nextInt();
        map = new int[n][m];
        visited = new int[n][m];

        visited[x][y] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = fr.nextInt();
            }
        }

        int time = 0;
        int count = 1;
        while (true) {
            turn();
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (visited[nx][ny] == 0 && map[nx][ny] == 0) {
                visited[nx][ny] = 1;
                x = nx;
                y = ny;
                count++;
                time = 0;
                continue;
            } else {
                time++;
            }
            if (time == 4) {
                nx = x - dx[d];
                ny = y - dy[d];
                if (map[nx][ny] == 0) {
                    x = nx;
                    y = ny;
                } else {
                    break;
                }
                time = 0;
            }
        }

        System.out.println(count);
    }

    private static void turn() {
        d -= 1;
        if (d == -1) {
            d = 3;
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
