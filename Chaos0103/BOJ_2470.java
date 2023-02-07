import java.io.*;
import java.util.Arrays;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int left = 0;
        int right = n - 1;
        int[] result = new int[2];
        int value = Integer.MAX_VALUE;

        while (left < right) {
            int add = arr[left] + arr[right];
            if (Math.abs(value) > Math.abs(add)) {
                value = add;
                result[0] = left;
                result[1] = right;
            }
            if (add == 0) {
                result[0] = left;
                result[1] = right;
                break;
            } else if (add < 0) {
                left++;
            } else {
                right--;
            }
        }

        bw.write(arr[result[0]] + " " + arr[result[1]]);
        bw.flush();
        bw.close();
    }
}
