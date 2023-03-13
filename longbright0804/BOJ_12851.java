package algorithm_study.day34;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * SSAFY 알고리즘 스터디 33일차 : 숨바꼭질 2
 * 1시간
 * 접근
 * 1. BFS 사용
 * 2. X-1, X+1, 2*X 의 경우로 탐색
 * 3. 큐에서 꺼낼 때 방문처리를 해야함
 */
public class BOJ_12851 {
    static class Pos {
        int x;
        int time;

        public Pos(int x, int time) {
            this.x = x;
            this.time = time;
        }
    }

    static int N, K, minTime, result;    // 수빈이의 위치, 동생의 위치, 최소 시간, 방법의 수
    static boolean[] used = new boolean[100001];
    static Queue<Pos> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        init();
        process();
        print();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        minTime = Integer.MAX_VALUE;
    }

    private static void process() {
        if (N >= K) {
            minTime = N - K;
            result = 1;
        } else {
            bfs(new Pos(N, 0));
        }
    }

    private static void bfs(Pos start) {
        q.add(start);
        while (!q.isEmpty()) {
            Pos now = q.poll();
            int x = now.x;
            int time = now.time;
            // 현재 최솟값보다 크면 종료
            if (time > minTime) return;
            // 동생의 위치에 도착한 경우
            if (x == K) {
                if (result == 0) minTime = time;
                result++;
                continue;
            }
            // 방문처리
            used[x] = true;
            // 3가지
            for (int i = 0; i < 3; i++) {
                int nx;
                if (i == 0) {
                    nx = now.x - 1;
                } else if (i == 1) {
                    nx = now.x + 1;
                } else {
                    nx = now.x * 2;
                }
                if (isOutOfRange(nx) || used[nx]) {
                    continue;
                }
                q.add(new Pos(nx, time + 1));
            }
        }
    }

    private static boolean isOutOfRange(int nx) {
        return nx < 0 || nx >= 100001;
    }

    private static void print() {
        System.out.println(minTime);
        System.out.println(result);
    }
}