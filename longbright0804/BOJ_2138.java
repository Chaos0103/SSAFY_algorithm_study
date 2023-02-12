import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * SSAFY 알고리즘 스터디 12일차 - 전구와 스위치
 * 1시간 풀었음
 * 왜 틀린지 모르겠음
 * 내일 다시 도전
 * @author YoungHwan
 *
 */
public class BOJ_2138 {
	static int n;
	static int result;
	static String input;
	static int[] state, target;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 개수 입력 및 배열 생성
		n = Integer.parseInt(br.readLine());
		state = new int[n+2];
		target = new int[n+2];
		
		// 전구의 현재 상태와 목표 상태 입력
		input = br.readLine();
		for (int i = 1; i <= n; i++) {
			state[i] = input.charAt(i-1) - '0';
		}
		input = br.readLine();
		for (int i = 1; i <= n; i++) {
			target[i] = input.charAt(i-1) - '0';
		}
		
		// 탐색
		for (int i = 1; i <= n; i++) {
			// 맨 마지막 인덱스이거나 맨 처음 인덱스의 경우 상태 변경
			state[i] = state[i] ^ 1;
			state[i + 1] = state[i + 1] ^ 1;
			state[i - 1] = state[i - 1] ^ 1;
			result++;
//			System.out.println("state: " + Arrays.toString(state));
			// 원하는 상태를 만든경우 종료
			if (isValid()) {
				System.out.println(result);
				return;
			}
		}
		// 원하는 상태를 만들지 못했을 경우
		System.out.println(-1);
	}
	
	private static boolean isValid() {
		for (int i = 1; i <= n; i++) {
			if (state[i] != target[i]) {
				return false;
			}
		}
		return true;
	}
}
