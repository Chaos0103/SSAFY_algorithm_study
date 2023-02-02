import java.util.*;
import java.io.*;

/*  예제 입력 3
6 4
1 -1 0 0 0 0
0 -1 0 0 0 0
0 0 0 0 -1 0
0 0 0 0 -1 1

에서, 좌상단 1과 우하단 1로부터 파생되면,
중앙에 동시에 도달하게 되는데,
이 과정을 처리하는 방법을 모르겠습니다.

현재 코드의 경우 익은토마토 1개씩 수행하므로
반대쪽까지 가서 9일을 찍고 방문처리를 하게 해버려서
반대쪽 1에서 시작시 방문하지 못하고 그대로 9일이
남아있게 되어버렸습니다.
*/

class Tomato {
	int x;
	int y;
	int state;
	int day;

	public Tomato(int x, int y, int state, int day) {
		this.x = x;
		this.y = y;
		this.state = state;
		this.day = day;
	}
}

public class Main {
	static int N, M;
	static Tomato[][] box;
	static boolean[][] visited;
	static int[][] diff = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

	public static void bfs(int x, int y) {
		int[] now = { 0, 0 };
		Queue<Tomato> q = new LinkedList<>();
		q.offer(box[x][y]);
		visited[x][y] = true;

		while (!q.isEmpty()) {
			Tomato polled = q.poll();

			for (int i = 0; i < 4; i++) {
				now[0] = polled.x + diff[i][0];
				now[1] = polled.y + diff[i][1];

				if (now[0] >= 0 && now[0] < M && now[1] >= 0 && now[1] < N) {
					if (box[now[0]][now[1]].state == 0 && !visited[now[0]][now[1]]) {
						box[now[0]][now[1]].state = 1;
						if (box[now[0]][now[1]].state != -1)
							q.offer(box[now[0]][now[1]]);
						box[now[0]][now[1]].day = polled.day + 1; // 공통영역을 어떻게 처리할 것인가?
					}
				}
			}
		}

	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		box = new Tomato[M][N];
		visited = new boolean[M][N];

		// 입력받기
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				box[i][j] = new Tomato(i, j, sc.nextInt(), 0);
			}
		}

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (box[i][j].state == 1 && !visited[i][j]) {
					bfs(i, j);
				}
			}
		}

		// 안익은 토마토가 있는지 검사.
		int max_day = 0;

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (box[i][j].state == 0) {
					System.out.println(-1);
					return;
				}
				if (max_day < box[i][j].day) {
					max_day = box[i][j].day;
				}
			}
		}

		printBox();
		System.out.println(max_day);

	}

	// 출력용
	static void printBox() {
		// box 출력해보기
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(box[i][j].day + " ");
			}
			System.out.println();
		}
	}
}