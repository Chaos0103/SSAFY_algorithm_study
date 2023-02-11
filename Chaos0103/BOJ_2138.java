import java.util.*;

public class Main {

    private static int n;
    private static int[] arr;
    private static int[] arr2;
    private static int[] target;
    private static int result1 = 1, result2 = 0;
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        arr = new int[n];
        arr2 = new int[n];
        target = new int[n];

        String line = sc.next();
        for (int i = 0; i < n; i++) {
            arr[i] = line.charAt(i) - '0';
            arr2[i] = line.charAt(i) - '0';
        }

        line = sc.next();
        for (int i = 0; i < n; i++) {
            target[i] = line.charAt(i) - '0';
        }

        arr[0] = 1 - arr[0];
        arr[1] = 1 - arr[1];

        for (int i = 1; i < n; i++) {
            if (arr[i - 1] != target[i - 1]) {
                arr[i - 1] = 1 - arr[i - 1];
                arr[i] = 1 - arr[i];
                result1++;
                if (i != n - 1) {
                    arr[i + 1] = 1 - arr[i + 1];
                }
            }
            if (arr2[i - 1] != target[i - 1]) {
                arr2[i - 1] = 1 - arr2[i - 1];
                arr2[i] = 1 - arr2[i];
                result2++;
                if (i != n - 1) {
                    arr2[i + 1] = 1 - arr2[i + 1];
                }
            }
        }

        if (arr[n - 1] != target[n - 1]) {
            result1 = INF;
        }
        if (arr2[n - 1] != target[n - 1]) {
            result2 = INF;
        }

        if (result1 == INF && result2 == INF) {
            System.out.println(-1);
        } else {
            System.out.println(Math.min(result1, result2));
        }
    }
}
