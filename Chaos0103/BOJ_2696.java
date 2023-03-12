import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class None {

    public static int TC;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        TC = Integer.parseInt(br.readLine());

        while (TC-- > 0) {
            PriorityQueue<Integer> descpq = new PriorityQueue<>(Collections.reverseOrder());
            PriorityQueue<Integer> ascpq = new PriorityQueue<>();
            int num = Integer.parseInt(br.readLine());
            
            int mid = (num + 1) / 2;
            sb.append(mid).append("\n");
            int line = 0;
            for (int i = 0; i < num; i++) {
                if (i % 10 == 0) {
                    st = new StringTokenizer(br.readLine());
                }
                int x = Integer.parseInt(st.nextToken());

                if (descpq.size() == ascpq.size())
                    descpq.offer(x);
                else
                    ascpq.offer(x);

                if (!descpq.isEmpty() && !ascpq.isEmpty()) {
                    
                    if (descpq.peek() > ascpq.peek()) {
                        int tmp = ascpq.poll();
                        ascpq.offer(descpq.poll());
                        descpq.offer(tmp);
                    }
                }
                if (i % 2 == 0) {
                    
                    sb.append(descpq.peek()).append(" ");
                    line++;
                    if (line % 10 == 0)
                        sb.append("\n");
                }
            }
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

}
