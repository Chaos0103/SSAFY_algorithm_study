
// 10 : 21 시작
// 10 :46
import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] parent;
    static ArrayList<Edge> edges = new ArrayList<>();

    public static class Edge {
        int nodeA;
        int nodeB;

        Edge(int a, int b) {
            this.nodeA = a;
            this.nodeB = b;
        }

        @Override
        public String toString() {
            return "Edge [nodeA=" + nodeA + ", nodeB=" + nodeB + "]";
        }

    }

    // find
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

        // 부모테이블 생성
        parent = new int[N];

        // 부모테이블 초기화
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            edges.add(new Edge(sc.nextInt(), sc.nextInt()));
        }

        for (int i = 0; i < M; i++) {
            if (findParent(edges.get(i).nodeA) != findParent(edges.get(i).nodeB)) {
                unionParent(edges.get(i).nodeA, edges.get(i).nodeB);
            } else {
                result = i + 1;
                break;
            }
        }
        System.out.println(result);
    }
}
