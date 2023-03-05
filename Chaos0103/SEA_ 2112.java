import java.io.FileInputStream;
import java.util.Scanner;

class Solution {

    private static int d, w, k, result;
    private static int[][] map;

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
//        System.setIn(new FileInputStream("input.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            result = Integer.MAX_VALUE;
            d = sc.nextInt();
            w = sc.nextInt();
            k = sc.nextInt();

            int[][] temp = new int[d][w];
            map = new int[d][w];
            for (int i = 0; i < d; i++) {
                for (int j = 0; j < w; j++) {
                    map[i][j] = sc.nextInt();
                    temp[i][j] = map[i][j];
                }
            }
            if (performanceTest(temp)) {
                result = 0;
            } else {
                dfs(temp, 0, 0);
            }

            sb.append("#").append(test_case).append(" ").append(result).append("\n");
        }

        System.out.println(sb);
    }

    private static void dfs(int[][] arr, int depth, int count) {
        if (result <= count) {
            return;
        }

        if (depth == d) {
            if (performanceTest(arr)) {
                result = Math.min(result, count);
            }
            return;
        }
        dfs(arr, depth + 1, count);
        for (int j = 0; j < w; j++) {
            arr[depth][j] = 0;
        }
        dfs(arr, depth + 1, count + 1);
        for (int j = 0; j < w; j++) {
            arr[depth][j] = 1;
        }
        dfs(arr, depth + 1, count + 1);
        for (int j = 0; j < w; j++) {
            arr[depth][j] = map[depth][j];
        }
    }

    private static boolean performanceTest(int[][] arr) {
        for (int j = 0; j < w; j++) {
            int count = 0;
            int attribute = arr[0][j];
            for (int i = 0; i < d; i++) {
                if (attribute == arr[i][j]) {
                    count++;
                } else {
                    count = 1;
                    attribute = arr[i][j];
                }
                if (count == k) {
                    break;
                }
            }
            if (count < k) {
                return false;
            }
        }
        return true;
    }
}
