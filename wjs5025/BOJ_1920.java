import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static int[] nums;
	static StringBuilder sb = new StringBuilder();

	public static int BinarySearch(int target, int low, int high) {
		if (low <= high) {
			int mid = (low + high) / 2;
			
			if (target == nums[mid]) {
				return 1;
			}
			if (target < nums[mid]) {
				return BinarySearch(target, low, mid - 1);
			} else {
				return BinarySearch(target, mid + 1, high);
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
			sb.append(BinarySearch(num, 0, N - 1)).append("\n");
		}

		System.out.println(sb.toString());
	}
}