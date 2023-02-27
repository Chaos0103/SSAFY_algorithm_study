package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14503 {
	static int n;
	static int m;
	static int[][] map;
	static boolean[][] visited;
	
	static int[] dx = {-1, 0, 1, 0 }; // 북 동 남 서
	static int[] dy = {0, 1, 0, -1 };
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());// 로봇 좌표
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken()); // 로봇 첫 방향
		
		map = new int[n][m];
		visited = new boolean[n][m];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		boolean secondFlag;
		boolean thirdFlag;
		int cnt = 0;
		while(true){
			//1
			if(!visited[r][c]) {
				visited[r][c] = true; // 청소
//				System.out.println(r + " " + c + " ");
				cnt++;
			}
			secondFlag = true;
			// 2
			for(int i = 0; i <4; i++) {
				int nx = r + dx[i];
				int ny = c + dy[i];
				if(nx >= 0 && nx < n && ny >= 0 && ny < m) { 
					if(map[nx][ny] == 0 && !visited[nx][ny]) { // 청소가 안된 방이라면
						secondFlag = false;
						break;
					}
				}
			}
			if(secondFlag) {
				int backDirection = (d + 2) % 4;
				int nx = r + dx[backDirection];
				int ny = c + dy[backDirection];
				if(nx >= 0 && nx < n && ny >= 0 && ny < m && map[nx][ny] == 0) { // 2-1
					r = nx;
					c = ny;
					continue;
				}else { // 2-2
					break;
				}
			}
			//3
			else {
//				System.out.print(d + " ");
				int rotation = d - 1;
				if(rotation < 0) {
					rotation = 3;
				} // 반시계 회전
				d = rotation;
//				System.out.println(rotation);
				int nx = r + dx[rotation];
				int ny = c + dy[rotation];
				if(!visited[nx][ny] && map[nx][ny] == 0) {
					r = nx;
					c = ny;
					continue;
				}
			}
		}
		System.out.println(cnt);
		
		
		
	}
}
