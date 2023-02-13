import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Map<Integer, Integer> count = new HashMap<>();
        int n = Integer.parseInt(br.readLine());
        String line = br.readLine();
        String[] num = line.split(" ");
        for (int i = 0; i < n; i++) {
            int value = count.getOrDefault(Integer.parseInt(num[i]), 0);
            count.put(Integer.parseInt(num[i]), value + 1);
        }

        int m = Integer.parseInt(br.readLine());
        String line2 = br.readLine();
        String[] num2 = line2.split(" ");
        for (int i = 0; i < m; i++) {
            bw.write(count.getOrDefault(Integer.parseInt(num2[i]), 0) + " ");
        }
        bw.flush();
    }
}
