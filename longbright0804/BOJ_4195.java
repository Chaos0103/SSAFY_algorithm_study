package algorithm_study.day42;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * SSAFY 알고리즘 스터디 41일차: 친구 네트워크
 * 접근
 * 1. 서로소 집합 알고리즘 사용
 * 2. 사람 수 : 친구 관계 수 * 2 를 넘지 않음
 * <p>
 * 솔루션
 * 해시맵을 이용한 서로소 집합 구현을 어떻게해야할지 몰랐음
 * level 배열을 사용한 접근도 못했음
 */
public class BOJ_4195 {
    static int T, F;
    static int[] parent, level;
    static Map<String, Integer> map;    // key = 이름, value = 이름의 인덱스번호

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            F = Integer.parseInt(br.readLine());
            parent = new int[F * 2];
            level = new int[F * 2];
            for (int i = 0; i < F * 2; i++) {
                parent[i] = i;
                level[i] = 1;
            }
            int index = 0;
            map = new HashMap<>();
            for (int i = 0; i < F; i++) {
                st = new StringTokenizer(br.readLine());
                String a = st.nextToken();
                String b = st.nextToken();
                // 맵에 존재하지 않으면 추가
                if (!map.containsKey(a)) map.put(a, index++);
                if (!map.containsKey(b)) map.put(b, index++);
                // union 연산 수행(인덱스 번호)
                sb.append(union(map.get(a), map.get(b))).append("\n");
            }
        }
        System.out.println(sb);
    }

    public static int find(int x) {
        if (x == parent[x]) {
            return x;
        }

        return parent[x] = find(parent[x]);
    }

    // 합집합 연산
    public static int union(int x, int y) {
        x = find(x);
        y = find(y);

        // 항상 x < y인 값이 들어온다고 가정
        if (x != y) {
            parent[y] = x;
            level[x] += level[y]; // y에 있던 층의 개수를 더해 줌.
        }

        return level[x];
    }
}
