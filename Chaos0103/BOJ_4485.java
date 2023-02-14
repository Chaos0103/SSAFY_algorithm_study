import java.io.*;
import java.util.*;

class Pos implements Comparable<Pos> {
    private int x;
    private int y;
    private int distance;

    public Pos(int x, int y, int distance) {
        this.x = x;
        this.y = y;
        this.distance = distance;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public int compareTo(Pos other) {
        if (this.distance < other.distance) {
            return -1;
        }
        return 1;
    }
}
public class Main {

    private static final int INF = (int) 1e9;
    private static int n;
    private static int[][] graph;
    private static int[][] d;
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};

    private static void dijkstra(int x, int y) {
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        pq.add(new Pos(x, y, 0));
        d[x][y] = graph[x][y];
        while (!pq.isEmpty()) {
            Pos pos = pq.poll();
            x = pos.getX();
            y = pos.getY();
            int distance = pos.getDistance();
            if (d[x][y] < distance) {
                continue;
            }
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (0 <= nx && nx < n && 0 <= ny && ny < n) {
                    int cost = d[x][y] + graph[nx][ny];
                    if (d[nx][ny] > cost) {
                        d[nx][ny] = cost;
                        pq.add(new Pos(nx, ny, cost));
                    }
                }
            }
        }

    }

    public static void main(String[] args) {
        FastReader fr = new FastReader();

        int no = 1;
        while (true) {
            n = fr.nextInt();
            if (n == 0) {
                break;
            }

            d = new int[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(d[i], INF);
            }

            graph = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    graph[i][j] = fr.nextInt();
                }
            }

            dijkstra(0, 0);

            System.out.printf("Problem %d: %d\n", no++, d[n - 1][n - 1]);
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
