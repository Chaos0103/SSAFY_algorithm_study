import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	private static int n, m, k, d = 1;
	private static int[][] map;
	private static int[] dx = { -1, 0, 1, 0 }; // 북동남서
	private static int[] dy = { 0, 1, 0, -1 };
	private static int[] reverseDirection = { 2, 3, 0, 1 }; // 남서북동
	private static int[][] dice = { { 0, 2, 0 }, { 4, 1, 3 }, { 0, 5, 0 }, { 0, 6, 0 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		Point point = new Point(0, 0);
		int result = 0;
		for (int i = 0; i < k; i++) {
			point = moveDice(point.x, point.y);
			result += getScore(point.x, point.y);
			turnDirection(point.x, point.y);
		}

		System.out.println(result);
	}

	// 1. 주사위가 이동 방향으로 한 칸 굴러간다. 만약, 이동 방향에 칸이 없다면, 이동 방향을 반대로 한 다음 한 칸 굴러간다.
	private static Point moveDice(int x, int y) {
		int nx = x + dx[d];
		int ny = y + dy[d];
		if (!isRange(nx, ny)) {
			d = reverseDirection[d];
			nx = x + dx[d];
			ny = y + dy[d];
		}

		switch (d) {
		case 0:
			diceUp();
			break;
		case 1:
			diceRight();
			break;
		case 2:
			diceDown();
			break;
		case 3:
			diceLeft();
			break;
		}

		return new Point(nx, ny);
	}

	// 2. 주사위가 도착한 칸 (x, y)에 대한 점수를 획득한다.
	private static int getScore(int x, int y) {
		boolean[][] visited = new boolean[n][m];
		int target = map[x][y];
		int count = 1;

		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(x, y));
		visited[x][y] = true;
		while (!q.isEmpty()) {
			Point point = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = point.x + dx[i];
				int ny = point.y + dy[i];
				if (!isRange(nx, ny)) {
					continue;
				}
				if (map[nx][ny] != target) {
					continue;
				}
				if (visited[nx][ny]) {
					continue;
				}
				count++;
				q.offer(new Point(nx, ny));
				visited[nx][ny] = true;
			}
		}

		return target * count;
	}

	// 3. 주사위의 아랫면에 있는 정수 A와 주사위가 있는 칸 (x, y)에 있는 정수 B를 비교해 이동 방향을 결정한다.
	// A > B인 경우 이동 방향을 90도 시계 방향으로 회전시킨다.
	// A < B인 경우 이동 방향을 90도 반시계 방향으로 회전시킨다.
	// A = B인 경우 이동 방향에 변화는 없다.
	private static void turnDirection(int x, int y) {
		int diceBottomNumber = dice[3][1];
		if (diceBottomNumber > map[x][y]) {
			d++;
			d %= 4;
		} else if (diceBottomNumber < map[x][y]) {
			d--;
			if (d < 0) {
				d = 3;
			}
		}
	}

	// == 범위 체크 ==//
	private static boolean isRange(int x, int y) {
		return 0 <= x && x < n && 0 <= y && y < m;
	}

	// ==주사위 이동==//
	// 북쪽 이동
	private static void diceUp() {
		int temp = dice[0][1];
		dice[0][1] = dice[1][1];
		dice[1][1] = dice[2][1];
		dice[2][1] = dice[3][1];
		dice[3][1] = temp;
	}

	// 남쪽 이동
	private static void diceDown() {
		int temp = dice[3][1];
		dice[3][1] = dice[2][1];
		dice[2][1] = dice[1][1];
		dice[1][1] = dice[0][1];
		dice[0][1] = temp;
	}

	// 동쪽 이동
	private static void diceRight() {
		int temp = dice[3][1];
		dice[3][1] = dice[1][2];
		dice[1][2] = dice[1][1];
		dice[1][1] = dice[1][0];
		dice[1][0] = temp;
	}

	// 서쪽 이동
	private static void diceLeft() {
		int temp = dice[3][1];
		dice[3][1] = dice[1][0];
		dice[1][0] = dice[1][1];
		dice[1][1] = dice[1][2];
		dice[1][2] = temp;
	}
}
