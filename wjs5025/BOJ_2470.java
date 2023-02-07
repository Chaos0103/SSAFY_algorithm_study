package BJ2470;

import java.util.*;
import java.io.*;

/*
 * 미 해 결
 * 
 * 시간초과를 해결하지 못했습니다.
 * 늦어도 이번 주말까지는 재시도 해보겠습니다...
 * 
 * */

public class Main {
	static int N;
	static int[] dissolution;
	static int min = Integer.MAX_VALUE;
	static StringBuilder sb = new StringBuilder();

	public static void search(int l, int r) {
		for (int i = 0; i < N; i++) {
			for (int j = N - 1; j >= 0; j--) {
				if (i < j) {
					int result = dissolution[j] + dissolution[i];
					if (result == 0) {
						sb.setLength(0);
						sb.append(dissolution[i]).append(" ");
						sb.append(dissolution[j]);
						return;
					} else if (min > Math.abs(result)) {
						min = Math.abs(result);
						sb.setLength(0);
						sb.append(dissolution[i]).append(" ");
						sb.append(dissolution[j]);
					} else {
						if (result < 0) i++;
						else continue;
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		dissolution = new int[N];
		String[] line = br.readLine().split(" ");
		
		for (int i = 0; i < N; i++) {
			dissolution[i] = Integer.parseInt(line[i]);
		}

		
		Arrays.sort(dissolution);
		search(0, N);
		System.out.println(sb.toString());
	}
}