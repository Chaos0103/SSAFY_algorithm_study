import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * SSAFY 알고리즘 스터디 9일차 - 수열
 * 1시간 소요
 * 누적합이 아직 익숙치 않아 누적합 접근을 못해서 시간초과 발생했음
 * @author YoungHwan
 *
 */
public class BOJ_2559 {
	static int n, k;
	static int[] arr, sum;
	static int max = Integer.MIN_VALUE;
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// n, k 입력
		st = new StringTokenizer(br.readLine(), " ");
		n = parseInt(st.nextToken());
		k = parseInt(st.nextToken());
		// 배열 생성 및 초기화
		arr = new int[n + 1];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= n; i++) {
			arr[i] = parseInt(st.nextToken()) + arr[i - 1];
		}
		// 최댓값 갱신
		getMax();
		System.out.println(max);
	}

	static void getMax() {
		for (int start = 1; start <= n-k+1; start++) {
			int end = start + k - 1;
			max = Math.max(arr[end] - arr[start - 1], max);
//			System.out.printf("start: %d | end: %d | max: %d\n", start, end, max);
		}
	}
}
