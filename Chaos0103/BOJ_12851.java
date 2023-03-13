import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static int n, k;
    private static int[] time = new int[100001];
    private static int minTime = Integer.MAX_VALUE;
    private static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        if (n >= k) {
            System.out.println(n - k);
            System.out.println(1);
            return;
        }

        bfs();

        System.out.println(minTime);
        System.out.println(count);
    }

    private static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.offer(n);
        time[n] = 1;

        while (!q.isEmpty()) {
            int now = q.poll();
            if (minTime < time[now]) {
                return;
            }
            for (int i = 0; i < 3; i++) {
                int next = now;
                if (i == 0) {
                    next++;
                } else if (i == 1) {
                    next--;
                } else {
                    next <<= 1;
                }

                if (next < 0 || next > 100000) {
                    continue;
                }

                if (next == k) {
                    minTime = time[now];
                    count++;
                }

                if (time[next] == 0 || time[next] == time[now] + 1) {
                    q.offer(next);
                    time[next] = time[now] + 1;
                }
            }
        }
    }
}
