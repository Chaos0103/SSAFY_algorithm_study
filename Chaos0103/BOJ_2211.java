
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {
    static ArrayList<Pair>[] map;
    static int[] path;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        map = new ArrayList[N+1];
        for(int i=1; i<=N; i++)
            map[i] = new ArrayList<>();
        path = new int[N+1];

        for(int i=0; i<M; i++) {
            input = br.readLine().split(" ");
            int start = Integer.parseInt(input[0]);
            int end = Integer.parseInt(input[1]);
            int cost = Integer.parseInt(input[2]);

            map[start].add(new Pair(end, cost));
            map[end].add(new Pair(start, cost));
        }

        solution(N);

        System.out.println(N-1);

        for(int i=2; i<=N; i++)
            System.out.println(i+" "+path[i]);
    }

    public static void solution(int N) {
        int[] dist = new int[N+1];
        for(int i=1; i<=N; i++)
            dist[i] = Integer.MAX_VALUE;
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(1, 0));
        dist[1] = 0;

        while(!pq.isEmpty()) {
            Pair temp = pq.poll();

            for(Pair next : map[temp.end]) {
                if(dist[next.end] > temp.cost+next.cost) {
                    dist[next.end] = temp.cost+next.cost;
                    path[next.end] = temp.end;
                    pq.add(new Pair(next.end, temp.cost+next.cost));
                }
            }
        }
    }

    public static class Pair implements Comparable<Pair>{
        int end;
        int cost;

        public Pair(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }

        public int compareTo(Pair p) {
            return this.cost > p.cost ? 1 : -1;
        }
    }
}
