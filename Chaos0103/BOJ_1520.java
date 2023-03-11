import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class acmicpc_1520_DownhillPath_DP {

	static int R, C, cnt;
	static int[] dx = {1,0,0,-1};
	static int[] dy = {0,1,-1,0};
	
	static int[][] map;
	static int[][] dp;
	static boolean[][] visited;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		dp = new int[R][C];
		visited = new boolean[R][C];
		
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp[R-1][C-1] = 1;
		dfs(0,0);
		
		System.out.println(dp[0][0]);
	}


	private static void dfs(int i, int j) {
		if(i==R-1 && j==C-1) {
			return;
		}
		
		if(visited[i][j]) {
			return;
		}
		
		visited[i][j] = true;
		
		for(int k=0; k<4; k++) {
			int nx = i + dx[k];
			int ny = j + dy[k];
			
			if(nx >=0 && nx <R && ny >=0 && ny <C && map[i][j] > map[nx][ny]) {
				if(dp[nx][ny] != 0) {
					dp[i][j] += dp[nx][ny];
					continue;
				}else {
					dfs(nx,ny);
					dp[i][j] += dp[nx][ny];
				}
			}
		}
	}
}
