import java.io.*;

/**
 * 생각해보면 쉬운문제인데,
 * 왜 돌려돌려 어렵게 생각했나 싶은 문제..
 */
public class Main {
	static int N, M;
	static int[] nums;
	static int max = Integer.MIN_VALUE;

	public static void twoPointerSearch() {
		for (int i = 0; i <= N - M; i++) {
			int intervalSum = 0;
			for (int j = i; j < i + M; j++) {
				intervalSum += nums[j];
			}
			max = Math.max(max, intervalSum);
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
		System.out.println(max);
	}
}public class BOJ_2559 {
    
}
