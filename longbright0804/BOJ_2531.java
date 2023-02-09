import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * SSAFY 알고리즘 스터디 10일차 - 회전 초밥
 * 접근
 * 1. 크기 d 만큼의 초밥 번호별 개수 배열 사용
 * 2. 슬라이딩 윈도우 알고리즘을 통해 연속된 k개의 초밥을 먹을 경우 종류 개수 계산
 * 3. 쿠폰 번호인 초밥은 따로 처리를 해준다
 * 
 * @author SSAFY
 *
 */
public class BOJ_2531 {
	static int n, d, k, c;
	static int cnt;
	static int[] dishes;
	static int[] count;
	static int max = Integer.MIN_VALUE;

	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// n, d, k, c 입력
		st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		// 배열 생성
		dishes = new int[n];
		count = new int[d+1];	// 1부터 d까지 저장
		
		// 초밥 벨트의 초밥 입력
		for (int i = 0; i < n; i++) {
			dishes[i] = Integer.parseInt(br.readLine());
		}
	}
}
