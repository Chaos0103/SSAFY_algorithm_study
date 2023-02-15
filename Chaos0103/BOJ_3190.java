import java.io.*;
import java.util.*;

class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
public class Main {

    private static final int[] dx = {0, 1, 0, -1};
    private static final int[] dy = {1, 0, -1, 0};
    private static int n, k, l, result;
    private static int direction;
    private static int[][] map;

    private static Map<Integer, Character> cmd = new HashMap<>();

    public static void main(String[] args) {
        FastReader fr = new FastReader();

        n = fr.nextInt();
        k = fr.nextInt();

        map = new int[n + 1][n + 1];
        for (int i = 0; i < k; i++) {
            int x = fr.nextInt();
            int y = fr.nextInt();
            map[x][y] = 1;
        }
        map[1][1] = 9;

        l = fr.nextInt();
        for (int i = 0; i < l; i++) {
            int x = fr.nextInt();
            char c = fr.next().charAt(0);
            cmd.put(x, c);
        }

        solution(1, 1);

        System.out.println(result + 1);
    }

    private static void solution(int x, int y) {
        Deque<Position> dq = new LinkedList<>();
        dq.offer(new Position(x, y));
        while (true) {
            Position pos = dq.getFirst();
            int nx = pos.getX() + dx[direction];
            int ny = pos.getY() + dy[direction];
            //뱀 머리가 범위 밖을 벗어난 경우
            if (nx <= 0 || n < nx || ny <= 0 || n < ny) {
                break;
            }
            //이동 위치에 뱀 일부가 있는 경우
            if (map[nx][ny] == 9) {
                break;
            }
            //이동한 자리에 사과가 있는 경우
            if (map[nx][ny] == 1) {
                map[nx][ny] = 0;
            } else {
                //꼬리 당기기
                Position tail = dq.pollLast();
                map[tail.getX()][tail.getY()] = 0;
            }
            //머리 이동
            dq.offerFirst(new Position(nx, ny));
            map[nx][ny] = 9;

            result++;
            //시간되면 회전
            if (cmd.containsKey(result)) {
                char c = cmd.get(result);
                turn(c);
            }
        }
    }

    private static void turn(char c) {
        if (c == 'D') {
            direction = (direction + 1) % 4;
        } else {
            direction--;
            if (direction < 0) {
                direction = 3;
            }
        }
    }

    public static class FastReader {
        BufferedReader br;
        StringTokenizer st;
        public FastReader() { br = new BufferedReader(new InputStreamReader(System.in)); }
        public FastReader(String s) throws FileNotFoundException { br = new BufferedReader(new FileReader(new File(s))); }
        String next() {
            while (st == null || !st.hasMoreElements()) {
                try { st = new StringTokenizer(br.readLine()); }
                catch (IOException e) { e.printStackTrace(); }
            }
            return st.nextToken();
        }
        int nextInt() { return Integer.parseInt(next()); }
        long nextLong() { return Long.parseLong(next()); }
        double nextDouble() { return Double.parseDouble(next()); }
        String nextLine() {
            String str = "";
            try { str = br.readLine(); }
            catch (IOException e) { e.printStackTrace(); }
            return str;
        }
    }
}
