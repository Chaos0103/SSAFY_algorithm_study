import java.util.Arrays;
import java.util.Scanner;

/**
 * 숫자 카드
 * 해결 5분
 * ### 코드 최적화
 * @author SSAFY
 *
 */
public class BOJ_10815 {
	static int n, m;
	static int[] arr;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		n = sc.nextInt();
		arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		Arrays.sort(arr);
		
		m = sc.nextInt();
		for (int i = 0; i < m; i++) {
			sb.append(binarySearch(sc.nextInt()) + " ");
		}
		System.out.println(sb.toString());
	}
	
	static int binarySearch(int key) {
		int start = 0;
		int end = arr.length - 1;
		
		while (start <= end) {
			int mid = (end + start) / 2;
			if (arr[mid] < key) {
				start = mid + 1;
			} else if (key < arr[mid]) {
				end = mid - 1;
			} else {
				return 1;
			}
		}
		return 0;
	}
}
