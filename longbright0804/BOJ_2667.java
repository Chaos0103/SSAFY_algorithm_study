import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * SSAFY 알고리즘 스터디 4일차 - 단지번호붙이기 23:40 시작
 * 25분 소요
 * @author YoungHwan
 *
 */
public class BOJ_2667 {
	static int n;
	static int count;	// 단지 내 집의 개수

	static ArrayList<Integer> answer;	// 정답 리스트
	
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static int[][] map;				// 지도
	static boolean[][] visited;		// 방문배열

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 지도의 크기 입력
		n = sc.nextInt();

		// 배열 초기화 및 집 위치 입력
		map = new int[n][n];
		visited = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			String input = sc.next();
			for (int j = 0; j < n; j++) {
				map[i][j] = input.charAt(j) - '0';
			}
		}

		// 미방문 노드이고 집이 있는 곳이면 dfs 수행, 정답 리스트에 단지 내 집 개수 추가
		answer = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j] && map[i][j] == 1) {
					count = 1;
					dfs(i, j);
					answer.add(count);
				}
			}
		}
		Collections.sort(answer);
		System.out.println(answer.size());
		for (int i : answer) {
			System.out.println(i);
		}
	}

	static void dfs(int x, int y) {
		// 방문 처리
		visited[x][y] = true;

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || nx > n - 1 || ny < 0 || ny > n - 1) {
				continue;
			}
			// 미방문 노드이고 집이 있으면 dfs 수행, 종료 시 집 개수 1 증가
			if (!visited[nx][ny] && map[nx][ny] == 1) {
				dfs(nx, ny);
				count++;
			}
		}
	}
}
