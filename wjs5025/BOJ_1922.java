package BOJ_1922;

import java.io.*;
import java.util.*;

/**
 * 크루스칼 부숴버렸습니다
 * 마스터 ㅎ
 */
class Edge implements Comparable<Edge> {
    int cost;
    int nodeA;
    int nodeB;

    Edge(int cost, int nodeA, int nodeB) {
        this.cost = cost;
        this.nodeA = nodeA;
        this.nodeB = nodeB;
    }

    @Override
    public String toString() {
        return "Edge [cost=" + cost + ", nodeA=" + nodeA + ", nodeB=" + nodeB + "]";
    }

    @Override
    public int compareTo(Edge o) {
        return this.cost - o.cost;
    }
}

public class Main {
    static int[] parent;
    static ArrayList<Edge> edges = new ArrayList<>();
    static int N, M;

    // fines
    public static int findParent(int target) {
        if (target == parent[target]) {
            return target;
        }
        return parent[target] = findParent(parent[target]);
    }

    // union
    public static void unionParent(int nodeA, int nodeB) {
        int a = findParent(nodeA);
        int b = findParent(nodeB);

        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int result = 0;

        N = sc.nextInt();
        M = sc.nextInt();

        // 부모 테이블 초기화
        parent = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int cost = sc.nextInt();
            edges.add(new Edge(cost, a, b));
        }

        Collections.sort(edges);

        for (Edge e : edges) {
            if (findParent(e.nodeA) != findParent(e.nodeB)) {
                unionParent(e.nodeA, e.nodeB);
                result += e.cost;
            }
        }
        System.out.println(result);
    }
}
