import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import static java.lang.Integer.parseInt;
/**
 * SSAFY 알고리즘 스터디 10일차 - 두 수의 합
 * 30분 소요
 * @author SSAFY
 *
 */
public class BOJ_3273 {
	static int n,x;
	static int count = 0;
	static int[] arr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// n, 배열 원소 입력
		n =  parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		// 배열 초기화
		arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		// x 입력
		x = parseInt(br.readLine());
		getCount();
		System.out.println(count);
	}
	
	static void getCount() {
		int start = 0;
		int end = arr.length - 1;
		int sum = 0;
		// 양 끝점에서 시작
		while (start < end) {
			sum = arr[start] + arr[end];
			if (sum == x) {	// 합이 x면 end 이동, count 증가
				end--;
				count++;
			} else if (sum > x) {	// 합이 x 이상이면 end 이동
				end--;
			} else if (sum < x) {	// 합이 x 미만이면 start 이동
				start++;
			}
		}
	}
}