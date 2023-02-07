import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);

        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int left = 1;
        int right = arr[n - 1] - arr[0];
        int result = 0;

        while (left <= right) {
            int mid = (left + right) / 2;
            int value = arr[0];
            int count = 1;
            for (int i = 1; i < n; i++) {
                if (arr[i] >= value + mid) {
                    value = arr[i];
                    count++;
                }
            }

            if (count >= c) {
                left = mid + 1;
                result = mid;
            } else {
                right = mid - 1;
            }
        }

        bw.write(result + "\n");
        bw.flush();
        bw.close();
    }
}
