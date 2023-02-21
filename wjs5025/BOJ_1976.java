import java.io.*;
import java.util.*;

class Node {
    int nodeA;
    int nodeB;

    Node(int nodeA, int nodeB) {
        this.nodeA = nodeA;
        this.nodeB = nodeB;
    }

    @Override
    public String toString() {
        return "Node [nodeA=" + nodeA + ", nodeB=" + nodeB + "]";
    }

}

public class Main {
    static int N, M;
    static int[] parent;
    static ArrayList<Node> edges = new ArrayList<>();

    public static int getParent(int target) {
        if (target == parent[target]) {
            return target;
        }
        return parent[target] = getParent(parent[target]);
    }

    public static void unionParent(int n1, int n2) {
        int a = getParent(n1);
        int b = getParent(n2);

        if (a < b) {
            parent[b] = a;
        } else
            parent[a] = b;
    }

    public static void main(String[] args) throws IOException {
        // Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        // 부모테이블 만들기
        parent = new int[N + 1];

        // 부모 테이블 초기화
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 1; i <= N; i++) {
            String[] tmp = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                int input = Integer.parseInt(tmp[j]);

                if (input == 1) {
                    edges.add(new Node(i, j + 1));
                }
            }
        }

        for (Node e : edges) {
            if (getParent(e.nodeA) != getParent(e.nodeB)) {
                unionParent(e.nodeA, e.nodeB);
            }
        }

        String[] tour = br.readLine().split(" ");
        boolean flag = false;

        for (int i = 0; i < M; i++) {
            if (getParent(Integer.parseInt(tour[0])) != getParent(Integer.parseInt(tour[i]))) {
                flag = true;
                break;
            }
        }

        if (!flag) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

    }
}