import java.util.Scanner;

/**
 * SSAFY 알고리즘 스터디 4일차 - 섬의 개수 23:10 시작
 * 25분 소요
 * @author YoungHwan
 *
 */
public class BOJ_4963 {

	static int w, h;
	static int answer;

	static int[] dx = { 0, 0, 1, -1, -1, 1, -1, 1 };
	static int[] dy = { 1, -1, 0, 0, -1, 1, 1, -1 };

	static int[][] map;
	static boolean[][] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			w = sc.nextInt();
			h = sc.nextInt();
			if (w == 0 && h == 0) {
				return;
			}
			
			// 지도 입력 및 배열 초기화
			map = new int[h][w];
			visited = new boolean[h][w];
			answer = 0;
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			// 미방문 노드이고 땅이면 dfs 수행
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (!visited[i][j] && map[i][j] == 1) {
						// dfs 수행
						dfs(i, j);
						answer++;
					}
				}
			}
			System.out.println(answer);
		}
	}
	
	static void dfs(int x, int y) {
		visited[x][y] = true;
		
		for (int i = 0; i < 8; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || nx > h-1 || ny < 0 || ny > w-1) {
				continue;
			}
			if (!visited[nx][ny] && map[nx][ny] == 1) {
				dfs(nx, ny);
			}
		}
	}
}
