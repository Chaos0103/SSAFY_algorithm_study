import java.util.*;

public class Main {
    
    private static int n, m;
    private static int[][] matrix;
    private static int[][] target;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            String line = sc.next();
            for (int j = 0; j < m; j++) {
                matrix[i][j] = line.charAt(j) - '0';
            }
        }

        target = new int[n][m];
        for (int i = 0; i < n; i++) {
            String line = sc.next();
            for (int j = 0; j < m; j++) {
                target[i][j] = line.charAt(j) - '0';
            }
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] != target[i][j] && i + 2 < n && j + 2 < m) {
                    for (int x = i; x < i + 3; x++) {
                        for (int y = j; y < j + 3; y++) {
                            matrix[x][y] = matrix[x][y] == 0 ? 1 : 0;
                        }
                    }
                    count++;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] != target[i][j]) {
                    System.out.println(-1);
                    return;
                }
            }
        }

        System.out.println(count);
    }
}
