package algorithm_study.day33;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * <pre>
 *     SSAFY 알고리즘 스터디 32일차 : 중앙값 구하기
 *     접근
 *     1. 입력받을때마다 리스트 정렬
 *     2. 홀수번째마다 중간값 구하기
 * </pre>
 */
public class BOJ_2696 {
    static int T, M;
    static ArrayList<Integer> list;
    static ArrayList<Integer> midList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            init(br);
            process(br);
            setResult(sb);
        }
        print(sb);
    }

    private static void init(BufferedReader br) throws IOException {
        M = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        midList = new ArrayList<>();
    }

    private static void process(BufferedReader br) throws IOException {
        StringTokenizer st;
        int len = (M / 10) + 1;
        for (int i = 0; i < len; i++) {
            st = new StringTokenizer(br.readLine());
            int tokens = st.countTokens();
            for (int j = 0; j < tokens; j++) {
                list.add(Integer.parseInt(st.nextToken()));
                Collections.sort(list);
                // 홀수번째 입력된 경우
                int size = list.size();
                if (size % 2 != 0) {
                    midList.add(getMidValue());
                }
            }
        }
    }

    private static int getMidValue() {
        int len = list.size();
        return list.get(len / 2);
    }

    private static void print(StringBuilder sb) {
        System.out.println(sb);
    }

    private static void setResult(StringBuilder sb) {
        sb.append((list.size() + 1) / 2).append("\n");
        for (Integer num : midList) {
            sb.append(num).append(" ");
        }
        sb.append("\n");
    }
}
