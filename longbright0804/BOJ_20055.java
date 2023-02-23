package algorithm_study.day21;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SSAFFY 알고리즘 스터디 21일차 - 컨베이어 벨트 위의 로봇
 * 1시간 - 솔루션
 * 내리는 위치의 로봇을 내리지 않아서 틀림
 * @author YoungHwan
 *
 */
public class BOJ_20055 {
	static int n, k, level, count, belt[]; // 컨베이어 벨트 길이, 종료 조건 내구도 개수, 현재 단계, 내구도가 0인 칸의 개수, 컨베이어 벨트
	static boolean used[]; // 로봇 위치 배열

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// n, k 입력
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		// 벨트, 로봇위치 배열 생성
		belt = new int[2 * n];
		used = new boolean[n];

		// 벨트 내구도 입력
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 2 * n; i++) {
			belt[i] = Integer.parseInt(st.nextToken());
		}

		// 수행 시작
		process();
		System.out.println(level);
	}

	// 절차 수행 메소드
	private static void process() {
		while (true) {
			// 내구도가 0인 칸이 k개면 종료
			if (!isValid())
				return;
			// 벨트 회전
			rotate();
			// 로봇 이동
			move();
			// 로봇 추가
			add();
			// 단계 증가
			level++;
		}
	}

	// 내구도가 0인 칸의 개수가 k개인지 검사
	private static boolean isValid() {
		count = 0;
		for (int i = 0; i < belt.length; i++) {
			if (belt[i] == 0) {
				count++;
			}
			if (count >= k) return false;
		}
		
		return true;
	}

	// 로봇 추가 메소드
	private static void add() {
		// 올리는 위치의 내구도가 0보다 크면 로봇 추가
		if (belt[0] > 0) {
			used[0] = true;
			belt[0]--;
		}
	}

	// 로봇 이동 메소드
	private static void move() {
		used[n-1] = false;	// 내려가는 위치의 로봇은 내려야함(여기를 안해줬음)
		for (int i = n-1; i > 0; i--) {
			if (used[i - 1] && !used[i] && belt[i] > 0) {
				used[i] = true;
				used[i - 1] = false;
				belt[i]--;
			}
		}
	}

	// 벨트 회전
	private static void rotate() {
		int temp = belt[belt.length - 1];
		for (int i = belt.length - 1; i > 0; i--) {
			belt[i] = belt[i - 1];
		}
		belt[0] = temp;
		// 로봇도 같이 회전
		for (int i = used.length - 1; i > 0; i--) {
			used[i] = used[i - 1];
		}
		used[0] = false;
	}
}
