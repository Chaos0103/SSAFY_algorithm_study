package BOJ_2623;

import java.io.*;
import java.util.*;

/**
 * 솔루션을 보았으나 오답..
 * 위상정렬 공부 후 재도전 하겠숩니다.
 * 
 * 
 */
public class Main {
    static int N, M;
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt(); // 가수의 수
        M = sc.nextInt(); // PD의 수

        graph = new ArrayList[N + 1];
        int[] indegree = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {

            // 몇명의 가수를 담당할건지
            int n = sc.nextInt();

            // 첫 번째 가수
            int first = sc.nextInt();

            for (int j = 0; j < n - 1; j++) {
                int second = sc.nextInt();

                graph[first].add(second);
                indegree[second]++;

                first = second;
            }
        }

        Deque<Integer> q = new ArrayDeque<>();

        // 진입 차수가 0이면, 그때의 가수를 큐에 넣는다.
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        ArrayList<Integer> result = new ArrayList<>();

        while (!q.isEmpty()) {
            int current = q.poll();
            result.add(current);

            // 특정 가수에 연결된 다른 가수들의 진입 차수를 감소시키고,
            // 진입 차수가 0이라면 큐에 넣음
            for (int next : graph[current]) {
                indegree[next]--;

                if (indegree[next] == 0) {
                    q.add(next);
                }
            }
        }

        // result의 크기가 N이 아니면, 사이클이 발생했기 때문에,
        // 진입차수가 0인 가수를 큐에 넣지 못한다.

        // -> 순서를 정할 수 없기 때문에 0을 출력.
        if (result.size() != N) {
            System.out.println(0);
            return;
        }

        // 가수 출연 순서 출력
        for (int s : result) {
            System.out.println(s);
        }
    }
}
