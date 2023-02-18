package algorithm_study.day18;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * SSAFY 알고리즘 스터디 16일차 - 인구이동 17:30 시작
 * 1시간 풀이, 솔루션
 * 또 최초 접근 맞게 잘 해놓고 구현 접근을 못함
 * 연합의 이동이 없을때까지, 모든 칸에 대하여 bfs 수행하는 것을 고려안하고 구현
 * 테케 1~3 번까지 맞고 4~5번 틀림
 * @author YoungHwan
 *
 */
public class BOJ_16234 {
	// 방향 배열(상,하,좌,우)
	static final int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	static int n, l, r; // 땅의 크기, 인구차 범위
	static int day; // 인구이동이 발생한 일수
	static int sum; // 각 연합들의 인구수 총합
	static int[][] map, used;

	static Queue<Pos> q;
	static ArrayList<Pos> list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 땅의 크기, 인구 차 범위 입력
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		// 2차원 배열 생성 및 초기화
		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// bfs 수행
		while (true) {
			used = new int[n][n];
			boolean isMove = false;

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (used[i][j] == 0) {
						bfs(new Pos(i, j));// 연합찾기
						if (list.size() > 1) {
							isMove = true;
						}
					}
				}
			}
			// 인구 이동이 없으면 종료
			if (!isMove) {
				System.out.println(day);
			}
			day++;
		}
	}

	private static void bfs(Pos start) {
		q = new LinkedList<>();
		list = new ArrayList<>();
		q.add(start);
		list.add(start);
		// 시작 노드는 일단 포함
		sum = map[start.getX()][start.getY()];
		used[start.getX()][start.getY()] = 1;
		// 큐가 빌때까지 수행
		while (!q.isEmpty()) {
			Pos now = q.poll(); // 현재 선택된 나라
			int x = now.getX();
			int y = now.getY();
			// 4방 탐색 수행
			for (int i = 0; i < 4; i++) {
				// 다음 좌표 값 계산
				int nx = x + dir[i][0];
				int ny = y + dir[i][1];
				// 범위를 벗어나면 넘어감
				if (nx < 0 || ny < 0 || nx >= n || ny >= n || used[nx][ny] == 1) {
					continue;
				}
				// 두 나라의 인구수 차이 계산
				int diff = Math.abs(map[x][y] - map[nx][ny]);
				// 연합을 이룰 수 있는 나라면 연합을 이룸
				if (l <= diff && diff <= r) {
					used[nx][ny] = 1;
					q.add(new Pos(nx, ny));
					list.add(new Pos(nx, ny));
					sum += map[nx][ny];
				}
			}
			// 인구 이동 시작(소속 국가가 2개 이상이면 연합)
			if (list.size() > 1) {
				int pop = sum / list.size(); // 연합의 인구수
				for (int i = 0; i < list.size(); i++) {
					map[list.get(i).getX()][list.get(i).getY()] = pop;
				}
			}
		}
	}
}

class Pos {
	private int x;
	private int y;

	/**
	 * @param x
	 * @param y
	 */
	public Pos(int x, int y) {
		super();
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
	 * @return the y
	 */
	public int getY() {
		return y;
	}

}
