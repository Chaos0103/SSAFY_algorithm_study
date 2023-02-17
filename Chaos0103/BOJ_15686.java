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
    private static ArrayList<Pos> chickens = new ArrayList<>();
    private static ArrayList<Pos> houses = new ArrayList<>();

    public static void main(String[] args) {
        FastReader fr = new FastReader();

        n = fr.nextInt();
        m = fr.nextInt();

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = fr.nextInt();
                if (map[i][j] == 1) {
                    houses.add(new Pos(i, j));
                } else if (map[i][j] == 2) {
                    chickens.add(new Pos(i, j));
                }
            }
        }

        Combination com = new Combination(chickens.size(), m);
        com.combination(chickens, 0, 0, 0);

        int result = Integer.MAX_VALUE;
        for (ArrayList<Pos> comChicken : com.getResult()) {
            int total = 0;
            for (Pos hPos : houses) {
                int minDistance = Integer.MAX_VALUE;
                for (Pos cPos : comChicken) {
                    int dist = Math.abs(hPos.getX() - cPos.getX()) + Math.abs(hPos.getY() - cPos.getY());
                    minDistance = Math.min(minDistance, dist);
                }
                total += minDistance;
            }
            result = Math.min(result, total);
        }

        System.out.println(result);
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
