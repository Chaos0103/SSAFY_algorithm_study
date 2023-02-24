import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class Solution {

    private static int[][] map;
    private static int[][] temp;
    private static int[] dx = {0, -1, 0, 1};
    private static int[] dy = {-1, 0, 1, 0};
    private static int[] gameTurn;
    private static int n, m, balls, min = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("res/input.txt"));
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            balls = sc.nextInt();
            m = sc.nextInt();
            n = sc.nextInt();
            map = new int[n][m];
            temp = new int[n][m];

            gameTurn = new int[balls];

            min = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    int num = sc.nextInt();
                    map[i][j] = num;
                    temp[i][j] = num;
                }
            }

            permutation(0);
            System.out.printf("#%d %d\n", test_case, min);
        }
    }

    private static void permutation(int count) {
        if (count == balls) {
            gameStart();
            min = Math.min(min, countBox());
            copyMap();
            return;
        }
        for (int i = 0; i < m; i++) {
            gameTurn[count] = i;
            permutation(count + 1);
        }
    }

    private static void gameStart() {
        int h = n - 1;
        for (int i = 0; i < balls; i++) {
            int turn = gameTurn[i];
            for (int j = 0; j < n; j++) {
                if (map[j][turn] > 0) {
                    h = j;
                    break;
                }
            }
            shotBall(h, turn);
            dropBoxToEmptyArea();
        }
    }


    private static void shotBall(int x, int y) {
        Deque<int[]> deque = new ArrayDeque<>();
        deque.add(new int[]{x, y, map[x][y]});
        map[x][y] = 0;
        int nx, ny;

        while (!deque.isEmpty()) {
            int[] pos = deque.poll();
            int power = pos[2];

            for (int p = 1; p < power; p++) {
                for (int i = 0; i < 4; i++) {
                    nx = pos[0] + dx[i] * p;
                    ny = pos[1] + dy[i] * p;
                    if (!isRange(nx, ny) || map[nx][ny] == 0)
                        continue;
                    if (map[nx][ny] > 0) {
                        deque.add(new int[] { nx, ny, map[nx][ny] });
                        map[nx][ny]= 0;
                    }
                }
            }
        }
    }

    private static void dropBoxToEmptyArea() {
        Deque<Integer> deque = new ArrayDeque<>();
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                if(map[i][j] > 0) {
                    deque.add(map[i][j]);
                }
            }
            for(int i= n-1; i>= 0; i--) {
                if(deque.isEmpty()) {
                    map[i][j] = 0;
                } else {
                    map[i][j] = deque.pollLast();
                }
            }
        }
    }

    private static int countBox() {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] > 0)
                    cnt++;
            }
        }
        return cnt;
    }

    private static void copyMap() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = temp[i][j];
            }
        }
    }

    private static boolean isRange(int nx, int ny) {
        return nx >= 0 && nx < n && ny >= 0 && ny < m;

    }
}
