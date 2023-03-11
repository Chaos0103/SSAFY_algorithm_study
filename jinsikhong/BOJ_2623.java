package Bj;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_2623 {
    static int N, M;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
    static int[] indegree;
    static ArrayList<Integer> result = new ArrayList<>(); // 정렬 수행 결과를 담을 리스트

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        for(int i = 0; i <= N; i++){
            graph.add(new ArrayList<Integer>());
        }
        indegree = new int[N+1];
        for(int i = 0; i < M; i++){
            int pd = sc.nextInt();
            int[] arr = new int[pd];

            for(int j = 0; j < pd; j++){
                arr[j] = sc.nextInt();
            }
            for(int j = 0; j < pd-1; j++){
                int from = arr[j];
                int to = arr[j+1];
                graph.get(from).add(to);
                indegree[to]++;
            }

        }
        topologySort();
        if(result.size() == N){
            for(int i = 0; i < result.size(); i++){
                System.out.println(result.get(i));
            }

        }else{
            System.out.println(0);
        }
    }
    public static void topologySort(){

        Queue<Integer> q = new LinkedList<>(); // 위상 정렬에 사용할 큐

        // 처음 시작할 때는 진입차수가 0인 노드를 큐에 삽입
        for(int i = 1; i <= N; i++){
            if(indegree[i] == 0){
                q.offer(i);
            }
        }

        //큐가 빌 때까지 반복
        while(!q.isEmpty()){
            // 큐에서 원소 꺼내기
            int now = q.poll();
            result.add(now);

            //해당 원소와 연결된 노드들의 진입차수에서 1빼기
            for(int i = 0; i < graph.get(now).size(); i++){
                indegree[graph.get(now).get(i)] -= 1; //진입차수 1 빼기
                // 새롭게 진입차수가 0이 되는 노드를 큐에 삽입
                if(indegree[graph.get(now).get(i)] == 0){
                    q.offer(graph.get(now).get(i));
                }
            }
        }



    }

}
