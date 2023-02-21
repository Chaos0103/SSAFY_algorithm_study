import java.io.*;
import java.util.*;

/*
 * 치킨을 좋아해서
 * 재밌게 풀었습니다.
 * 120분.
 */
public class Main {
	static ArrayList<Node[]> combinations = new ArrayList<>(); // 치킨집 고르는 조합 목록
	static Node[] combinationTmp;
	static ArrayList<Node> chickenCoordinate = new ArrayList<>();
	static ArrayList<Node> houseCoordinate = new ArrayList<>();
	static boolean[] visited;

	static int[][] map;
	static int[][] tmpMap;
	static int N, M;

	static class Node {
		int x;
		int y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + "]";
		}
	}

	public static int getMinChickenDistance() {
		int min = Integer.MAX_VALUE;
		for (Node[] combi : combinations) {
			int[][] clone = new int[N + 1][N + 1];

			for (int i = 0; i <= N; i++) {
				for (int j = 0; j <= N; j++) {
					if (map[i][j] != 2)
						clone[i][j] = map[i][j];
				}
			}

			min = Math.min(min, getChickenDistance(clone,combi));

		}

		return min;
	}

	// getChickenDistance : map에서의 치킨거리 구하기
	public static int getChickenDistance(int[][] map,Node[] combi) {
		int sum = 0;

		for (Node house : houseCoordinate) {
			int min = Integer.MAX_VALUE;
			// Node chicken : chickenCoordinate) 
			for (int i = 0; i<M; i++){
				int chickenDistance = Math.abs(house.x - combi[i].x) + Math.abs(house.y - combi[i].y);
				min = Math.min(chickenDistance, min);
			}
			map[house.x][house.y] = min;
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (map[i][j] != 0) {
					sum += map[i][j];
				}
			}
		}
		return sum;
	}

	// getCombination : 살려놓을 치킨집의 조합구하기
	public static void getCombination(int idx, int start) {
		// 1부터 N까지 숫자 중, M개를 고르는 경우의 수
		if (idx == M) {
			// M개가 다채워졌으면
			combinations.add(combinationTmp.clone());
			return;
		}

		for (int i = start; i < chickenCoordinate.size(); i++) {
			if (visited[i])
				continue;
			visited[i] = true;
			combinationTmp[idx] = chickenCoordinate.get(i);
			getCombination(idx + 1, i + 1);
			visited[i] = false;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 입력받기
		String[] tmp = br.readLine().split(" ");
		N = Integer.parseInt(tmp[0]);
		M = Integer.parseInt(tmp[1]);

		map = new int[N + 1][N + 1];
		tmpMap = new int[N + 1][N + 1];
		combinationTmp = new Node[M];

		// map 입력받기
		for (int i = 1; i <= N; i++) {
			tmp = br.readLine().split(" ");
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(tmp[j - 1]);
				if (map[i][j] == 2)
					chickenCoordinate.add(new Node(i, j));
				else if (map[i][j] == 1) {
					houseCoordinate.add(new Node(i, j));
				}
			}
		}

		// 조합을 만들기 위한 visited
		visited = new boolean[chickenCoordinate.size() + 1];

		getCombination(0, 0);
		System.out.println(getMinChickenDistance());
	}
}