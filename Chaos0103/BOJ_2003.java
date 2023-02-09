import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int count = 0;
        int intervalSum = 0;
        int right = 0;
        for (int left = 0; left < n; left++) {
            while (intervalSum < m && right < n) {
                intervalSum += arr[right];
                right++;
            }

            if (intervalSum == m) {
                count++;
            }

            intervalSum -= arr[left];
        }

        bw.write(count + "\n");
        bw.flush();
        bw.close();
    }
}
