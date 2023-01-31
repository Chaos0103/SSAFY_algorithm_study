import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
 
public class Main_1476 {
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
 
        int E = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
 
        int e = 1, s = 1, m = 1;
        int ans = 1;
        while (true) {
            if (e > 15) {
                e = 1;
            }
 
            if (s > 28) {
                s = 1;
            }
 
            if (m > 19) {
                m = 1;
            }
            if (e == E && s == S && m == M) {
                break;
            }
 
            e++;
            s++;
            m++;
            ans++;
        }
 
        bw.write(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
 
}
