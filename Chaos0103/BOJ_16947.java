import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int n;
    private static ArrayList<Integer>[] graph;
    private static boolean[] visited;
    private static boolean[] cycle;
    private static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[u].add(v);
            graph[v].add(u);
        }
        visited = new boolean[n + 1];
        cycle = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            if (isCycle(i, -1, i)) {
                break;

            } else {
                visited = new boolean[n + 1];
            }
        }

        result = new int[n + 1];
        bfs();

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(result[i]).append(" ");
        }
        System.out.println(sb);
        br.close();
    }

    private static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        visited = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            if (cycle[i]) {
                visited[i] = true;
                q.offer(i);
            }
        }

        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : graph[u]) {
                if (!visited[v]) {
                    q.offer(v);
                    visited[v] = true;
                    result[v] = result[u] + 1;
                }
            }
        }
    }

    private static boolean isCycle(int u, int parent, int start) {
        visited[u] = true;
        for (int v : graph[u]) {

            if (!visited[v]) {
                if (isCycle(v, u, start)) {
                    cycle[v] = true;
                    return true;
                }
            } else if (v != parent && v == start) {
                cycle[v] = true;
                return true;
            }
        }
        return false;
    }
}
