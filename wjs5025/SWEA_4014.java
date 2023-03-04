package SWEA_4014;

import java.io.*;
import java.util.*;

/*
 * tc 10이 틀린 이유를 모르겠습니다.
 * */
public class Solution {
	static int N, X;
	static int[][] map;
	static Deque<Integer> q;

	// 한 행을 볼 건데, 해당 행에서 지을 수 있으면 true 반환
	static boolean check(int[] arr) {
		int nowValue = arr[0];
		int cntNowValue = 1;

//		System.out.println("---------");
//		System.out.println("이 배열은 통과할까?");
//		System.out.println(Arrays.toString(arr));

		for (int i = 1; i < N; i++) {
			// 1. nextValue == 현재 값
			if (arr[i] == nowValue) {
				cntNowValue++;
			}

			// 2. nextValue == 현재값 + 1
			else if (arr[i] == nowValue + 1) {
				if (cntNowValue >= X) {
					nowValue = arr[i];
					cntNowValue = 1;
					continue;
				}

				return false;
			}

			// 3. nextValue > 현재값 + 1
			else if (arr[i] > nowValue + 1)
				return false;

			// 4. nextValue == 현재값 - 1
			else if (arr[i] == nowValue - 1) {
				int cntNextValue = 0;
				int idx = i;
				for (int j = i; j < N; j++) {
					if (arr[j] == arr[i])
						cntNextValue++;
					else
						break;
					idx = j;
				}
				if (cntNextValue < X)
					return false;
				nowValue = arr[i];
				cntNowValue = cntNextValue-X;
				i = idx;
			}

			// 5. nextValue < 현재값 - 1
			else if (arr[i] < nowValue - 1)
				return false;
		}

//		System.out.println("결과 : " + true);
		return true;
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		int tc = sc.nextInt();

		for (int t = 1; t <= tc; t++) {
			N = sc.nextInt();
			X = sc.nextInt();

			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
				}
			}

//			print();

			int sum = 0;
			
			for (int i = 0; i < N; i++) {
				if (check(map[i])) {
					sum++;
				}

			}
			for (int j = 0; j < N; j++) {
				int[] tmp = new int[N];
				for (int i = 0; i < N; i++) {
					tmp[i] = map[i][j];
				}
				if (check(tmp)) {
					sum++;
				}
			}

			
			
			sb.append(String.format("#%d %d%n", t, sum));
		}
		System.out.println(sb);
		
//		System.out.println("여기부터 테스ㅔ트 ------------");
//		int[] test = { 4, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 5, 5, 5, 5, 5, 5, 4, 4, 4 };

//		check(test);
	}

	static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}
