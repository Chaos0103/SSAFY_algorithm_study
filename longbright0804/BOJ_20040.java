package algorithm_study.day50;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SSAFY 알고리즘 스터디: 사이클 게임
 * 서로소집합 문제 -> union 연산 시 사이클 판별
 * 15분
 */
public class BOJ_20040 {
    static int N, M, result;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = new int[N];
        // 부모 테이블 초기화
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }
        // 연산 입력
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (!unionParent(a, b)) {
                result = i;
                break;
            }
        }
        System.out.println(result);
    }

    private static int findParent(int x) {
        if (parent[x] == x) return x;
        return parent[x] = findParent(parent[x]);
    }

    private static boolean unionParent(int a, int b) {
        a = findParent(a);
        b = findParent(b);
        if (a == b) return false;
        parent[b] = a;
        return true;
    }
}
