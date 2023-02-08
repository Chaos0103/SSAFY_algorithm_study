import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        FastReader fr = new FastReader();
        int n = fr.nextInt();
        int k = fr.nextInt();

        int[] dp = new int[n+1];
        for (int i = 1; i <= n; i++) {
            dp[i] = fr.nextInt() + dp[i - 1];
        }

        int start = 0;
        int end = k;
        int result = Integer.MIN_VALUE;
        while (end <= n) {
            if (end - start == k) {
                result = Math.max(result, dp[end] - dp[start]);
            }
            end++;
            if (end - start > k) {
                start++;
            }
        }
        System.out.println(result);
    }

    public static class FastReader {
        BufferedReader br;
        StringTokenizer st;
        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
        int nextInt() {
            return Integer.parseInt(next());
        }
        long nextLong() {
            return Long.parseLong(next());
        }
        double nextDouble() {
            return Double.parseDouble(next());
        }
        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
