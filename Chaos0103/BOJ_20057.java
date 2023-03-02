import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    private static int n, result;
    private static int[][] map;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    static int nextDir[] = { 2, 3, 1, 0 };
    private static int dsx[][] = {{1, 1, 0, 0, -2, 0, 0, -1, -1, -1}, {-1, -1, 0, 0, 2, 0, 0, 1, 1, 1},
            {-1, 1, -2, 2, 0, -1, 1, -1, 1, 0}, {-1, 1, -2, 2, 0, -1, 1, -1, 1, 0}};
    private static int dsy[][] = {{-1, 1, -2, 2, 0, -1, 1, -1, 1, 0}, {-1, 1, -2, 2, 0, -1, 1, -1, 1, 0},
            {1, 1, 0, 0, -2, 0, 0, -1, -1, -1}, {-1, -1, 0, 0, 2, 0, 0, 1, 1, 1}};
    private static int rate[] = {1, 1, 2, 2, 5, 7, 7, 10, 10};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Point now = new Point(n / 2, n / 2);
        Point next = new Point(0, 0);
        int direction = 2;
        int d = 1;
        int count = 0;
        int check = 0;

        while (now.x != 0 || now.y != 0) {
            next.x = now.x + dx[direction];
            next.y = now.y + dy[direction];
            count++;
            move(now, next, direction);

            if (d == count) {
                count = 0;
                direction = nextDir[direction];
                check++;
            }
            if (check == 2) {
                check = 0;
                d++;
            }
            now.x = next.x;
            now.y = next.y;
        }

        bw.write(result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void move(Point now, Point next, int d) {
        map[next.x][next.y] += map[now.x][now.y];
        map[now.x][now.y] = 0;
        int sand = map[next.x][next.y];
        int a = sand;
        for (int i = 0; i < 9; i++) {
            int sx = next.x + dsx[d][i];
            int sy = next.y + dsy[d][i];
            int amount = (int) (sand * (rate[i] * 0.01));

            check(sx, sy, amount);
            a -= amount;
        }
        int ax = next.x + dsx[d][9];
        int ay = next.y + dsy[d][9];
        check(ax, ay, a);
        map[next.x][next.y] = 0;
    }

    private static void check(int sx, int sy, int amount) {
        if (0 <= sx && sx < n && 0 <= sy && sy < n) {
            map[sx][sy] += amount;
        } else {
            result += amount;
        }
    }
    //토네이도를 시전하면 격자의 가운데 칸부터 토네이도의 이동이 시작된다.
    //토네이도가 소멸되었을 때, 격자의 밖으로 나간 모래의 양을 구해보자.
}
