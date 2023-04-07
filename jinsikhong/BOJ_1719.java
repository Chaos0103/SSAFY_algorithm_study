package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1719 {
	static final int max = 2000001;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] map = new int[n+1][n+1];
		int[][] dist =  new int[n+1][n+1];
		int[][] result = new int[n+1][n+1];
		for(int i = 1; i <= n; i++) {
			Arrays.fill(map[i], max);
			map[i][i] = 0;
		}
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			map[a][b] = d;
			map[b][a] = d;
			result[a][b] = b;
			result[b][a] = a;
		}
		
		
		//플로이드 워샬
		for(int k = 1; k <= n; k++) {
			for(int i = 1; i <= n; i++) {
				for(int j = 1; j <= n; j++) {
					if(map[i][j] > map[i][k] + map[k][j]) {
						map[i][j] = map[i][k] + map[k][j];
//						System.out.println(i + " " + j + " : " + k );
//						if(result[i][j] == 0) {
							result[i][j] = result[i][k];
//						}
					}
				}
			}
		}
		
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
//				System.out.print(dist[i][j] + " ");
				if(result[i][j] == 0) {
					System.out.print("-" + " ");
				}else {
					System.out.print(result[i][j] + " ");
				}
			}
			System.out.println();
		}
		
	}
}
