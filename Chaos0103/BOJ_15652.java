import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int r = sc.nextInt();

        combination(new int[r], n, r, 0, 0);
    }

    public static void combination(int[] arr, int n, int r, int index, int target) {
        if (r == 0) {
            for (int num : arr) {
                System.out.print((num + 1) + " ");
            }
            System.out.println();
            return;
        }
        if (target == n) {
            return;
        }
        arr[index] = target;
        combination(arr, n, r - 1, index + 1, target);
        combination(arr, n, r, index, target + 1);
    }
}
