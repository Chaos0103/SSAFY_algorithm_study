import java.util.*;
import java.io.*;

public class Main {
	static int w,h;
	static StringBuilder sb = new StringBuilder();
	static boolean[][] visited;
	static int[][] map;
	static int[][] diff = {{-1,1},{0,1},{1,1},{-1,0},{1,0},{-1,-1},{0,-1},{1,-1}};
	static int[] now = new int[2];

	public static void dfs(int x, int y) {
		visited[x][y] = true;
		
		for (int i = 0; i < 8; i ++) {
			now[0] = x + diff[i][0];
			now[1] = y + diff[i][1];
			
			if ((now[0] >= 0) && (now[0] < h) && (now[1] >=0) && (now[1] < w) && (!visited[now[0]][now[1]]) && (map[now[0]][now[1]] == 1)) {
				dfs(now[0], now[1]);
			}
		}
	}
	
	public static void TC(int w, int h) {
		int islandCnt = 0;
		
		for (int i = 0; i < h; i++) {
			for (int j =0; j<w; j++) {
				if (!visited[i][j] && map[i][j] == 1) {
					islandCnt++;
					dfs(i,j);
				}
			}
		}
		
		sb.append(islandCnt).append("\n");
	}
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			w = sc.nextInt();
			h = sc.nextInt();
			map = new int[h][w];
			visited = new boolean[h][w];
			
			if(w==0 && h == 0) break;
			
			for(int i =0; i < h; i++) {
				for (int j = 0; j <w; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			TC(w,h);
		}
		
		System.out.println(sb);
		
	}
}
