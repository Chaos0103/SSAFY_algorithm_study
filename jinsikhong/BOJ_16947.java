package Bj;

import java.util.*;

public class BOJ_16947 {
    static int n;
    static boolean[] visited;
    static int first; // dfs 시작 노드
    static boolean[] circular;
    static int[] result;
    static boolean finsihCircular;
    static ArrayList<Integer>[] graph;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        graph = new ArrayList[n+1];
        visited = new boolean[n+1];
        circular = new boolean[n+1]; //순환선
        result = new int[n+1];
        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i = 1; i<= n; i++){
            int from = sc.nextInt();
            int to = sc.nextInt();
            graph[from].add(to);
            graph[to].add(from);
        }
        for(int i = 1; i <= n; i++){
            if(finsihCircular){
                break;
            }
            Arrays.fill(visited, false);
            first = i;
            dfs(i, 1);
        }
//        System.out.println(Arrays.toString(result));



//        for(int i = 1; i <= n; i++){
//            for(int j = 0; j < graph[i].size(); j++){
//                System.out.print(graph[i].get(j)+ " ");
//            }
//            System.out.println();
//        }
        for(int i = 1; i <= n; i++){
            if(result[i] == 0){
                result[i] = bfs(i);
            }
        }


        for(int i = 1; i <= n; i ++){
            if(result[i] == -1){
                System.out.print(0 + " ");
            }else{
                System.out.print(result[i] + " ");
            }
        }

    }


    static class Node{
        int x, depth;
        public Node(int x, int depth){
            this.x = x;
            this.depth = depth;
        }
    }

    static int bfs(int start){
        visited = new boolean[n+1];
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(start, 1));
        visited[start] = true;
        while(!q.isEmpty()){
            Node cur = q.poll();
//            System.out.println(cur.x);
            for(int i = 0; i < graph[cur.x].size(); i++){
                if(result[graph[cur.x].get(i)] == -1){ // 간선 노드면
                    return cur.depth;
                }
                if(!visited[graph[cur.x].get(i)]){
                    q.offer(new Node(graph[cur.x].get(i), cur.depth + 1));
                    visited[graph[cur.x].get(i)] = true;
                }
            }
        }

        return -1;
    }



    static void dfs(int start, int cnt){
        if(finsihCircular){
            return;
        }
        visited[start] = true;
        for(int i = 0; i < graph[start].size(); i++){
            if(graph[start].get(i) == first && cnt > 2){//
                for(int j = 1; j <= n; j++){
                    if(visited[j]) {
                        result[j] = -1;
                        circular[j] = true;

                    }
                }
                finsihCircular = true;
                return;
            }
            if(!visited[graph[start].get(i)]){
                dfs(graph[start].get(i), cnt+1);
                visited[graph[start].get(i)] = false;
            }
        }
    }

}
