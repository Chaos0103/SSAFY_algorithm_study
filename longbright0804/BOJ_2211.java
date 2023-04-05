package algorithm_study.day48;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 내일 다시 풀이
 */
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
    }

    static final int INF = (int) 1e9;
    static int N, M;
    static int[] d;
    static ArrayList<ArrayList<Node>> graph;
    static ArrayList<ArrayList<Node>> path;

    public static void main(String[] args) throws IOException {
        // 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // 최단거리 테이블 초기화
        d = new int[N + 1];
        Arrays.fill(d, INF);
        // 그래프, 경로 초기화
        graph = new ArrayList<>();
        path = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
            path.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }

        // 다익스트라 알고리즘 수행
        dijkstra(1);
        System.out.println(Arrays.toString(d));
        for (ArrayList<Node> nodes : path) {
            System.out.println("---------");
            for (Node node : nodes) {
                System.out.println(node.index + " " + node.cost);
            }
        }
    }

    private static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        d[start] = 0;
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int curIdx = cur.index;
            int curCost = cur.cost;
            if (d[curIdx] < curCost) continue;
            ArrayList<Node> nextNodes = graph.get(curIdx);
            for (Node next : nextNodes) {
                int nextIdx = next.index;
                int cost = d[curIdx] + next.cost;
                if (d[nextIdx] > cost) {
                    d[nextIdx] = cost;
                    pq.add(new Node(nextIdx, cost));
                    path.get(curIdx).add(new Node(curIdx, nextIdx));
                    path.get(nextIdx).add(new Node(nextIdx, curIdx));
                }
            }
        }
    }
}
