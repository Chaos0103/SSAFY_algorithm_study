package BOJ_2565;

import java.io.*;
import java.util.*;

// 60분 도전 후, 솔루션 ...
//  dp 이즈 투 하드..
class Lines implements Comparable<Lines> {
	int a;
	int b;

	@Override
	public String toString() {
		return "Lines [a=" + a + ", b=" + b + "]";
	}

	public Lines(int a, int b) {
		super();
		this.a = a;
		this.b = b;
	}

	@Override
	public int compareTo(Lines o) {
		return this.a - o.a;
	}
}

public class BOJ_2565 {
	static ArrayList<Lines> lines = new ArrayList<>();
	static int N;
	static int[] dp;
	static int INF = (int) 1e9;

	public static int electronic(int num) {

		if (dp[num] == INF) {
			dp[num] = 1;

			for (int i = num + 1; i < dp.length; i++) {
				if (lines.get(num).b < lines.get(i).b)
					dp[num] = Math.max(dp[num], electronic(i) + 1);
			}
		}

		return dp[num];
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		dp = new int[N];

		for (int i = 0; i < N; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			lines.add(new Lines(a, b));
		}

		Collections.sort(lines);

		Arrays.fill(dp, INF);

		int max = Integer.MIN_VALUE;

		for (int i = 0; i < N; i++) {
			max = Math.max(electronic(i), max);
		}

		System.out.println(N - max);
	}
}
