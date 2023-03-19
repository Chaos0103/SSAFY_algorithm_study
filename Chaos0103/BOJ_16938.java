import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int n, l, r, x;
    private static int[] arr;
    private static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(args);
        dfs(0, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);

        bw.write(result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int index, int depth, int sum, int max, int min) {
        if (depth >= 2) {
            if (sum >= l && sum <= r && max - min >= x) {
                result++;
            }
        }
        for (int i = index; i < n; i++) {
            dfs(i + 1, depth + 1, sum + arr[i], Math.max(max, arr[i]), Math.min(min, arr[i]));
        }
    }
}
