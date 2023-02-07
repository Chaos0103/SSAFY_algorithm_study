package BJ10816;

import java.util.*;
import java.io.*;

/*
 * lowerBound와 upperBound가 잘 이해되지 않아서,
 * 솔루션을 참고했습니다.
 */
public class Main {
	static int N, M;
	static int[] cards;
	static StringBuilder sb = new StringBuilder();

	public static int lowerBound(int l, int r, int key) {
		int m;
		while (l < r) {
			m = (l + r) / 2;
			if (key <= cards[m])
				r = m;
			else
				l = m + 1;
		}

		return r;
	}

	public static int upperBound(int l, int r, int key) {
		int m;
		while (l < r) {
			m = (l + r) / 2;
			if (key < cards[m])
				r = m;
			else
				l = m + 1;
		}
		return r;
	}

//	public static int binarySearch(int key, int low, int high) {
//		if (low <= high) {
//			int middle = (low + high) / 2;
//
//			if (key == cards[middle]) {
//				System.out.println("미들" + middle);
//				return middle;
//			} else if (key < cards[middle]) {
//				return binarySearch(key, low, middle - 1);
//			} else {
//				return binarySearch(key, middle + 1, high);
//			}
//		}
//		return 0;
//	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		cards = new int[N];
		String[] line = br.readLine().split(" ");

		for (int i = 0; i < N; i++) {
			cards[i] = Integer.parseInt(line[i]);
		}

		Arrays.sort(cards);

		M = Integer.parseInt(br.readLine());
		String[] questions = br.readLine().split(" ");

		for (int i = 0; i < M; i++) {
			int input = Integer.parseInt(questions[i]);
			sb.append(upperBound(0, N, input) - lowerBound(0, N, input)).append(" ");
		}
		System.out.println(sb.toString());
	}
}
