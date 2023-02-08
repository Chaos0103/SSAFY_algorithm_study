package BOJ2003;

import java.io.*;

public class Main {
	static int N, M;
	static int[] nums;
	static int cnt = 0;
	static int end = 0;
	static int intervalSum = 0;

	public static void twoPointerSearch() {
		for (int s = 0; s < N; s++) {
			while(intervalSum < M && end < N) {
				intervalSum += nums[end];
				end++;
			}
			if (intervalSum == M) {
				cnt++;
			}
			intervalSum -= nums[s];
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);

		nums = new int[N];
		input = br.readLine().split(" ");

		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(input[i]);
		}

		twoPointerSearch();

		System.out.println(cnt);
	}
}
