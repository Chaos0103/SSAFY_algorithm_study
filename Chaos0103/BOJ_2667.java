import java.util.*;

public class Main {
	
	private static int[][] map;
	private static int n;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		map = new int[n][n];
		
		for(int i=0;i<n;i++) {
			String line = sc.next();
			for(int j=0;j<n;j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}
		
		int count = -1;
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if (dfs(i, j, count)) {
					count--;
				}
			}
		}
		
		count++;
		
		List<Integer> res = new ArrayList<>();
		for(int c=count;c<0;c++) {
			int home = 0;
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					if (map[i][j] == c) {
						home++;
					}
				}
			}
			res.add(home);
		}
		
		Collections.sort(res);
		
		System.out.println(count * -1);
		for(int cnt : res) {
			System.out.println(cnt);
		}
	}
	
	private static boolean dfs(int x, int y, int count) {
		if (x < 0 || n <= x || y < 0 || n <= y) {
			return false;
		}
		if (map[x][y] == 1) {
			map[x][y] = count;
			dfs(x+1, y, count);
			dfs(x, y+1, count);
			dfs(x-1, y, count);
			dfs(x, y-1, count);
			return true;
		}
		return false;
	}
}
