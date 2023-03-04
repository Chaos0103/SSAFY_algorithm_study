import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[][] map;
    static boolean[][] visited;
    static int[][] scores;
    static int direction = 1;
    static int r = 0;
    static int c = 0;
    static int sum = 0;
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };

    static int[][] dice = { { 0, 2, 0 }, { 4, 1, 3 }, { 0, 5, 0 }, { 0, 6, 0 } };

    static int bfs(int r, int c) {
        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], false);
        }

        Deque<int[]> q = new ArrayDeque<int[]>();
        int[] tmp = { r, c };
        q.offer(tmp);
        visited[tmp[0]][tmp[1]] = true;
        int b = map[r][c];
        int cnt = 0;

        while (!q.isEmpty()) {
            int[] current = q.poll();
            cnt++;

            for (int i = 0; i < 4; i++) {
                int nx = current[0] + dx[i];
                int ny = current[1] + dy[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                    if (!visited[nx][ny] && map[nx][ny] == b) {

                        int[] arr = { nx, ny };
                        q.offer(arr);
                        visited[nx][ny] = true;

                    }
                }
            }
        }
        return cnt;
    }

    static void swap(int i, int j, int x, int y) {
        int tmp = dice[i][j];
        dice[i][j] = dice[x][y];
        dice[x][y] = tmp;
    }

    // 주사위 굴리기
    static void rollEast() {
        swap(1, 0, 1, 1);
        swap(1, 0, 1, 2);
        swap(1, 0, 3, 1);
    }

    static void rollWest() {
        swap(1, 0, 3, 1);
        swap(1, 0, 1, 2);
        swap(1, 0, 1, 1);
    }

    static void rollNorth() {
        swap(0, 1, 1, 1);
        swap(1, 1, 2, 1);
        swap(2, 1, 3, 1);
    }

    static void rollSouth() {
        swap(2, 1, 3, 1);
        swap(1, 1, 2, 1);
        swap(0, 1, 1, 1);
    }

    // 시계 회전
    static void rotateRight() {
        if (direction == 0) {
            direction = 1;
        } else if (direction == 1) {
            direction = 2;
        } else if (direction == 2) {
            direction = 3;
        } else if (direction == 3) {
            direction = 0;
        }
    }

    // 반시계 회전
    static void rotateLeft() {
        if (direction == 0) {
            direction = 3;
        } else if (direction == 1) {
            direction = 0;
        } else if (direction == 2) {
            direction = 1;
        } else if (direction == 3) {
            direction = 2;
        }
    }

    // A는 주사위 아랫면
    // B는 맵의 정수
    static void changeDircetion(int A, int B) {
        if (A > B)
            rotateRight();
        else if (A < B)
            rotateLeft();
        return;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();

        map = new int[N][M];
        visited = new boolean[N][M];
        scores = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        while (K != 0) {
            K--;
            // 북
            if (direction == 0) {
                if (r - 1 >= 0) {
                    // 갈데있으면 북쪽으로
                    rollNorth();
                    r = r - 1;
                } else {
                    // 갈데 없으면 방향 반대로
                    rotateLeft();
                    rotateLeft();

                    // 남쪽으로 굴리기
                    rollSouth();
                    r = r + 1;
                }
            } else if (direction == 1) {
                // 동
                if (c + 1 < M) {
                    // 갈데있으면 동쪽으로
                    rollEast();
                    c = c + 1;
                } else {
                    // 갈데 없으면 방향 반대로
                    rotateLeft();
                    rotateLeft();

                    // 서쪽으로 굴리기
                    rollWest();
                    c = c - 1;
                }
            } else if (direction == 2) {
                // 남
                if (r + 1 < N) {
                    // 갈데있으면 남쪽으로
                    rollSouth();
                    r = r + 1;
                } else {
                    // 갈데 없으면 방향 반대로
                    rotateLeft();
                    rotateLeft();

                    // 북쪽으로 굴리기
                    rollNorth();
                    r = r - 1;
                }
            } else if (direction == 3) {
                // 서
                if (c - 1 >= 0) {
                    // 갈데있으면 서쪽으로
                    rollWest();
                    c = c - 1;
                } else {
                    // 갈데 없으면 방향 반대로
                    rotateLeft();
                    rotateLeft();

                    // 동쪽으로 굴리기
                    rollEast();
                    c = c + 1;
                }
            }

            // 굴렸으니까 점수합해주기
            sum += (bfs(r, c) * map[r][c]);

            if (dice[3][1] > map[r][c]) {
                rotateRight();
            } else if (dice[3][1] < map[r][c]) {
                rotateLeft();
            }
        }
        // 점수 갱신
        System.out.println(sum);
    }

    /*
     * 1. 주사위 굴러가는거 만들기 / 2. 방향바꾸는거 만들기 / 3. 점수계산해서 더하는거 만들기
     */
    static void printDice() {
        System.out.println();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(dice[i][j]);
            }
            System.out.println();
        }
    }
}