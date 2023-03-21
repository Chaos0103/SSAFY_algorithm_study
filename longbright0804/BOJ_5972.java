package algorithm_study.day40;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * SSAFY 알고리즘 39일차: 택배 배송
 * 다익스트라 알고리즘 기억 안나서 알고리즘 노트보면서 풀었음
 * 20분 소요
 * 솔루션으로 판정하겠음
 */
public class BOJ_5972 {
    static class Node implements Comparable<Node> {
        int index;
        int cost;

        public Node(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    static final int INF = (int) 1e9;
    static int n, m;
    static int[] d;
    static ArrayList<Node>[] graph;
    static PriorityQueue<Node> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        init();
        process();
        print();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = new int[n + 1];
        Arrays.fill(d, INF);
        // 그래프 초기화
        graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }
    }

    private static void process() {
        d[1] = 0;
        pq.add(new Node(1, 0));
        dijkstra();
    }

    private static void dijkstra() {
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            int dist = now.cost;
            int nowIdx = now.index;
            // 이미 최솟값으로 갱신되었으면 탐색할 필요가 없음
            if (d[nowIdx] < dist) continue;
            for (int i = 0; i < graph[nowIdx].size(); i++) {
                Node next = graph[nowIdx].get(i);
                int cost = d[nowIdx] + next.cost;
                // 현재 노드를 거쳐서 다른 노드로 이동하는 거리가 더 짧은 경우
                if (cost < d[next.index]) {
                    d[next.index] = cost;
                    pq.add(new Node(next.index, cost));
                }
            }
        }

    }

    private static void print() {
        System.out.println(d[n]);
    }
}
