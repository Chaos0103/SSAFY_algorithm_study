package algorithm_study.day43;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * SSAFY 알고리즘 스터디 43일차: 전화번호 목록
 * 30분
 */
public class BOJ_5052 {
    static int T, N;
    static String[] numbers;
    static StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            input(br);
            setResult();
        }
        print();
    }

    private static void input(BufferedReader br) throws IOException {
        N = Integer.parseInt(br.readLine());
        numbers = new String[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = br.readLine();
        }
    }

    private static boolean isValid() {
        Arrays.sort(numbers);
        boolean isValid = true;
        for (int i = 0; i < N - 1; i++) {
            if (numbers[i + 1].startsWith(numbers[i])) {
                isValid = false;
                break;
            }
        }
        return isValid;
    }

    private static void setResult() {
        if (isValid()) result.append("YES\n");
        else result.append("NO\n");
    }

    private static void print() {
        System.out.println(result);
    }
}
