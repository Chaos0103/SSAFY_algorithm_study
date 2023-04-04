package Algorithm_230404;

import java.io.*;
import java.util.*;

class Computer implements Comparable<Computer> {
    int depend;
    int time;
	
    public Computer(int depend, int time) {
        this.depend = depend;
        this.time = time;
    }

    @Override
    public int compareTo(Computer o) {
        return this.time - o.time;
    }
}

public class BOJ_10282 {

    static int INF = Integer.MAX_VALUE;
	
    static ArrayList<Computer>[] list;
    static int[] dist;
    static boolean[] visited;
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
        int T = Integer.parseInt(br.readLine());
		
        for (int testCase = 0; testCase < T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
			
            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
			
            list = new ArrayList[n + 1];
            dist = new int[n + 1];
            visited = new boolean[n + 1];
		
            for (int i = 1; i < n + 1; i++) {
                dist[i] = INF;
                list[i] = new ArrayList<>();
            }
			
            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
				
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());
				
                list[b].add(new Computer(a, s));
            }
			
            dijkstra(c);
			
            int infect = 0; 
            int total = 0; 
			
            for (int i = 1; i < n + 1; i++) {
                if (dist[i] != INF) {
                    infect++;
                    total = Math.max(total, dist[i]);
                }
            }
			
            System.out.println(infect + " " + total);
        }

    }
	
    public static void dijkstra(int start) {
        PriorityQueue<Computer> queue = new PriorityQueue<>();
		
        dist[start] = 0;
        queue.offer(new Computer(start, 0));
		
        while (!queue.isEmpty()) {
            int cur = queue.poll().depend;
			
            if (!visited[cur]) {
                visited[cur] = true;
				
                for (Computer next : list[cur]) {
                    if (dist[next.depend] > dist[cur] + next.time) {
                        dist[next.depend] = dist[cur] + next.time;
                        queue.add(new Computer(next.depend, dist[next.depend]));
                    }
                }
            }
        }
    }
}