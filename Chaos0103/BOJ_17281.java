import java.io.*;
import java.util.StringTokenizer;

public class Main {

    private static int n, result;
    private static boolean[] selected = new boolean[10];
    private static int[] players = new int[10];
    private static int[][] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        arr = new int[n][10];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < 10; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        players[4] = 1;
        selected[4] = true;

        perm(2);
        bw.write(result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void perm(int depth) {
        if (depth == 10) {
            result = Math.max(result, baseball());
            return;
        }
        for (int i = 1; i < 10; i++) {
            if (!selected[i]) {
                selected[i] = true;
                players[i] = depth;
                perm(depth + 1);
                selected[i] = false;
            }
        }
    }

    private static int baseball() {
        int start = 1;
        int score = 0;
        for (int i = 0; i < n; i++) {
            int[] point = {0, 0, 0, 0, 0};
            while (point[0] < 3) {
                run(point, arr[i][players[start]]);
                if (start == 9) {
                    start = 1;
                } else {
                    start++;
                }
            }
            score += point[4];
        }
        return score;
    }

    private static void run(int[] point, int n) {
        for (int i = 0; i < n; i++) {
            point[4] += point[3];
            point[3] = point[2];
            point[2] = point[1];
            point[1] = 0;
        }
        point[n]++;
    }
}
