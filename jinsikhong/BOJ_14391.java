package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14391 {
	static int n, m;
	static boolean[][] visited;
	static char[][] map;
	static int max = Integer.MIN_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		visited = new boolean[n][m];
		map = new char[n][m];
		for(int i = 0; i < n ; i++) {
			char[] temp = br.readLine().toCharArray();
			for(int j = 0; j < m; j++) {
				map[i][j] = temp[j];
			}
		}
		
		recur(0,0);
		System.out.println(max);
//		int sum = 0;
//		for(int i = 0; i < n; i++) {
//			String s = "";
//			for(int j = 0; j < m; j++) {
//				s += map[i][j];
//			}
//			sum += Integer.parseInt(s);
//		}
//		
//		max = Math.max(max, sum);
//		sum = 0;
//		
//		for(int j = 0; j < m; j++) {
//			String s = "";
//			for(int i = 0; i < n; i++) {
//				s += map[i][j];
//			}
//			sum += Integer.parseInt(s);
//		}
//		max = Math.max(max, sum);
//		System.out.println(max);
		
	}
	
	
	static void recur(int r, int c) {
		if(r >= n) {
			getSum();
			return;
		}
		
		if(c >= m) {
			recur(r+1 , 0);
			return;
		}
		
		visited[r][c] = true;
		recur(r, c+1);
		visited[r][c] = false;
		recur(r, c+1);
	}
	
	static void getSum() {
		int sum = 0;
		for(int i = 0; i < n; i++) {
			String s = "0";
			for(int j = 0; j < m; j++) {
				if(visited[i][j]) {
					s += map[i][j];
				}else {
					sum += Integer.parseInt(s);
					s = "0";
				}
			}
			sum += Integer.parseInt(s);
		}
		
		for(int i = 0; i < m; i++) {
			String s = "0";
			for(int j = 0; j < n; j++) {
				if(!visited[j][i]) {
					s += map[j][i];
				}else {
					sum += Integer.parseInt(s);
					s = "0";
				}
			}
			sum += Integer.parseInt(s);
		}
		max = Math.max(max, sum);
	}
}
