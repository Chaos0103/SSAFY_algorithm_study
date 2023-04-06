package algorithm_study.day49;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.BitSet;
import java.util.StringTokenizer;

/**
 * SSAFY 알고리즘 스터디 48일차: 중복제거
 * 솔루션
 * BitSet 을 처음봄
 */
public class BOJ_13701 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        BitSet set = new BitSet(33554432);
        StringBuilder sb = new StringBuilder();

        while(st.hasMoreTokens()) {
            int n = Integer.parseInt(st.nextToken());
            if (set.get(n)) continue;   // 중복 값이면 건너뜀
            set.set(n);
            sb.append(n).append(" ");
        }
        System.out.println(sb);
    }
}
