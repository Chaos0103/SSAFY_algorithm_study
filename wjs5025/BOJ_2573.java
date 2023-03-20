import java.io.*;
import java.util.*;

/*
year = 0;

while (섬개수가 2개이상이 될 때까지)
	year ++;
	녹히기
	섬개수 체크 (dfs)

시간초과로 솔루션참고...
 */
class Pos {
	int r;
	int c;

	public Pos(int r, int c) {
		super();
		this.r = r;
		this.c = c;
	}

	@Override
	public String toString() {
		return "Pos [r=" + r + ", c=" + c + "]";
	}

}

public class Main {
	static int N, M;
	static int[][] map;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static boolean[][] visited;
	static int year = 0;
	static boolean allZero = false;

	// 빙산 개수세기
	public static int countIsland() {
		boolean[][] visited = new boolean[N][M];

		int cnt = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j] && map[i][j] > 0) {
					dfs(i, j, visited);
					cnt++;
				}
			}
		}

		return cnt;
	}

	public static void dfs(int x, int y, boolean[][] visited) {
		visited[x][y] = true;

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
				if (!visited[nx][ny] && map[nx][ny] > 0) {
					dfs(nx, ny, visited);
				}
			}
		}
	}

	// 녹히기
	static void melting() {
		Deque<Pos> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] > 0) {
                    q.add(new Pos(i, j));
                    visited[i][j] = true;
                }
            }
        }
		
		while (!q.isEmpty()) {
			Pos current = q.poll();
			
			int seaCnt = 0; // 빙산 상하좌우에 존재하는 바닷물 영역의 개수
			
			for (int i = 0; i < 4; i++) {
				int nx = current.r + dx[i];
				int ny = current.c + dy[i];
				
				if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
					if (!visited[nx][ny] && map[nx][ny] == 0) {
						seaCnt++;
					}
				}
			}
			
			if (map[current.r][current.c] - seaCnt < 0) {
				map[current.r][current.c] = 0;
			} else {
				map[current.r][current.c] -= seaCnt;
			}
		}
	}

	static boolean checkAllZero(int[][] map) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0)
					continue;
				else
					return false;
			}
		}
		allZero = true;
		return true;
	}


	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		while (true) {
			int result = countIsland();

			if (result >= 2) {
				break;
			} else if (result == 0) {
				year = 0;
				break;
			}

			melting();
			year++;
		}
		System.out.println(year);

	}

	static void print(int[][] map) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}
