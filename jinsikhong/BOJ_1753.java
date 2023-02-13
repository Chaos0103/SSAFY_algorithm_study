package 최단경로;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ_1753 {
    public static final int INF = Integer.MAX_VALUE;
    public static int n, m, start;
    public static ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
    public static int[] d = new int[100001];

    static class Node implements Comparable<Node>{
        public int index;
        public int distance;

        public Node(int index, int distance){
            this.index = index;
            this.distance = distance;
        }

        public int getIndex(){
            return this.index;
        }
        public int getDistance(){
            return this.distance;
        }

        // 거리(비용)가 짧은 것이 높은 우선순위를 가지도록 설정
        @Override
        public int compareTo(Node other) {
            if(this.distance < other.distance){
                return -1;
            }
            return 1;
        }


    }


    public static void dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        d[start] = 0;
        while(!pq.isEmpty()){
            Node node = pq.poll();
            int dist = node.getDistance();
            int now = node.getIndex();

            if(d[now] < dist) continue;

            for(int i = 0; i < graph.get(now).size(); i++){
                int cost = d[now] + graph.get(now).get(i).getDistance();

                if(cost < d[graph.get(now).get(i).getIndex()]){
                    d[graph.get(now).get(i).getIndex()] = cost;
                    pq.offer((new Node(graph.get(now).get(i).getIndex(), cost)));
                }
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt();
        int E = sc.nextInt();
        int K = sc.nextInt();
        for(int i = 0; i <= V; i++){ //idx 주의
            graph.add(new ArrayList<Node>());
        }
        for(int i = 0; i < E; i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            graph.get(u).add(new Node(v, w));
        }
        Arrays.fill(d, INF);

        dijkstra(K);

        for(int i = 1; i <= V; i++){
            if(d[i] == Integer.MAX_VALUE){
                System.out.println("INF");
            }else{
                System.out.println(d[i]);
            }
        }

    }

}
