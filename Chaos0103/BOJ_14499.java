import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Integer.*;

public class Main {

    private static int[] dx = {0, 0, 0, -1, 1};
    private static int[] dy = {0, 1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);
        int n = parseInt(st.nextToken());
        int m = parseInt(st.nextToken());
        int x =parseInt(st.nextToken());
        int y = parseInt(st.nextToken());
        int k = parseInt(st.nextToken());

        int[][] data = new int[n][m];
        for (int i = 0; i < n; i++) {
            String num = br.readLine();
            StringTokenizer st2 = new StringTokenizer(num);
            for (int j = 0; j < m; j++) {
                data[i][j] = parseInt(st2.nextToken());
            }
        }

        int top = 0;
        int down = 0;
        int front = 0;
        int back = 0;
        int left = 0;
        int right = 0;

        String[] orders = br.readLine().split(" ");
        for (String order : orders) {
            int i = parseInt(order);
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                continue;
            }
            if (i == 1 || i == 2) {
                int cycle = 3;
                if (i == 1) {
                    cycle = 1;
                }
                for (int j = 0; j < cycle; j++) {
                    int temp = down;
                    down = right;
                    right = top;
                    top = left;
                    left = temp;
                }
            } else {
                int cycle = 3;
                if (i == 3) {
                    cycle = 1;
                }
                for (int j = 0; j < cycle; j++) {
                    int temp = down;
                    down = back;
                    back = top;
                    top = front;
                    front = temp;
                }
            }

            if (data[nx][ny] == 0) {
                data[nx][ny] = down;
            } else {
                down = data[nx][ny];
                data[nx][ny] = 0;
            }
            x = nx;
            y = ny;
            bw.write(top + "\n");
        }
        bw.flush();
        bw.close();
    }
}
