package algorithm_study.day48;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_2211 {
    static class Node implements Comparable<Node> {
        int index;
        int cost;

        public Node(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(cost, o.cost);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "index=" + index +
                    ", cost=" + cost +
                    '}';
        }
    }

    static final int INF = (int) 1e9;
    static int N, M, k;
    static int[] d, path;
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        init();
        process();
        print(k, sb);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        d = new int[N + 1];
        path = new int[N + 1];
        Arrays.fill(d, INF);
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(from).add(new Node(to, cost));
            graph.get(to).add(new Node(from, cost));
        }
    }

    private static void process() {
        dijkstra(1);
        setResults();
    }

    private static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        d[start] = 0;
        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int curIdx = current.index;
            int curCost = current.cost;
            if (d[curIdx] < curCost) continue;
            ArrayList<Node> nextNodes = graph.get(curIdx);
            for (Node next : nextNodes) {
                int nextIdx = next.index;
                int cost = d[curIdx] + next.cost;
                if (cost < d[nextIdx]) {
                    d[nextIdx] = cost;
                    path[nextIdx] = curIdx;
                    pq.add(new Node(nextIdx, cost));
                }
            }
        }
    }

    private static void setResults() {
        for (int i = 1; i <= N; i++) {
            if (path[i] == 0) continue;
            k++;
            sb.append(i).append(" ").append(path[i]).append("\n");
        }
    }

    private static void print(int k, StringBuilder sb) {
        System.out.println(k);
        System.out.println(sb);
    }
}
