import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int half;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		
		int m = Integer.parseInt(st.nextToken());
		half = (n+1) / 2;
		int[][] upMap = new int[n+1][n+1];
		int[][] downMap = new int[n+1][n+1];
		

		for(int i = 1; i <= n; i++) {
			Arrays.fill(upMap[i], 100000);
			Arrays.fill(downMap[i], 100000);
			upMap[i][i] = 0;
			downMap[i][i] = 0;
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			upMap[left][right] = 1;
			downMap[right][left] = 1;
			

		}
		
		int result = floyd(upMap) + floyd(downMap);
		System.out.println(result);
		
		
		
		
		
		
		
		
		
	}
	
	
	static int floyd(int[][] map) {
		for(int k = 1; k <= n; k++) {
			for(int i = 1; i <= n; i++) {
				for(int j = 1; j <= n; j++) {
					map[i][j] = Math.min(map[i][k] + map[k][j], map[i][j]);
				}
			}
		}
		int result = 0;
		
		for(int i = 1; i <= n; i++) {
			int sum = 0;
			for(int j = 1; j <= n; j++) {
				if(i == j) {
					continue;
				}
				if(map[i][j] < 100000) {
					sum++;
				}
			}
			if(sum >= half) {
				result++;
			}
		}
		return result;
	}
	
	
}
