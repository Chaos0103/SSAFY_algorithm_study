package algorithm_study.day33;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * SSAFY 알고리즘 스터디 32일차: 음악 프로그램
 * 솔루션
 * 위상정렬 공부해야함
 */
public class BOJ_2623 {
    static int N, M;
    static int[] indegree;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static Queue<Integer> q = new LinkedList<>();
    static ArrayList<Integer> result = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        init();
        topologySort();
        print();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        indegree = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        // 연결 상태 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            for (int j = 1; j < num; j++) {
                int to = Integer.parseInt(st.nextToken());
                graph.get(from).add(to);
                indegree[to]++;
                from = to;
            }
        }
    }

    private static void topologySort() {
        for (int i = 1; i <= N; i++) {
            // 진입차수가 0인 노드 삽입
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            int now = q.poll();
            result.add(now);
            // 현재 노드와 연결된 간선 제거
            ArrayList<Integer> list = graph.get(now);
            for (int next : list) {
                indegree[next]--;
                // 진입 차수가 0이 되면 삽입
                if (indegree[next] == 0) {
                    q.add(next);
                }
            }
        }
    }

    private static void print() {
        if (result.size() != N) {
            System.out.println(0);
            return;
        }
        for (int num : result) {
            System.out.println(num);
        }
    }
}
