import java.io.*;
import java.util.StringTokenizer;

public class Main {

    private static int n;
    private static int[][] map;
    private static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        map = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(1, 2, 0);
        bw.write(answer + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int x, int y, int direction) {
        if (x == n && y == n) {
            answer++;
            return;
        }
        switch (direction) {
            case 0:
                if (y + 1 <= n && map[x][y + 1] == 0) {
                    dfs(x, y + 1, 0);
                }
                break;
            case 1:
                if (x + 1 <= n && map[x + 1][y] == 0) {
                    dfs(x + 1, y, 1);
                }
                break;
            case 2:
                if (y + 1 <= n && map[x][y + 1] == 0) {
                    dfs(x, y + 1, 0);
                }
                if (x + 1 <= n && map[x + 1][y] == 0) {
                    dfs(x + 1, y, 1);
                }
                break;
        }
        if (y + 1 <= n && x + 1 <= n && map[x][y + 1] == 0 && map[x + 1][y] == 0 && map[x + 1][y + 1] == 0) {
            dfs(x + 1, y + 1, 2);
        }
    }
}
