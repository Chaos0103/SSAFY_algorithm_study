package BOJ_2138;

import java.util.*;
import java.io.*;

/*
 * 아이디어를 떠올리기 힘들었어서,
 * 
 * 30~40분 고민 후, 질문게시판을 참고했는데
 * 너무 많은 힌트를 보고 코드를 짜게 되었습니다.
 * 
 * 이 아이디어를 어떻게 떠올리죠...
 * https://staticvoidlife.tistory.com/143
 * */
public class Main {
	static int N;
	static int[] from;
	static int[] to;
	static int min = Integer.MAX_VALUE;

	public static void switching(int[] arr, int idx) {
		if (idx == 0) {
			arr[idx] = arr[idx] == 1 ? 0 : 1;
			arr[idx + 1] = arr[idx + 1] == 1 ? 0 : 1;
		} else if (idx == N - 1) {
			arr[idx] = arr[idx] == 1 ? 0 : 1;
			arr[idx - 1] = arr[idx - 1] == 1 ? 0 : 1;
		} else {
			arr[idx + 1] = arr[idx + 1] == 1 ? 0 : 1;
			arr[idx] = arr[idx] == 1 ? 0 : 1;
			arr[idx - 1] = arr[idx - 1] == 1 ? 0 : 1;
		}
	}

	// 0번 스위치를 눌렀을 때
	public static int pushZero() {
		int[] arr = from.clone();

		switching(arr, 0);
		int cnt = 1;

		for (int i = 1; i < N; i++) {
			if (arr[i - 1] != to[i - 1]) {
				switching(arr, i);
				cnt++;
			} else {
				continue;
			}
		}
		if (Arrays.equals(to, arr)) {
			return cnt;
		}

		return -1;
	}

	// 0번 스위치를 안 눌렀을 때
	public static int noPushZero() {
		int[] arr = from.clone();

		int cnt = 0;

		for (int i = 1; i < N; i++) {
			if (arr[i - 1] != to[i - 1]) {
				switching(arr, i);
				cnt++;
			} else {
				continue;
			}
		}
		if (Arrays.equals(to, arr)) {
			return cnt;
		}

		return -1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 입력받기
		N = Integer.parseInt(br.readLine());

		from = new int[N];
		to = new int[N];

		String[] tmp = br.readLine().split("");

		for (int i = 0; i < N; i++) {
			from[i] = Integer.parseInt(tmp[i]);
		}

		tmp = br.readLine().split("");
		for (int i = 0; i < N; i++) {
			to[i] = Integer.parseInt(tmp[i]);
		}

		if (Arrays.equals(from, to)) {
			System.out.println(0);
		} else {
			int a = pushZero();
			int b = noPushZero();

			if (a == -1 && b == -1) {
				System.out.println(-1);
			} else if (a > 0 && b > 0) {
				System.out.println(Math.min(a, b));
			} else {
				System.out.println(Math.max(a, b));
			}
		}

	}
}
