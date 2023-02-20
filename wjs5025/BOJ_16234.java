package BOJ_16234;

import java.io.*;
import java.util.*;

/*
 * BFS가 아직 어려워요...
 * 
*/
class Node {
	int people; // 인구 수
	int x;
	int y;

	Node(int people, int x, int y) {
		this.people = people;
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "Node [people=" + people + ", x=" + x + ", y=" + y + "]";
	}

}

public class BOJ_16234 {
	public static int N, L, R;
	public static Node[][] map;
	public static boolean[][] visited;

	// 사방 탐색을 위한
	public static int[] dx = { 1, 0, -1, 0 }; // 상 우 하 좌
	public static int[] dy = { 0, 1, 0, -1 };
	public static int cnt = 0;

	// 국경 개방된 나라
	public static ArrayList<Node> openCountryList = new ArrayList<>();

	// 연합
	public static ArrayList<Node> union; // 현재 노드에서의 연합 나라들 담기

	public static int bfs(int x, int y) {
		Queue<Node> q = new LinkedList<>();
		union = new ArrayList<>();

		q.offer(new Node(map[x][y].people, x, y));
		union.add(new Node(map[x][y].people, x, y));
		visited[x][y] = true;
		int sum = map[x][y].people;

		while (!q.isEmpty()) {
			Node current = q.poll();

			for (int k = 0; k < 4; k++) {
				int nextX = current.x + dx[k];
				int nextY = current.y + dy[k];

				if (nextX >= 0 && nextX < N && nextY >= 0 && nextY < N) {
					if (!visited[nextX][nextY]) {
						if (check(current, new Node(map[nextX][nextY].people, nextX, nextY))) {
							visited[nextX][nextY] = true;
							q.offer(new Node(map[nextX][nextY].people, nextX, nextY));
							union.add(new Node(map[nextX][nextY].people, nextX, nextY));
							sum += map[nextX][nextY].people;
						}

					}
				}
			}
		}
		return sum;
	}

	// 국경 열기 (국경선 열 나라들 찾아보기 - BFS)
	public static int openBoundary() {
		int result = 0;

		while (true) {
			boolean isMove = false;
			visited = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j]) {
						int sum = bfs(i, j); // 현재 위치(i,j)의 연합 찾기
						if (union.size() > 1) {
							for (int c = 0; c < union.size(); c++) {
								map[union.get(c).x][union.get(c).y].people = sum / union.size();
							}
							isMove = true;
						}
					}
				}
			}
			
			if (!isMove) return result;
			result++;
		}
	}

	// 개방 조건에 부합하는지 확인
	public static boolean check(Node cur, Node next) {
		int value = Math.abs(cur.people - next.people);
		if (L <= value && value <= R)
			return true;
		return false;
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		L = sc.nextInt();
		R = sc.nextInt();

		map = new Node[N][N];
		visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = new Node(sc.nextInt(), i, j);
			}
		}

		System.out.println(openBoundary());
	}
}