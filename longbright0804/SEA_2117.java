package algorithm_study.day28;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <pre>
 * 홈 방범 서비스
 * 접근
 * 1. BFS 사용
 * 2. 큐에 넣을때마다 서비스 영역 크기 증가
 * 3. 큐에서 꺼낼때마다 결과값 갱신
 * 
 * 풀이 소요: 30분
 * 문제를 제대로 안 읽어서 안 풀었었는데
 * 이거 먼저 풀걸 그랬음
 * </pre>
 * 
 * @author YoungHwan
 *
 */
public class SEA_2117 {

	static class Pos {
		int r;
		int c;
		int k; // 서비스 영역

		/**
		 * @param r
		 * @param c
		 * @param k
		 */
		public Pos(int r, int c, int k) {
			super();
			this.r = r;
			this.c = c;
			this.k = k;
		}

	}

	// 방향 배열
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static int T, N, M, result; // 테스트 케이스 개수, 지도 크기, 지불 가능한 비용, 결과
	static int[][] map; // 지도
	static boolean[][] used; // 방문배열
	static Queue<Pos> q; // BFS 큐

	public static void main(String[] args) throws IOException, NumberFormatException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			init(br);
			// 각 위치에서 BFS 수행
			process();
			setPrint(sb, tc);
		}
		print(sb);
	}

	private static void init(BufferedReader br) throws IOException {
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		result = Integer.MIN_VALUE;
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	private static void process() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				used = new boolean[N][N]; // 방문 배열 초기화
				bfs(new Pos(i, j, 1));
			}
		}
	}

	private static void bfs(Pos start) {
		q = new LinkedList<>();
		q.add(start);
		used[start.r][start.c] = true;
		int count = 0;
		while (!q.isEmpty()) {
			Pos now = q.poll();
			// 현 위치가 집이면 집의 개수 증가
			count = countHouse(count, now.r, now.c);
			int profit = getProfit(count, now.k);
			// 손해가 발생하지 않으면 결과 갱신
			updateResult(count, profit);
			// 4 방 탐색
			for (int i = 0; i < 4; i++) {
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];
				// 범위를 벗어나면 탐색안함
				if (isOutOfRange(nr, nc)) {
					continue;
				}
				// 이미 방문한 지점은 탐색안함
				if (used[nr][nc]) {
					continue;
				}
				// 탐색 가능하면 영역 크기 1 증가시키고 큐에 추가
				used[nr][nc] = true;
				q.add(new Pos(nr, nc, now.k + 1));
			}
		}
	}

	private static int countHouse(int count, int r, int c) {
		if (map[r][c] == 1) {
			count++;
		}
		return count;
	}

	private static int getProfit(int count, int k) {
		// 운영 비용
		int price = k * k + (k - 1) * (k - 1);
		// 수익
		int income = count * M;
		int profit = income - price;
		return profit;
	}

	private static void updateResult(int count, int profit) {
		if (profit >= 0) {
			result = Math.max(count, result);
		}
	}

	private static boolean isOutOfRange(int nr, int nc) {
		return nr < 0 || nc < 0 || nr >= N || nc >= N;
	}

	private static void setPrint(StringBuilder sb, int tc) {
		sb.append("#").append(tc).append(" ").append(result).append("\n");
	}

	private static void print(StringBuilder sb) {
		System.out.println(sb);
	}

}
