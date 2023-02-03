package BJ;

import java.util.Scanner;
/*
 * 참고 : testcase가 없어서 입력 받는 부분만 검색 후  확인.
 * 풀이시간 : 37분
 */

public class BOJ_4963 {
	static boolean visited[][];
	static int arr[][];
	static int[] dx = {0,0,1,-1,1,1,-1 ,-1}; //동서남북 ↘↗ ↙↖
	static int[] dy = {1,-1,0,0,1,-1,-1,1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			int w = sc.nextInt(); // 열
			int h = sc.nextInt(); // 행
			arr = new int[h][w];
			visited = new boolean[h][w];
			int cnt = 0;
			if(w == h && w == 0)
				break;
			for (int i = 0; i < h; i++) { // make map
				for (int j = 0; j < w; j++) {
					arr[i][j] = sc.nextInt();
				}
			}
//			for (int i = 0; i < h; i++) {
//				for(int x : arr[i]) {
//					System.out.print(x + " ");
//				}
//				System.out.println();
//			}
//			
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if(!visited[i][j] && arr[i][j] == 1) {
						dfs(i,j,h,w);
						cnt++;
					}
				}
			}
			System.out.println(cnt);
			
		}
	}	
	
	static void dfs(int i, int j,int h, int w) {
		visited[i][j] = true;
//		System.out.println(i +" "+ j);
		for(int k = 0; k < 8; k++) {
			int nx = i + dx[k];
			int ny = j + dy[k];
			if(0 <= nx && nx < h && 0 <= ny && ny < w) {
				if(arr[nx][ny] == 1 && !visited[nx][ny])
					dfs(nx, ny, h, w);
			}
		}
		
		
		
		
		
		
		
	}
}
