import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge> {
    private int nodeA;
    private int nodeB;
    private int distance;

    public Edge(int nodeA, int nodeB, int distance) {
        this.nodeA = nodeA;
        this.nodeB = nodeB;
        this.distance = distance;
    }

    public int getNodeA() {
        return nodeA;
    }

    public int getNodeB() {
        return nodeB;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.distance, other.distance);
    }
}
public class Main {

    private static int n, m;
    private static int[] parent = new int[100001];
    private static ArrayList<Edge> edges = new ArrayList<>();

    private static int findParent(int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = findParent(parent[x]);
    }

    private static void unionParent(int a, int b) {
        a = findParent(a);
        b = findParent(b);
        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }

    public static void main(String[] args) {
        FastReader fr = new FastReader();

        n = fr.nextInt();
        m = fr.nextInt();

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            int a = fr.nextInt();
            int b = fr.nextInt();
            int c = fr.nextInt();
            edges.add(new Edge(a, b, c));
        }

        Collections.sort(edges);

        int result = 0;
        int delete = 0;
        for (int i = 0; i < m; i++) {
            int distance = edges.get(i).getDistance();
            int a = edges.get(i).getNodeA();
            int b = edges.get(i).getNodeB();
            if (findParent(a) != findParent(b)) {
                unionParent(a, b);
                delete = distance;
                result += distance;
            }
        }

        System.out.println(result - delete);
    }

    public static class FastReader {
        BufferedReader br;
        StringTokenizer st;
        public FastReader() { br = new BufferedReader(new InputStreamReader(System.in)); }
        public FastReader(String s) throws FileNotFoundException { br = new BufferedReader(new FileReader(new File(s))); }
        String next() {
            while (st == null || !st.hasMoreElements()) {
                try { st = new StringTokenizer(br.readLine()); }
                catch (IOException e) { e.printStackTrace(); }
            }
            return st.nextToken();
        }
        int nextInt() { return Integer.parseInt(next()); }
        long nextLong() { return Long.parseLong(next()); }
        double nextDouble() { return Double.parseDouble(next()); }
        String nextLine() {
            String str = "";
            try { str = br.readLine(); }
            catch (IOException e) { e.printStackTrace(); }
            return str;
        }
    }
}
