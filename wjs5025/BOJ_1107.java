package BOJ_1107;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		int target = sc.nextInt();
		int M = sc.nextInt();

		boolean[] broken = new boolean[10];
		for (int i = 0; i < M; i++) {
			int N = sc.nextInt();
			broken[N] = true;
		}

		int result = Math.abs(target - 100); // 초기값 설정
		for (int i = 0; i <= 999999; i++) {
			String str = String.valueOf(i);
			int length = str.length();

			boolean isBreak = false;
			for (int j = 0; j < length; j++) {
				if (broken[str.charAt(j) - '0']) { // 고장난 버튼을 눌러야 하면
					isBreak = true;
					break; // 더 이상 탐색하지 않고 빠져나온다.
				}
			}
			if (!isBreak) { // i를 누를때 고장난 버튼을 누르지 않는다면
				int min = Math.abs(target - i) + length; // i를 누른 후(len) target까지 이동하는 횟수(target - i)
				result = Math.min(min, result);
			}
		}
		System.out.println(result);
		sc.close();
	}
}
