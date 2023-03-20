import java.io.*;
import java.util.*;

class Node implements Comparable<Node> {
    private int start;
    private int end;

    public Node(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    @Override
    public int compareTo(Node other) {
        if (this.start == other.start) {
            return this.end - other.end;
        }
        return this.start - other.start;
    }
}

public class Main {

    private static int n;
    private static List<Node> nodes = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            nodes.add(new Node(a, b));
        }

        Collections.sort(nodes);

        Queue<Integer> q = new PriorityQueue<>();
        q.offer(nodes.get(0).getEnd());

        for (int i = 1; i < n; i++) {
            Node node = nodes.get(i);
            if (node.getStart() < q.peek()) {
                q.offer(node.getEnd());
            } else {
                q.poll();
                q.offer(node.getEnd());
            }
        }

        bw.write(q.size() + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
