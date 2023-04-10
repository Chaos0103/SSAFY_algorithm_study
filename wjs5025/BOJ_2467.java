import java.io.*;
import java.util.*;

/**
 * 솔루션을 참고했어요..
 * 추후에 다시...
 * 
 */
public class BOJ_2467 {
	static int N;
	static long[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		arr = new long[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}

		int low = 0;
		int high = N - 1;
		int ml = 0, mr = 0;
		long min = Long.MAX_VALUE;
		while (low < high) {
			long sum = arr[low] + arr[high];
			if (min > Math.abs(sum)) {
				min = Math.abs(sum);
				ml = low;
				mr = high;
			}
			if (sum >= 0) {
				high--;
			} else {
				low++;
			}
		}
		System.out.println(arr[ml] + " " + arr[mr]);
	}
}