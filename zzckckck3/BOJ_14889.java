package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14889 {
	static int n;
	static int[][] grid;
	static boolean[] visited;
	
	static int Min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(reader.readLine());
		
		grid = new int[n][n];
		visited = new boolean[n];
		
		for (int i = 0; i < n; i++) {
			StringTokenizer tokenizer = new StringTokenizer(reader.readLine(), " ");
			
			for (int j = 0; j < n; j++) {
				grid[i][j] = Integer.parseInt(tokenizer.nextToken());
			}
		}
		
		dfs(0,0);
		
		System.out.println(Min);
	}
	
	public static void dfs(int index, int depth) {
		//완성 시
		if(depth == n / 2) {
			diff();
			return;
		}
		
		for (int i = index; i < n; i++) {
			if(!visited[i]) {
				visited[i] = true;
				dfs(i + 1, depth + 1);
				visited[i] = false;
			}
		}
	}
	
	public static void diff() {
		int start = 0;
		int link = 0;
		
		for (int i = 0; i < n-1; i++) {
			for (int j = i + 1; j < n; j++) {
				if(visited[i] == true && visited[j] == true) { // i, j 가 true => start team
					start += grid[i][j];
					start += grid[j][i];
				} else if(visited[i] == false && visited[j] == false) { // i, j 가 false => link team
					link += grid[i][j];
					link += grid[j][i];
				}
			}
		}
		
		int teamVal = Math.abs(start - link);
		
		// 0 뜨면 탐색 종료
		if(teamVal == 0) {
			System.out.println(teamVal);
			System.exit(0);
		}
		
		Min = Math.min(Min, teamVal);
	}
}
