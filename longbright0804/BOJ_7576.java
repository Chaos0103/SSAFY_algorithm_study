import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * SSAFY 알고리즘 스터디 4일차 - 토마토 21:55 시작
 * 1 시간 소요 - 틀림
 * 틀린 이유
 * 최초 접근: map 을 순회하여 익은 토마토가 있을 경우 bfs 호출
 * 정답 접근: bfs 호출이 아니라 큐에 해당 위치를 넣고 그 후에 bfs 호출
 * 
 * 큐를 배열로하는 것이 아니라, Position 클래스를 선언해서 사용하는 것도 나쁘지 않아보임
 * @author YoungHwan
 *
 */
public class BOJ_7576 {

	static int n, m;
	static int answer = Integer.MIN_VALUE;

	static int[][] map;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static Queue<int[]> q = new LinkedList<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		m = sc.nextInt();
		n = sc.nextInt();

		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		// map 을 순회하여 익은 토마토가 있을 경우 큐에 삽입
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 1) {
					q.offer(new int[] {i, j});
				}
			}
		}
		
		bfs();
		
		// 배열에 0 이 남아 있으면 -1 출력
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 0) {
					System.out.println(-1);
					return;
				}
				answer = Math.max(answer, map[i][j]);
			}
		}
		System.out.println(answer - 1);
	}

	static void bfs() {
		while (!q.isEmpty()) {
			int[] now = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = now[0] + dx[i];
				int ny = now[1] + dy[i];

				// 범위를 벗어나거나 -1 또는 1일 경우 수행 X
				if (nx < 0 || nx > n - 1 || ny < 0 || ny > m - 1 || map[nx][ny] == -1 || map[nx][ny] == 1) {
					continue;
				}
				// 안 익은 토마토인 경우 익은 토마토로 변경하고 큐에 추가
				if (map[nx][ny] == 0) {
					map[nx][ny] = map[now[0]][now[1]] + 1;
					q.offer(new int[] {nx, ny});
				}
			}
		}
	}
}
