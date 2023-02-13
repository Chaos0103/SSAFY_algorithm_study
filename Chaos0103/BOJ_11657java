import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Node {
    private int curNode;
    private int nextNode;
    private int cost;

    public Node(int curNode, int nextNode, int cost) {
        this.curNode = curNode;
        this.nextNode = nextNode;
        this.cost = cost;
    }

    public int getCurNode() {
        return curNode;
    }

    public int getNextNode() {
        return nextNode;
    }

    public int getCost() {
        return cost;
    }
}
public class Main {

    private static final int INF = (int) 1e9;
    private static int n, m;
    private static long[] d = new long[501];
    private static ArrayList<Node> edges = new ArrayList<>();

    private static boolean bf(int start) {
        d[start] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int curNode = edges.get(j).getCurNode();
                int nextNode = edges.get(j).getNextNode();
                int cost = edges.get(j).getCost();
                if (d[curNode] != INF && d[nextNode] > d[curNode] + cost) {
                    d[nextNode] = d[curNode] + cost;
                    if (i == n - 1) {
                        return true; //순환 존재
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        Arrays.fill(d, INF);

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            edges.add(new Node(a, b, c));
        }

        boolean isCycle = bf(1);

        if (isCycle) {
            System.out.println(-1);
        } else {
            for (int i = 2; i <= n; i++) {
                if (d[i] == INF) {
                    System.out.println(-1);
                } else {
                    System.out.println(d[i]);
                }
            }
        }
    }
}
