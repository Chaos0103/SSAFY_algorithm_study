package BJ;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/*
 * 풀이시간 : 40분
 */

public class BOJ_2667 {
	static int[][] arr;
	static boolean[][] visited;
	static ArrayList<Integer> result = new ArrayList<Integer>();
	static int[] dx = {0,0,1,-1}; //동서남북 ↘↗ ↙↖
	static int[] dy = {1,-1,0,0};
	static int cnt = 0; // 단지 수 
	static int apt;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		visited = new boolean[n][n];
		arr = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			String s = sc.next();
			char[] x = s.toCharArray();
			for (int j = 0; j < n; j++) {
				arr[i][j] = x[j]-'0';
			}
		}
		
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(!visited[i][j] && arr[i][j] == 1) {
					cnt++;
					apt = 1;
					dfs(i,j,n);
					result.add(apt);
				}
			}
		}
		System.out.println(cnt);
		Collections.sort(result);
		for(int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i));
		}
	}
	
	static void dfs(int i, int j, int n) {
		visited[i][j] = true;
		
		for(int k = 0; k < 4; k++) {
			int nx = i + dx[k];
			int ny = j + dy[k];
			if(0 <= nx && nx < n && 0 <= ny && ny < n) {
				if(arr[nx][ny] == 1 && !visited[nx][ny]) {
					dfs(nx, ny, n);
					apt++;
				}
			}
		}
	}
	
}








//for (int i = 0; i < n; i++) {
//for(int x : arr[i]) {
//	System.out.print(x + " ");
//}
//System.out.println();
//}
