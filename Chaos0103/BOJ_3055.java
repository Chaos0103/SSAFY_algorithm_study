import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
    private int x;
    private int y;
    private int count;

    public Node(int x, int y, int count) {
        this.x = x;
        this.y = y;
        this.count = count;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getCount() {
        return count;
    }
}

public class Main {

    private static int r, c;
    private static char[][] map;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    private static Queue<Node> sq = new LinkedList<>();
    private static Queue<Point> wq = new LinkedList<>();
    private static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new char[r][c];

        for (int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = str.charAt(j);

                if (map[i][j] == 'S') {
                    sq.add(new Node(i, j, 0));
                } else if (map[i][j] == '*') {
                    wq.add(new Point(i, j));
                }
            }
        }

        bfs();

        System.out.println(answer == Integer.MAX_VALUE ? "KAKTUS" : answer);
    }

    private static void bfs() {
        while (!sq.isEmpty()) {
            int len = wq.size();
            for (int i = 0; i < len; i++) {
                Point poll = wq.poll();
                for (int j = 0; j < 4; j++) {
                    int nx = poll.x + dx[j];
                    int ny = poll.y + dy[j];
                    if (nx >= 0 && nx < r && ny >= 0 && ny < c && map[nx][ny] == '.') {
                        map[nx][ny] = '*';
                        wq.add(new Point(nx, ny));
                    }
                }
            }

            len = sq.size();
            for (int i = 0; i < len; i++) {
                Node node = sq.poll();
                for (int j = 0; j < 4; j++) {
                    int nx = node.getX() + dx[j];
                    int ny = node.getY() + dy[j];
                    int time = node.getCount();
                    if (nx >= 0 && nx < r && ny >= 0 && ny < c) {
                        if (map[nx][ny] == 'D') {
                            answer = Math.min(answer, time + 1);
                            return;
                        } else if (map[nx][ny] == '.') {
                            map[nx][ny] = 'S';
                            sq.add(new Node(nx, ny, time + 1));
                        }

                    }
                }
            }
        }
    }
}
