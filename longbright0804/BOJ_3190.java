import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * SSAFY 알고리즘 스터디 15일차 - 뱀
 * 1시간
 * @author SSAFY
 *
 */
public class BOJ_3190 {
	// 방향 배열
	static final int[] dx = {-1, 0, 1, 0};
	static final int[] dy = {0, 1, 0, -1};
	
	static int n, k, l;		// 보드의 크기, 사과의 개수
	static int direction = 1;	// 방향변수
	static int answer = 0;	// 게임 진행 시간
	static int[][] board;	// 보드
	static HashMap<Integer, Character> map = new HashMap<>();	// 전환할 방향 정보 맵

	// 머리와 꼬리 위치
	static Deque<Position> q = new LinkedList<>();
	
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 보드판의 크기 및 사과의 개수 입력
		n = Integer.parseInt(br.readLine());
		k = Integer.parseInt(br.readLine());
		// 배열 생성 및 사과 위치 입력
		board = new int[n+1][n+1];
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			board[x][y] = 1;	// 사과는 1로 표시
		}
		// 방향 변환 횟수만큼 방향 변환하며 이동
		q.addFirst(new Position(1,1));
		board[1][1] = -1;
		l = Integer.parseInt(br.readLine());
		for (int i = 0; i < l; i++) {			
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			char c = st.nextToken().charAt(0);
			map.put(x, c);
		}
		move();
		System.out.println(answer);
	}

	private static void move() {
		while (true) {
			// 총 시간 증가
			answer++;
			// 머리 이동 준비
			Position head = q.peekFirst();
			int nx = head.getX() + dx[direction];
			int ny = head.getY() + dy[direction];
			// 범위를 벗어나거나(벽) 몸과 닿으면 -> 종료
			if (nx < 1 || ny < 1 || nx > n || ny > n || board[nx][ny] == -1) {
				return;
			}
			// 사과가 없을 경우 꼬리 위치 비워줌
			if (board[nx][ny] == 0) {
				board[nx][ny] = -1;	// 이동 처리
				Position tail = q.pollLast();
				board[tail.getX()][tail.getY()] = 0;
				q.addFirst(new Position(nx, ny));
			} else {
				// 사과가 있을 경우
				board[nx][ny] = -1;	// 이동 처리
				q.addFirst(new Position(nx, ny));
			}
			// 방향 전환 시점 도달 시 방향 전환
			if (map.containsKey(answer)) {
				turn(map.get(answer));
			}
		}
	}

	private static void turn(char c) {
		if (c == 'L') {
			direction--;
			if (direction == -1) {
				direction = 3;
			}
		} else {
			direction++;
			if (direction == 4) {
				direction = 0;
			}
		}
	}
}

class Position {
	private int x;
	private int y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	
}