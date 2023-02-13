package BJ;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/*
*
* 참고 : bfs 동시 구글 검색 -> 별로 도움이 안됨.
* 풀이시간 : 1시간 30분
*/

class Pair{
	private int x;
	private int y;
	
	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}	

	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	
}

public class BOJ_7576 {
	static int[][] arr;
	static boolean[][] visited;
	static Queue<Pair> q1 = new LinkedList<>();
	static Queue<Pair> q2 = new LinkedList<>();
	static int[] dx = {0,0,1,-1}; 
	static int[] dy = {1,-1,0,0};
	static int n;
	static int m;
	static int cnt;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		m = sc.nextInt(); //열
		n = sc.nextInt(); //행
		
		arr = new int[n][m];
		visited = new boolean[n][m];
		
		for(int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				arr[i][j] = sc.nextInt();
				if(arr[i][j] == 1) {
					Pair p = new Pair(i,j);
					q1.offer(p);
				}
			}
		}
		bfs();
		
		System.out.println(cnt);
	}
	
	static void bfs() {
		while(!q1.isEmpty() || !q2.isEmpty()) {
			while(!q2.isEmpty()) {
				Pair p = q2.poll();
				int x = p.getX();
				int y = p.getY();
				visited[x][y] = true;
				for(int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					if(0 <= nx && nx < n && 0 <= ny && ny < m){
						if(!visited[nx][ny] && arr[nx][ny] == 0) {
							Pair temp = new Pair(nx, ny);
							q1.offer(temp);
							
						}
					}
				}
			}
      
      /*
      * 안익은 토마토가 있는지 확인하는거 구현 못함
      * 정답이 자꾸 +2가 되어서 나옴  아마 각각의 while문이 끝나고 더해지는 cnt가 
      * 한번씩 더 더해지는 것으로 추축 또는 / 로직이 잘못된거일수도
      */
      
      
			cnt++;
			while(!q1.isEmpty()) {
				Pair p = q1.poll();
				int x = p.getX();
				int y = p.getY();
				visited[x][y] = true;
				for(int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					if(0 <= nx && nx < n && 0 <= ny && ny < m) {
						if(!visited[nx][ny] && arr[nx][ny] == 0) {
							Pair temp = new Pair(nx, ny);
							q2.offer(temp);
							
						}
					}
				}
			}
			cnt++;	
		}
	}
}
