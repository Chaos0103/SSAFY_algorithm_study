package algorithm_study.day24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <pre>
 * 시간내로 못푼 이유
 * 문제 이해하다보니 시간이 다 지남
 * </pre>
 * 
 * @author YoungHwan
 *
 */
public class BOJ_14891 {
	static int k, index, direction, result; // 회전 횟수, 톱니바퀴 번호, 회전 방향, 점수
	static int[] score = { 1, 2, 4, 8 }; // 톱니바퀴별 점수 배열
	static int[] used = new int[4]; // 사용배열
	static int[][] gear = new int[4][8]; // 톱니바퀴

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	public static void main(String[] args) throws Exception {
		// 변수 입력 및 초기화
		init();
		// 회전 정보 입력 후 회전
		process();
		// 점수 계산
		calculateScore();
		System.out.println(result);
	}

	// 회전 정보 입력 및 회전 메소드 호출
	private static void process() throws IOException {
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			index = Integer.parseInt(st.nextToken()) - 1;
			direction = Integer.parseInt(st.nextToken());
			Arrays.fill(used, 0); // 방문배열 초기화
			rotateGear(index, direction); // 회전 메소드 호출
		}
	}

	// 변수 입력 및 초기화
	private static void init() throws IOException {
		for (int i = 0; i < 4; i++) {
			String s = br.readLine();
			for (int j = 0; j < 8; j++) {
				gear[i][j] = s.charAt(j) - '0';
			}
		}
		k = Integer.parseInt(br.readLine());
	}

	// 점수 계산
	private static void calculateScore() {
		for (int i = 0; i < 4; i++) {
			if (gear[i][0] == 1) {
				result += score[i];
			}
		}
	}

	// 톱니바퀴 회전
	private static void rotateGear(int now, int dir) {
		// 현재 톱니바퀴 방문 처리
		used[now] = 1;
		// 왼쪽 회전이 가능하면 왼쪽 톱니바퀴 회전
		if (now - 1 >= 0 && used[now - 1] == 0) {
			if (gear[now][6] != gear[now - 1][2]) {
				rotateGear(now - 1, dir * -1);
			}
		}
		// 오른쪽 회전이 가능하면 오른쪽 톱니바퀴 회전
		if (now + 1 <= 3 && used[now + 1] == 0) {
			if (gear[now][2] != gear[now + 1][6]) {
				rotateGear(now + 1, dir * -1);
			}
		}
		// 현재 톱니바퀴 회전
		// 시계방향일 경우
		if (dir == 1) {
			turnRight(now);
		}
		// 반시계 방향일 경우
		else {
			turnLeft(now);
		}
	}

	private static void turnLeft(int now) {
		int temp = gear[now][0];
		for (int i = 0; i < 7; i++) {
			gear[now][i] = gear[now][i + 1];
		}
		gear[now][7] = temp;
	}

	private static void turnRight(int now) {
		int temp = gear[now][7];
		for (int i = 7; i > 0; i--) {
			gear[now][i] = gear[now][i - 1];
		}
		gear[now][0] = temp;
	}
}