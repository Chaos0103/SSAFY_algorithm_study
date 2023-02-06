package BOJ_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_4963 {
	public static int[][] dxy = { {-1, -1 }, {-1, 0 }, {-1, 1 },
								  { 0, -1 },		   { 0, 1 }, 
								  { 1, -1 }, { 1, 0 }, { 1, 1 } };
	public static int W;
	public static int H;
	public static int[][] grid;
	public static boolean[][] check;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		while (true) {
			st = new StringTokenizer(br.readLine());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			if (W == 0 && H == 0) {
				System.exit(0);
			}
			
			grid = new int[H][W];
			check = new boolean[H][W];
			
			
			for (int i = 0; i < grid.length; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < grid[i].length; j++) {
					grid[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int count = 0;
			
			for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid[i].length; j++) {
						if(grid[i][j] == 1 && !check[i][j]) {
							search(i, j);
							count++;
						}
					}
				}
			System.out.println(count);
			}
	}
	
	public static void search(int x, int y) {
		check[x][y] = true;
		
		for (int i = 0; i < dxy.length; i++) {
			int dx = x + dxy[i][0];
			int dy = y + dxy[i][1];
			
			if(dx >= 0 && dy >= 0 && dx < H && dy < W) {
				if(grid[dx][dy] == 1 && !check[dx][dy]) {
					search(dx, dy);
				}
			}
		}
	}
}
