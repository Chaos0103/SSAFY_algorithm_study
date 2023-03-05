package algorithm_study.day28;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * <pre>
 * 접근
 * 사용 알고리즘: 재귀(constructRoad)
 * 최종: 1시간 23분 소요
 * 
 * 공사 한번만 하도록 하는 flag 변수를 까먹고 안썼음
 * 공사하고 복귀하는 과정에서 복귀값을 잘못 넣어줘서 오래걸림
 * 화가남
 * </pre>
 * 
 * @author YoungHwan
 *
 */
public class SEA_1949 {

	// 좌표 정보 클래스
	static class Pos {
		int r;
		int c;

		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Pos [r=");
			builder.append(r);
			builder.append(", c=");
			builder.append(c);
			builder.append("]");
			return builder.toString();
		}

	}

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static int T, N, K, maxDist; // 테스트 케이스 개수, 지도 크기, 최대 공사 가능 깊이
	static int[][] map; // 지도 배열
	static boolean[][] used; // 방문 배열
	static ArrayList<Pos> topList; // 제일 높은 봉우리 리스트

	// 입출력
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 테스트 케이스 입력
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			init(); // 입력 및 초기화
			solution(); // 수행
			setResult(tc); // 출력문 생성
		}
		print(); // 결과 출력
	}

	private static void init() throws IOException {
		// 값 입력 및 초기화
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		used = new boolean[N][N];
		topList = new ArrayList<>();
		maxDist = Integer.MIN_VALUE;
		// 지도 정보 입력
		int maxHeight = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// 가장 높은 봉우리 리스트 생성
				if (map[i][j] > maxHeight) {
					topList.clear();
					maxHeight = map[i][j];
					topList.add(new Pos(i, j));
				} else if (map[i][j] == maxHeight) {
					topList.add(new Pos(i, j));
				}
			}
		}
	}

	private static void solution() {
		for (int i = 0; i < topList.size(); i++) {
			Pos now = topList.get(i);
			constructRoad(now, 1, false);
		}
	}

	private static void constructRoad(Pos now, int dist, boolean isDone) {
		maxDist = Math.max(maxDist, dist);
		// 현재 위치 방문처리
		used[now.r][now.c] = true;
		// 네 방향 중 갈 수 있는 곳 선택
		for (int i = 0; i < 4; i++) {
			int nr = now.r + dr[i];
			int nc = now.c + dc[i];
			// 범위를 벗어나면 이동하지 않음
			if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
				continue;
			}
			// 이미 방문한 지점은 가지 않음
			if (used[nr][nc]) {
				continue;
			}
			int current = map[now.r][now.c]; // 현재 높이
			int next = map[nr][nc]; // 다음칸의 높이
			// 방문하지 않았고 현재 칸보다 낮은 칸이면 이동해봄
			if (next < current) {
				constructRoad(new Pos(nr, nc), dist + 1, isDone);
			}
			// 현재 경로에서 깎은적이 없는 경우 깎을 수 있으면 깎고 진행
			else {
				if (!isDone && current > next - K) {
					map[nr][nc] = current - 1;
					constructRoad(new Pos(nr, nc), dist + 1, true);
					map[nr][nc] = next;
				}
			}
		}
		// 이동이 종료되면 현재까지의 최대 경로 갱신
		used[now.r][now.c] = false;
	}

	private static void setResult(int tc) {
		sb.append("#").append(tc).append(" ").append(maxDist).append("\n");
	}

	private static void print() {
		System.out.println(sb);
	}

}
