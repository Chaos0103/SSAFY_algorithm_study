package algorithm_study.day22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * SSAFY 알고리즘 스터디 22일차 - 벽돌깨기
 * 접근
 * 1. 모든 열에 대한 중복순열 생성
 * 2. 생성된 중복 순열들에 대해 최상단 행의 벽돌에 BFS 수행
 * 3. BFS 가 종료되면 남은 벽돌의 최솟값을 갱신
 * 1시간 소요 - 실패
 * </pre>
 * 
 * @author YoungHwan
 *
 */
public class SEA_5656 {
	// 방향 배열
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static int T, n, w, h, bricks; // 테이트케이스 개수, 구슬 개수, 영역의 크기, 벽돌 개수
	static int topRow; // 최상단 행
	static int[][] map; // 영역

	// 입출력
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			input(); // 입력
			// 구슬 이동하면서 벽돌 깨기
			for (int k = 0; k < n; k++) {
				findTopRow(); // 최상단 행 찾기
				// 최상단 행에서 벽돌을 찾아서 파괴하기
				int r = topRow;
				for (int direction = 0; direction < 4; direction++) {
					// 방향별로 벽돌에 적힌 수만큼 수행
					for (int c = 0; c < map[r][c]; c++) {
						int nr = r + dr[direction];
						int nc = c + dc[direction];
					}
				}
			}
		}
	}

	// 최상단 행 탐색
	private static void findTopRow() {
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				if (map[i][j] != 0) {
					topRow = i;
					return;
				}
			}
		}
	}

	private static void input() throws IOException {
		// 구슬 개수, 영역크기 입력
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		// 영역 생성하면서 벽돌의 총 개수 파악
		map = new int[h][w];
		for (int i = 0; i < h; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < w; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != 0) bricks++;
			}
		}
	}
}
