import java.io.*;
import java.util.*;

public class Main {

    private static int n, k;
    private static int[] belt;
    private static boolean[] robots;

    public static void main(String[] args) {
        FastReader fr = new FastReader();

        n = fr.nextInt();
        k = fr.nextInt();
        belt = new int[n * 2];
        robots = new boolean[n];

        int count = 0;
        for (int i = 0; i < n * 2; i++) {
            belt[i] = fr.nextInt();
        }

        while (true) {
            turn();
            count++;
            move();
            if (isEnd()) {
                System.out.println(count);
                break;
            }
        }
    }

    private static void turn() {
        int end = belt[belt.length - 1];
        for (int i = belt.length - 2; i > -1; i--) {
            belt[i + 1] = belt[i];
        }
        belt[0] = end;

        for (int i = n - 2; i > -1; i--) {
            robots[i + 1] = robots[i];
        }
        robots[0] = false;
    }

    private static void move() {
        if (robots[n - 1]) {
            robots[n - 1] = false;
        }
        for (int i = n - 2; i > 0; i--) {
            if (robots[i] && !robots[i + 1] && belt[i + 1] > 0) {
                robots[i + 1] = true;
                robots[i] = false;
                belt[i + 1]--;
            }
        }

        if (belt[0] > 0 && !robots[0]) {
            robots[0] = true;
            belt[0]--;
        }
    }

    private static boolean isEnd() {
        int count = 0;
        for (int j : belt) {
            if (j == 0) {
                count++;
            }
        }
        return count >= k;
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
