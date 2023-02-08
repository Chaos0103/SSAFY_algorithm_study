import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import static java.lang.Integer.parseInt;

/**
 * SSAFY 알고리즘 스터디 9일차 - 수들의 합
 * 30분 소요
 * @author YoungHwan
 *
 */
public class BOJ_2003 {
	static int n, m;
	static int cnt = 0;
	static int[] arr;
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// n, m 입력 및 배열 생성
		st = new StringTokenizer(br.readLine(), " ");
		n = parseInt(st.nextToken());
		m = parseInt(st.nextToken());
		arr = new int[n];
		// 수열 입력
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < n; i++) {
			arr[i] = parseInt(st.nextToken());
		}
		// 부분합이 m 이 되는 경우의 수 계산
		System.out.println(getCount());
	}
	
	static int getCount() {
		int end = 0;
		int sum = 0;
		
		// 배열의 시작부터 끝까지 이동
		for (int start = 0; start < n; start++) {
			// end 를 가능한 만큼 이동
			while (sum < m && end < n) {
				// 부분합 증가
				sum += arr[end++];
			}
			// 부분합이 m 이면 count 값 증가
			if (sum == m) {
				cnt++;
			}
			// 기존 start 의 값은 빼줘야함
			sum -= arr[start];
		}
		return cnt;
	}
} 
