import java.io.*;
import java.util.*;

public class Main {

    private static int[][] wheel = new int[5][8];
    private static int k;


    public static void main(String[] args) {
        FastReader fr = new FastReader();

        //구현부
        for (int i = 1; i <= 4; i++) {
            String str = fr.next();
            for (int j = 0; j < 8; j++) {
                wheel[i][j] = str.charAt(j) - '0';
            }
        }

        k = fr.nextInt();
        for (int t = 0; t < k; t++) {
            int num = fr.nextInt();
            int d = fr.nextInt(); //1 시계, -1 반시계

            int[] possible = new int[5];
            possible[num] = d;
            //오른쪽 확인
            for (int i = num + 1; i <= 4; i++) {
                if (wheel[i][6] != wheel[i - 1][2]) {
                    possible[i] = possible[i - 1] * -1;
                }
            }
            //왼쪽 확인
            for (int i = num - 1; i > 0; i--) {
                if (wheel[i][2] != wheel[i + 1][6]) {
                    possible[i] = possible[i + 1] * -1;
                }
            }

            for (int i = 1; i <= 4; i++) {
                if (possible[i] == 1) {
                    turnRight(wheel[i]);
                } else if (possible[i] == -1) {
                    turnLeft(wheel[i]);
                }
            }
        }

        int result = wheel[1][0] + wheel[2][0] * 2 + wheel[3][0] * 4 + wheel[4][0] * 8;
        System.out.println(result);
    }

    //시계방향
    private static void turnRight(int[] target) {
        int temp = target[7];
        for (int i = 7; i > 0; i--) {
            target[i] = target[i - 1];
        }
        target[0] = temp;
    }

    //반시계방향
    private static void turnLeft(int[] target) {
        int temp = target[0];
        for (int i = 0; i < 7; i++) {
            target[i] = target[i + 1];
        }
        target[7] = temp;
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
