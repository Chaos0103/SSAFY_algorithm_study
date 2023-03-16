import java.io.*;
import java.util.*;

class Pos {
	int x;
	int second;

	@Override
	public String toString() {
		return "Pos [x=" + x + ", second=" + second + "]";
	}

	public Pos(int x, int second) {
		super();
		this.x = x;
		this.second = second;
	}

}

public class Main {
	static int N, M;
	static Pos[] map;
	static boolean[] visited;
	static int cnt = 0;
	static int minSecond = Integer.MAX_VALUE;

	static void bfs(Pos start) {
		Deque<Pos> q = new ArrayDeque<Pos>();
		q.offer(start);
		if (start.x != M && start.x != M - 1 && start.x != M + 1 && start.x != M * 2)
			visited[start.x] = true;

		while (!q.isEmpty()) {
			Pos current = q.poll();
			visited[current.x] = true;
			if (current.second > minSecond)
				return;

			if (current.x == M) {
				minSecond = Math.min(minSecond, current.second);
				if (current.second == minSecond) {
					cnt++;
				}
			}

			if (current.x - 1 >= 0 && !visited[current.x - 1]) {
				q.offer(new Pos(current.x - 1, current.second + 1));
			}
			if (current.x + 1 <= 100000 && !visited[current.x + 1]) {
				q.offer(new Pos(current.x + 1, current.second + 1));
			}
			if (current.x * 2 <= 100000 && !visited[current.x * 2]) {
				q.offer(new Pos(current.x * 2, current.second + 1));
			}
		}

	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		map = new Pos[100001];
		visited = new boolean[100001];

		// 10만 by 10만
		bfs(new Pos(N, 0));

		System.out.println(minSecond);
		System.out.println(cnt);
	}
}
