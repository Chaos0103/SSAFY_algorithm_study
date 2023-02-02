import java.util.*;
import java.io.*;

class Node {
	int x;
	int y;
	int size;
}

public class Main {
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int[][] diff = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int[] now = new int[2];
	static int size=0;
	static StringBuilder sb = new StringBuilder();
	static int before = 0;
	static ArrayList<Integer> houses = new ArrayList<Integer>();
	
	static void dfs(int x, int y) {
		visited[x][y] = true;
		size++;
		
		for (int i = 0; i < 4; i++) {
			now[0] = x + diff[i][0];
			now[1] = y + diff[i][1];
			
			
			// 범위 충족하는지 확인
			if (now[0] >= 0 && now[0] < N && now[1]>= 0 && now[1] < N) {
				if (!visited[now[0]][now[1]] && map[now[0]][now[1]] == 1) {
					dfs(now[0], now[1]);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int aptCnt = 0;

		N = sc.nextInt();
		map = new int[N][N];
		visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			String[] arr = sc.next().split("");
			map[i] = Arrays.stream(arr).mapToInt(Integer::parseInt).toArray();
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j] && map[i][j] == 1) {
					aptCnt++;
					dfs(i, j);
					houses.add(size - before);
					before = size;
				} 
			}
		}
		
		Collections.sort(houses);
		sb.append(aptCnt).append("\n");
		for (int i = 0; i <houses.size(); i ++) {
			sb.append(houses.get(i)).append("\n");
		}
		
		System.out.println(sb);
	}
}
