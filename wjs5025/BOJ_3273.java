import java.util.*;
import java.io.*;


/**
 * 어렵다.. 투포인터 너란녀석...
 * 60분 소요
 * 
 */
public class Main {
	static int n, x;
	static int[] nums;
	static int cnt;

	static void twoPointerSearch() {
		int start = 0;
		int end = n - 1;

		while (start < end) {
			int tmp = nums[start] + nums[end];
			
			if (tmp == x) {
				cnt++;
			}

			if (tmp > x) {
				end--;
			} else {
				start++;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		nums = new int[n];

		for (int i = 0; i < n; i++) {
			nums[i] = sc.nextInt();
		}

		x = sc.nextInt();

		// 배열 정렬
		Arrays.sort(nums);

		// 3가지 경우에 따라 투포인터 탐색
		twoPointerSearch();
		System.out.println(cnt);
	}
}