package algorithm_study.day25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <pre>
 * SSAFY 알고리즘 스터디 25일차
 * 마법사 상어와 파이어볼
 * <주요수행과정>
 * 1. 각각의 파이어볼들은 자신의 방향으로 속력만큼의 칸으로 이동
 * 2. 2개 이상의 파이어볼이 존재하는 칸의 파이어볼은 하나로 합쳐짐
 * 3. 4개의 파이어볼로 분리
 *    - 질량: 질량의 합 / 5
 *    - 속력: 속력의 합 / 파이어볼의 개수
 *    - 방향이 모두 홀수이거나 짝수 -> 각각 0,2,4,6 
 *    - 모두 다르면 1,3,5,7
 * 4. 질량이 0인 파이어볼은 소멸되어 사라짐
 * 
 * <접근>
 * 사용 알고리즘: BFS
 * 최초 접근
 * 1. 격자 배열 정수형 선언 -> 파이어볼 개수 저장
 * 2. 방향 배열은 8방향(문제에서 제시된 인덱스 번호대로 선언)
 * 3. 파이어볼 클래스를 만들고, 입력받은 파이어볼들을 큐에 넣음
 * 4. 파이어볼 이동 명령마다 BFS 수행
 * => 문제점: 파이어볼 정보는 큐에 있는데? => 폐기
 * 
 * 2차 접근
 * 1. 2차원 큐를 사용 -> 격자 구현
 * 2. 동일
 * 3. 파이어볼 클래스를 만들고, 리스트를 생성함
 * 4. 리스트의 파이어볼들을 이동시켜서 큐에 넣음
 * 5. size 가 2 이상인 큐들은 분리 작업을 수행(이때 리스트의 파이어볼 삭제)
 * 6. 분리된 파이어볼들을 리스트에 전부 다 추가
 * 
 * <결론>
 * 실패
 * 2차 접근은 맞았으나, 리스트의 파이어볼을 삭제하는 과정에서 리스트의 파이어볼이 삭제되지 않음
 * 계속 객체를 새로 생성하면서 진행한 것이 원인인 것 같음
 * 해결방안
 * 1. private 이랑 getter setter 미사용하고 바로 멤버 변수로 접근
 * </pre>
 * 
 * @author YoungHwan
 *
 */
public class BOJ_20056 {
	// 파이어볼의 정보
	static class Fire {
		private int r; // 행
		private int c; // 열
		private int mass; // 질량
		private int speed; // 속도
		private int direction; // 방향

		public Fire(int r, int c, int mass, int speed, int direction) {
			super();
			this.r = r;
			this.c = c;
			this.mass = mass;
			this.speed = speed;
			this.direction = direction;
		}

		public int getR() {
			return r;
		}

		public int getC() {
			return c;
		}

		public int getMass() {
			return mass;
		}

		public int getSpeed() {
			return speed;
		}

		public int getDirection() {
			return direction;
		}

	}

	// 방향 배열(상, 우상, 우, 우하, 하, 좌하, 좌, 좌상)
	static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static int[][] dirIndex = { { 0, 2, 4, 6 }, { 1, 3, 5, 7 } };

	static int N, M, K; // 격자의 크기, 초기 파이어볼의 개수, 명령의 개수
	static int result; // 남아있는 파이어볼의 총 질량
	static Queue<Fire>[][] grid; // 격자
	static ArrayList<Fire> list = new ArrayList<>(); // 파이어볼 리스트

	// 입출력
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	public static void main(String[] args) throws IOException {
		process();
	}

	private static void process() throws IOException {
		// 입력
		input();
		// 이동명령 수행
		for (int k = 0; k < K; k++) {
			// 리스트의 파이어볼 이동
			moveFires();
			// 파이어볼 2개 이상인지 확인
			checkFire();
		}
		// 리스트에 남은 파이어볼의 질량 합산
		getResult();
		// 결과 출력
		print();
	}

	@SuppressWarnings("unchecked")
	private static void input() throws IOException {
		// 격자 정보, 파이어볼 개수, 명령 개수 입력, 격자 생성
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		grid = new LinkedList[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				grid[i][j] = new LinkedList<>();
			}
		}
		// 파이어볼 정보 입력
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int mass = Integer.parseInt(st.nextToken());
			int speed = Integer.parseInt(st.nextToken());
			int direction = Integer.parseInt(st.nextToken());
			// 리스트 추가
			list.add(new Fire(r, c, mass, speed, direction));
		}
	}

	// 파이어볼 이동
	private static void moveFires() {
		for (Fire f : list) {
			int r = f.getR();
			int c = f.getC();
			int m = f.getMass();
			int s = f.getSpeed();
			int d = f.getDirection();
			// 모든 파이어볼이 자신의 방향으로 속력칸 만큼 이동
			int nr = (N + r + dr[d] * (s % N)) % N;
			int nc = (N + c + dc[d] * (s % N)) % N;
			// 큐에 추가
			grid[nr][nc].add(new Fire(nr, nc, m, s, d));
		}
	}

	// 파이어볼 수량 체크
	private static void checkFire() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 2개 이상이면 분리
				Queue<Fire> q = grid[i][j];
				int size = q.size();
				if (size >= 2) {
					splitFire(i, j, q, size);
				} else {
					q.clear(); // 큐 초기화
				}
			}
		}
	}

	// 파이어볼 분리
	private static void splitFire(int i, int j, Queue<Fire> q, int size) {
		int massSum = 0; // 질량 합
		int speedSum = 0; // 속도 합
		boolean isOdd = true; // 홀수 여부
		boolean isEven = true; // 짝수 여부
		// 큐가 빌때까지 수행(파이어볼 합치기)
		while (!q.isEmpty()) {
			Fire now = q.poll();
			massSum += now.getMass();
			speedSum += now.getSpeed();
			if (now.getDirection() % 2 == 0) {
				isOdd = false;
			} else {
				isEven = false;
			}
			list.remove(now); // 리스트에서 제거
		}
		// 리스트 추가
		addFires(i, j, size, massSum, speedSum, isOdd, isEven);
	}

	// 새로 분리된 4개의 파이어볼을 리스트에 추가
	private static void addFires(int i, int j, int size, int massSum, int speedSum, boolean isOdd, boolean isEven) {
		int mass = massSum / 5;
		// 질량이 0인 경우 리스트에 추가하지 않음
		if (mass != 0) {
			int speed = speedSum / size;
			if (isOdd || isEven) {
				for (int d = 0; d < 8; d += 2) { // 방향 0,2,4,6
					list.add(new Fire(i, j, mass, speed, d));
				}
			} else {
				for (int d = 1; d < 8; d += 2) { // 1,3,5,7
					list.add(new Fire(i, j, mass, speed, d));
				}
			}
		}
	}

	// 리스트의 파이어볼 질량 합산
	private static void getResult() {
		for (Fire f : list) {
			result += f.getMass();
		}
	}

	// 출력
	private static void print() {
		for (Fire f : list) {
			System.out.println(f.getMass());
		}
		System.out.println(result);
	}
}