import java.io.*;
import java.util.*;

class Combination {
    private int n;
    private int r;
    private int[] now; // 현재 조합
    private ArrayList<ArrayList<Pos>> result; // 모든 조합

    public ArrayList<ArrayList<Pos>> getResult() {
        return result;
    }

    public Combination(int n, int r) { //전체 갯수, 뽑을 갯수
        this.n = n;
        this.r = r;
        this.now = new int[r];
        this.result = new ArrayList<ArrayList<Pos>>();
    }

    public void combination(ArrayList<Pos> arr, int depth, int index, int target) {
        if (depth == r) {
            ArrayList<Pos> temp = new ArrayList<>();
            for (int i = 0; i < now.length; i++) {
                temp.add(arr.get(now[i]));
            }
            result.add(temp);
            return;
        }
        if (target == n) return;
        now[index] = target;
        combination(arr, depth + 1, index + 1, target + 1);
        combination(arr, depth, index, target + 1);
    }
}
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

    private static int n, m;
    private static int[][] map;
    private static int[][] temp;
    private static ArrayList<Pos> blank = new ArrayList<>();
    private static ArrayList<Pos> virus = new ArrayList<>();

    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) {
        FastReader fr = new FastReader();

        n = fr.nextInt();
        m = fr.nextInt();

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = fr.nextInt();
                if (map[i][j] == 0) {
                    blank.add(new Pos(i, j));
                } else if (map[i][j] == 2) {
                    virus.add(new Pos(i, j));
                }
            }
        }

        Combination com = new Combination(blank.size(), 3);
        com.combination(blank, 0, 0, 0);

        int result = 0;
        for (ArrayList<Pos> positions : com.getResult()) {
            temp = deepCopy();
            for (Pos pos : positions) {
                temp[pos.getX()][pos.getY()] = 1;
            }

            for (Pos pos : virus) {
                bfs(pos.getX(), pos.getY());
            }

            int count = countSafeZone();
            result = Math.max(result, count);
        }
        System.out.println(result);
    }

    private static int[][] deepCopy() {
        int[][] res = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                res[i][j] = map[i][j];
            }
        }
        return res;
    }

    private static void bfs(int x, int y) {
        Queue<Pos> q = new LinkedList<>();
        q.offer(new Pos(x, y));
        while (!q.isEmpty()) {
            Pos pos = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = pos.getX() + dx[i];
                int ny = pos.getY() + dy[i];
                if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                    if (temp[nx][ny] == 0) {
                        temp[nx][ny] = 2;
                        q.offer(new Pos(nx, ny));
                    }
                }
            }
        }
    }

    private static int countSafeZone() {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (temp[i][j] == 0) {
                    count++;
                }
            }
        }
        return count;
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
