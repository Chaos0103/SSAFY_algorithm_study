package algorithm_study.day43;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SSAFY 알고리즘 스터디 43일차: 개똥벌레
 * 솔루션
 * # 이진탐색 풀이
 * 배열 두개를 나누는 것을 제외하고는 다 접근 못했음
 * 1. 석순과 종유석을 담을 배열 사용
 * 2. 이진탐색 수행
 * - 배열에 들어있는 값과 높이를 이용해 right 값을 구함
 * - 이 값은 파괴될 장애물의 높이 중 최솟값
 * - 이 값보다 큰 값은 전부 부서지므로, 해당 배열의 길이에서 이 값을 빼주면 파괴될 개수를 구할 수 있음
 * <p>
 * # 누적합 풀이 (현재 솔루션)
 * 1. 입력받은 높이에 따라 석순과 종유석의 개수를 증가시킴
 * 2. 각각의 배열에 대한 누적합 계산
 * 3. 누적합을 구한 후 장애물의 수를 확인하며 최솟값과 개수 갱신
 * - bottom : bottom 장애물 최대 개수 - 이전 장애물 개수(i-1)
 * - top : top 장애물 최대 개수 - 이전 강애물 개수(i+1)
 * - 두 결과의 차이와 현재 최솟값을 비교
 */
public class BOJ_3020 {
    static int N; // 동굴의 길이
    static int H; // 동굴의 높이
    static int[] top;// 석순
    static int[] bot;// 종유석
    static int min; // 파괴하는 장애물의 최솟값
    static int cnt; // min에 해당되는 구간의 수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 동굴의 길이
        H = Integer.parseInt(st.nextToken()); // 동굴의 높이
        bot = new int[H + 1]; // 석순 정보
        top = new int[H + 1]; // 종유석 정보
        min = N; // 파괴하는 장애물의 최솟값
        cnt = 0; // min에 해당되는 구간의 수

        for (int i = 0; i < N / 2; i++) {
            bot[Integer.parseInt(br.readLine())]++; // 석순(bot)
            top[Integer.parseInt(br.readLine())]++; // 종유석(top)
        } // End of input

        process();

        System.out.println(min + " " + cnt);

    }

    private static void process() {

        int[] bot_sum = new int[H + 1]; // 높이가 h이하인 석순의 갯수
        int[] top_sum = new int[H + 1]; // 높이가 h이하인 종유석의 갯수

        // 누적합 계산
        for (int i = 1; i < H + 1; i++) {
            top_sum[i] = top_sum[i - 1] + top[i];
            bot_sum[i] = bot_sum[i - 1] + bot[i];
        }

        for (int i = 1; i < H + 1; i++) {
            int crush = 0; // 부딪히는 개수

            // 부딪히는 종유석의 갯수 = 전체 종유석갯수 - (i-1)이하인 종유석의 갯수
            crush += bot_sum[H] - bot_sum[i - 1];
            // 부딪히는 석순의 갯수 = 전체 석순의 갯수 - (h-i)이하인 석순의 갯수
            crush += top_sum[H] - top_sum[H - i];

            // 최솟값 && 개수 위한 조건문
            if (min > crush) {
                min = crush;
                cnt = 1;
            } else if (min == crush) {
                cnt++;
            }
        }

    }
}
