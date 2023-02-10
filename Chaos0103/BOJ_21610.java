import java.io.*;
import java.util.*;

class Pos {
    private int x;
    private int y;

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
public class Main {

    private static final int[] dx = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    private static final int[] dy = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    private static int n, m;
    private static int[][] map;
    private static Queue<Pos> clouds = new LinkedList<>();

    public static void main(String[] args) {
        FastReader fr = new FastReader();

        n = fr.nextInt();
        m = fr.nextInt();

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = fr.nextInt();
            }
        }

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                clouds.offer(new Pos(n - 1 - i, j));
            }
        }

        for (int i = 0; i < m; i++) {
            int d = fr.nextInt();
            int s = fr.nextInt();
            moveClouds(d, s);
            rain();
            int size = clouds.size();
            for (int j = 0; j < size; j++) {
                Pos pos = clouds.poll();
                copyWaterBug(pos.getX(), pos.getY());
                clouds.offer(new Pos(pos.getX(), pos.getY()));
            }
            createClouds();
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result += map[i][j];
            }
        }
        System.out.println(result);
    }

    private static void moveClouds(int d, int s) {
        int size = clouds.size();
        for (int i = 0; i < size; i++) {
            Pos pos = clouds.poll();
            int nx = (n + pos.getX() + dx[d] * (s % n)) % n;
            int ny = (n + pos.getY() + dy[d] * (s % n)) % n;
            clouds.offer(new Pos(nx, ny));
        }
    }

    private static void rain() {
        boolean[][] temp = new boolean[n][n];
        int size = clouds.size();
        for (int i = 0; i < size; i++) {
            Pos pos = clouds.poll();
            temp[pos.getX()][pos.getY()] = true;
            clouds.offer(new Pos(pos.getX(), pos.getY()));
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (temp[i][j]) {
                    map[i][j]++;
                }
            }
        }
    }

    private static final int[] bugDx = {1, 1, -1, -1};
    private static final int[] bugDy = {1, -1, 1, -1};

    private static void copyWaterBug(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + bugDx[i];
            int ny = y + bugDy[i];
            if (0 <= nx && nx < n && 0 <= ny && ny < n) {
                if (map[nx][ny] > 0) {
                    map[x][y]++;
                }
            }
        }
    }

    private static void createClouds() {
        boolean[][] temp = new boolean[n][n];
        while (!clouds.isEmpty()) {
            Pos pos = clouds.poll();
            temp[pos.getX()][pos.getY()] = true;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] >= 2 && !temp[i][j]) {
                    map[i][j] -= 2;
                    clouds.offer(new Pos(i, j));
                }
            }
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
