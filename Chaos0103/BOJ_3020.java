import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int result;
    private static int[] up;
    private static int[] down;
    private static int n, h;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        up = new int[n / 2];
        down = new int[n / 2];
        for (int i = 0; i < n / 2; i++) {
            down[i] = Integer.parseInt(br.readLine());
            up[i] = Integer.parseInt(br.readLine());
        }

        int min = n;
        int count = 0;

        Arrays.sort(up);
        Arrays.sort(down);

        for (int i = 1; i < h + 1; i++) {
            int countWall = binarySearch(0, n / 2, i, down) + binarySearch(0, n / 2, h - i + 1, up);

            if (min == countWall) {
                count++;
                continue;
            }
            if (min > countWall) {
                min = countWall;
                count = 1;
            }
        }

        System.out.println(min + " " + count);
    }

    private static int binarySearch(int left, int right, int h, int[] arr) {
        while (left < right) {
            int mid = (left + right) / 2;

            if (arr[mid] >= h) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return arr.length - right;
    }
}
