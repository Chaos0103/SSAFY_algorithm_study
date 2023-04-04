package algorithm_study.day47;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * SSAFY 알고리즘 스터디 46일차: 해킹
 * 다익스트라 알고리즘
 * 15:30 시작 16:10 종료
 * 다익스트라 알고리즘 수행하면서 초기화 할 때마다 cnt 값 증가
 * 가장 마지막에 도착한 지점의 dist 값을 time 에 저장
 */
public class BOJ_10282 {

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
    static int T, n, d, c;
    static int cnt;
    static int max = Integer.MIN_VALUE;
    static ArrayList<ArrayList<Node>> graph;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            init(br);
            dijkstra(c);
            getResults();
            print();
        }
    }

    private static void init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        dist = new int[n+1];
        Arrays.fill(dist, INF);
        for (int i = 0; i < d; i++) {
            st = new StringTokenizer(br.readLine());
            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(from).add(new Node(to, cost));
        }
    }

    private static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        dist[start] = 0;
        while(!pq.isEmpty()) {
            Node node = pq.poll();
            int curIdx = node.index;
            if (dist[curIdx] < node.cost) continue;
            for (int i = 0; i < graph.get(curIdx).size(); i++) {
                Node next = graph.get(curIdx).get(i);
                int cost = dist[curIdx] + next.cost;
                if (dist[next.index] > cost) {
                    dist[next.index] = cost;
                    pq.add(new Node(next.index, cost));
                }
            }
        }
    }

    private static void getResults() {
        for (int i : dist) {
            if (i == INF) continue;
            cnt++;
            max = Integer.max(max, i);
        }
    }

    private static void print() {
        System.out.println(cnt + " " + max);
    }
}