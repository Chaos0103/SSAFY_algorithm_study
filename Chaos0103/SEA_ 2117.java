import java.awt.*;
import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Solution {

    private static int n, m, result;
    private static int[][] map;
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        //System.setIn(new FileInputStream("input.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            result = 0;
            n = sc.nextInt();
            m = sc.nextInt();

            map = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    map[i][j] = sc.nextInt();
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    bfs(i, j);
                }
            }

            sb.append("#").append(test_case).append(" ").append(result).append("\n");
        }
        System.out.println(sb);
    }

    //좌표에 대해서 bfs
    private static void bfs(int x, int y) {
        int homeCount = 0;
        int[][] temp = new int[n][n];
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(x, y));
        temp[x][y] = 1;
        while (!q.isEmpty()) {
            Point point = q.poll();
            if (map[point.x][point.y] == 1) {
                homeCount++;
                int plus = getPlus(temp[point.x][point.y], m, homeCount);
                refresh(plus, homeCount);
            }
            for (int i = 0; i < 4; i++) {
                int nx = point.x + dx[i];
                int ny = point.y + dy[i];
                if (isOutOfRange(nx, ny)) {
                    continue;
                }
                if (temp[nx][ny] > 0) {
                    continue;
                }
                temp[nx][ny] = temp[point.x][point.y] + 1;
                q.offer(new Point(nx, ny));
            }
        }
    }

    //범위 확인
    private static boolean isOutOfRange(int x, int y) {
        return !(0 <= x && x < n && 0 <= y && y < n);
    }

    //운영 비용
    private static int getPrice(int k) {
        return k * k + (k - 1) * (k - 1);
    }

    //이익계산
    private static int getPlus(int k, int m, int count) {
        return count * m - getPrice(k);
    }

    //결과 갱신
    private static void refresh(int price, int count) {
        if (price < 0) {
            return;
        }
        result = Math.max(result, count);
    }
}
