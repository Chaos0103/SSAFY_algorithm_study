import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * SSAFY 알고리즘 스터디 11일차 - ATM
 * 5분
 * 접근
 * 1. 배열 정렬
 * 2. 누적합 구함
 * 3. 누적합의 합계 구함
 * @author SSAFY
 *
 */
public class BOJ_11399 {
	static int n;
	static int sum;
	static int[] arr;
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 배열의 길이 입력
		n = Integer.parseInt(br.readLine());
		// 배열 생성 및 초기화
		arr = new int[n+1];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		// 1. 배열 정렬
		Arrays.sort(arr);
		// 2~3. 누적합 계산하며 총합 계산
		for (int i = 1; i <= n; i++) {
			arr[i] += arr[i-1];
			sum += arr[i];
		}
		System.out.println(sum);
	}
}
