package Algorithm_230321;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_5972 {

    static int dist[];
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    static int N,M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine() );
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dist = new int[N+1];

        for (int i = 0; i <= N ; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < M; i++){
            st= new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            graph.get(A).add(new Node(B,C));
            graph.get(B).add(new Node(A,C));
        }

        Arrays.fill(dist,Integer.MAX_VALUE);

        dijst();
        System.out.println(dist[N]);
    }

    static void dijst(){
        PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        queue.add(new Node(1,0));
        dist[1] = 0;

        while (!queue.isEmpty()){
              Node now = queue.poll();

              for(Node next : graph.get(now.v)){
                  if(dist[next.v] > dist[now.v] + next.cost){
                      dist[next.v] = dist[now.v] + next.cost;
                      queue.add(new Node(next.v, dist[next.v]));
                  }
              }
        }
    }

    static class Node{
        int v;
        int cost;

        public Node(int v,int cost){
                this.v = v;
                this.cost = cost;
        }
    }
}