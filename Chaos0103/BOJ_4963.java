import java.util.*;

public class Main {
	
	private static final int[][] directions = {{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1},{-1,0},{-1,1}};
	private static int[][] map;
	private static int w, h;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			w = sc.nextInt();
			h = sc.nextInt();
			if (w == 0 && h == 0) {
				break;
			}
			
			map = new int[h][w];
			for(int i=0;i<h;i++) {
				for(int j=0;j<w;j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			int count = 0;
			for(int i=0;i<h;i++) {
				for(int j=0;j<w;j++) {
					if(dfs(i, j)) {
						count++;
					}
				}
			}
			
			System.out.println(count);
		}
	}
	
	private static boolean dfs(int x, int y) {
		if (x < 0 || h <= x || y < 0 || w <= y) {
			return false;
		}
		if (map[x][y] == 1) {
			map[x][y] = 0;
			for(int i=0;i<8;i++) {
				dfs(x+directions[i][0],y+directions[i][1]);
			}
			return true;
		}
		return false;
	}
}
