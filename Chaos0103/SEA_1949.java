import java.awt.*;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Solution {

    private static int n, k;
    private static int[][] map;
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};

    public static void main(String args[]) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            n = sc.nextInt();
            k = sc.nextInt();
            int height = 0;
            map = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    map[i][j] = sc.nextInt();
                    height = Math.max(height, map[i][j]);
                }
            }

            ArrayList<Point> heightPoints = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (height == map[i][j]) {
                        heightPoints.add(new Point(i, j));
                    }
                }
            }

            int result = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    for (int d = 0; d < k; d++) {
                        map[i][j] -= 1;
                        for (Point heightPoint : heightPoints) {
                            int distance = bfs(heightPoint.x, heightPoint.y);
                            result = Math.max(result, distance);
                        }
                    }
                    map[i][j] += k;
                }
            }
            System.out.printf("#%d %d\n", test_case, result);
        }
    }

    private static int bfs(int x, int y) {
        int result = 1;
        int[][] visited = new int[n][n];
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(x, y));
        visited[x][y] = 1;

        while (!q.isEmpty()) {
            Point point = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = point.x + dx[i];
                int ny = point.y + dy[i];
                if (0 <= nx && nx < n && 0 <= ny && ny < n) {
                    if (map[point.x][point.y] > map[nx][ny]) {
                        if (visited[nx][ny] < visited[point.x][point.y] + 1) {
                            visited[nx][ny] = visited[point.x][point.y] + 1;
                            result = Math.max(result, visited[nx][ny]);
                            q.offer(new Point(nx, ny));
                        }
                    }
                }
            }
        }
        return result;
    }
    //1. 등산로는 가장 높은 봉우리에서 시작해야 한다.
    //2. 등산로는 산으로 올라갈 수 있도록 반드시 높은 지형에서 낮은 지형으로 가로 또는 세로 방향으로 연결이 되어야 한다.
    //   즉, 높이가 같은 곳 혹은 낮은 지형이나, 대각선 방향의 연결은 불가는하다.
    //3. 긴 등산로를 만들기 위해 딱 한 곳을 정해서 최대 K 깊이만큼 지형을 깎는 공사를 할 수 있다.

}
