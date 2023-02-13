import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int N, M;
	static int[] nums;
	static StringBuilder sb = new StringBuilder();

	public static int binarySearch(int target, int low, int high) {
		if (low <= high) {
			int mid = (low + high) / 2;
			if (target == nums[mid])
				return 1;
			if (target < nums[mid]) {
				return binarySearch(target, low, mid - 1);
			} else {
				return binarySearch(target, mid + 1, high);
			}
		}

		return 0;
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();

		nums = new int[N];
		for (int i = 0; i < N; i++) {
			nums[i] = sc.nextInt();
		}
		Arrays.sort(nums);
		M = sc.nextInt();

		for (int i = 0; i < M; i++) {
			int num = sc.nextInt();
			sb.append(binarySearch(num, 0, N - 1)).append(" ");
		}

		System.out.println(sb.toString());
	}
}