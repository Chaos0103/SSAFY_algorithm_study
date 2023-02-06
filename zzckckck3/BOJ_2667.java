package BOJ_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_2667 {
	public static int N;
	public static int[][] grid;
	public static boolean[][] visited;
	public static ArrayList<Integer> inCnt; // 단지 내 아파트 갯수 >   
	public static int complexCnt; // 단지 갯수
	public static int[][] dxy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; //상하좌우
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), "");
		
		N = Integer.parseInt(st.nextToken());
		
		grid = new int[N][N];
		visited = new boolean[N][N];
		inCnt = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				grid[i][j] = line.charAt(j)-'0'; // 띄어쓰기 없을땐 이렇게 해야함..
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(grid[i][j] == 1 && !visited[i][j]) {
					complexCnt = 1;
					dfs(i, j);
					inCnt.add(complexCnt);
				}
			}
		}
		
		Collections.sort(inCnt);
		System.out.println(inCnt.size());
		for (int i = 0; i < inCnt.size(); i++) {
			System.out.println(inCnt.get(i));
		}
	}
	
	public static void dfs(int x, int y) {
		visited[x][y] = true;
		for (int i = 0; i < dxy.length; i++) {
			int dx = x + dxy[i][0];
			int dy = y + dxy[i][1];
			
			if(dx >= 0 && dy >= 0 && dx < N && dy < N) {
				if(grid[dx][dy] == 1 && !visited[dx][dy]) {
					dfs(dx, dy);
					complexCnt++;
				}
			}
		}
	}
}
