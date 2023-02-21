import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge> {
    int distance;
    int nodeA;
    int nodeB;

    Edge(int distance, int nodeA, int nodeB) {
        this.distance = distance;
        this.nodeA = nodeA;
        this.nodeB = nodeB;
    }

    @Override
    public int compareTo(Edge o) {
        return this.distance - o.distance;
    }

    @Override
    public String toString() {
        return "Edge [distance=" + distance + ", nodeA=" + nodeA + ", nodeB=" + nodeB + "]";
    }

}

public class Main {
    static int N, M;
    static int[] parent;
    static ArrayList<Edge> edges = new ArrayList<>();

    // find
    public static int getParent(int target) {
        if (target == parent[target]) {
            return target;
        }
        return parent[target] = getParent(parent[target]);
    }

    // union
    public static void unionParent(int nodeA, int nodeB) {
        int a = getParent(nodeA);
        int b = getParent(nodeB);

        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        parent = new int[N + 1];

        for (int i = 1; i < N; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int cost = sc.nextInt();
            edges.add(new Edge(cost, a, b));
        }

        Collections.sort(edges);

        int result = 0;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < edges.size(); i++) {
            if (getParent(edges.get(i).nodeA) != getParent(edges.get(i).nodeB)) {
                unionParent(edges.get(i).nodeA, edges.get(i).nodeB);
                max = Math.max(edges.get(i).distance, max);
                result += edges.get(i).distance;
            }
        }

        System.out.println(result - max);
    }
}