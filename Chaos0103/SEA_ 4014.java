import java.io.FileInputStream;
import java.util.Scanner;

class Solution {

    private static int n, k;
    private static int[][] map;

    public static void main(String args[]) throws Exception {
//        System.setIn(new FileInputStream(&quot;input.txt&quot;));

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int result = 0;
            n = sc.nextInt();
            k = sc.nextInt();

            map = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    map[i][j] = sc.nextInt();
                }
            }

            for (int i = 0; i < n; i++) {
                if (weight(i)) {
                    result++;
                }
            }
            for (int j = 0; j < n; j++) {
                if (height(j)) {
                    result++;
                }
            }

            System.out.printf("#%d %d\n", test_case, result);
        }
    }

    private static boolean weight(int x) {
        boolean down = false;
        int nowHeight = map[x][0];
        int count = 0;
        for (int y = 0; y < n; y++) {
            if (Math.abs(nowHeight - map[x][y]) > 1) {
                return false;
            }
            if (down && count == k) {
                count = 0;
                down = false;
            }
            if (nowHeight == map[x][y]) {
                count++;
            } else if (nowHeight < map[x][y]) { //오르막
                if (count < k) {
                    return false;
                }
                nowHeight = map[x][y];
                count = 1;
            } else { //내리막
                if (down) {
                    return false;
                }
                down = true;
                nowHeight = map[x][y];
                count = 1;
            }
        }
        if (down && count == k) {
            down = false;
        }
        return !down;
    }

    private static boolean height(int y) {
        boolean down = false;
        int nowHeight = map[0][y];
        int count = 0;

        for (int x = 0; x < n; x++) {
            if (Math.abs(nowHeight - map[x][y]) > 1) {
                return false;
            }
            if (down && count == k) {
                count = 0;
                down = false;
            }
            if (nowHeight == map[x][y]) {
                count++;
            } else if (nowHeight < map[x][y]) { //오르막
                if (count < k) {
                    return false;
                }
                nowHeight = map[x][y];
                count = 1;
            } else { //내리막
                if (down) {
                    return false;
                }
                down = true;
                nowHeight = map[x][y];
                count = 1;
            }
        }
        if (down && count == k) {
            down = false;
        }
        return !down;
    }
}
