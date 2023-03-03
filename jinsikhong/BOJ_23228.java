package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;



public class BOJ_23228 {
	static int N;
	static int M;
	static int[][] dice; // 주사위 도면
	static int[][] map;
	static int[] dx = {0,1,0,-1}; // 동 남 서 북
	static int[] dy = {1,0,-1,0};
	static int direction;// 주사위 방향
	static int r; //주사위 좌표
	static int c; //주사위 좌표
	static int result = 0;
	
	public static void main(String[] args) throws IOException {
		dice = new int[4][3];	
		dice[0][1] = 2; dice[1][0] = 4; dice[1][1] = 1; dice[1][2] = 3; dice[2][1] = 5; dice[3][1] = 6;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		direction = 0; // 초기 방향은 동쪽
		r = 1;
		c = 1;
		
		map = new int[N+1][M+1];
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 입력 받기 완료
		
		
		for(int k = 0; k < K; k++) { // 주사위 굴리기 k번 만큼
			//주사위가 이동 방향으로 한 칸 굴러간다. 만약, 이동 방향에 칸이 없다면, 이동 방향을 반대로 한 다음 한 칸 굴러간다.
			moveDice();
			//주사위가 도착한 칸 (x, y)에 대한 점수를 획득한다.
			getScore();
			
			chageDirection();
			
			
		}
		System.out.println(result);
		
		
		
		
		

		
	}

	
	static void moveDice() {
		int nx = r + dx[direction]; //한칸 이동
		int ny = c + dy[direction]; //한칸 이동
		if(nx > 0 && nx <= N && ny > 0 && ny <= M) { // 범위 안에 있으면
			r = nx;
			c = ny;
		}else { //범위를 벗어나면
			direction = (direction + 2) % 4; // 반대 방향
			r = r + dx[direction]; 
			c = c + dy[direction];
		}
		// 동 남 서 북 (주사위 전개도 변경)
		if(direction == 0) {
			diceEastMove();
		}else if(direction == 1) {
			diceSouthMove();
		}else if(direction == 2) {
			diceWestMove();
		}else {
			diceNorthMove();
		}
		return;
	}
	
	static void getScore() { // 점수 구하기
		int B = map[r][c];
		int count = bfs(B);
		result += B * count;
		
	}
	
	static int bfs(int b) {
		boolean[][] visited = new boolean[N+1][M+1];
		Queue<Node> q = new ArrayDeque<>();
		int cnt = 1;
		visited[r][c] = true;
		q.offer(new Node(r, c));
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			for(int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				if(nx > 0 && nx <= N && ny > 0 && ny <= M) { // 범위 안에 있고
					if(!visited[nx][ny] && map[nx][ny] == b) { // b와 같으면
						visited[nx][ny] = true;
						q.offer(new Node(nx ,ny));
						cnt++;
					}
				}
			}
		}
		return cnt; // 카운트 개수 리턴
	}
	
	static void chageDirection() {
		int A = dice[3][1];
		int B = map[r][c];
		if(A > B) { // 시계 방향 회전
			direction = (direction + 1) % 4;
		}else if(A < B) {// 반시계 회전
			direction = (direction + 3) % 4; 
		}
		return;
	}
	
	
	
	static void diceEastMove() { //동쪽
		int temp = dice[1][2];
		dice[1][2] = dice[1][1];
		dice[1][1] = dice[1][0];
		dice[1][0] = dice[3][1];
		dice[3][1] = temp;
	}
	
	static void diceWestMove() { //서쪽
		int temp = dice[1][0];
		dice[1][0] = dice[1][1];
		dice[1][1] = dice[1][2];
		dice[1][2] = dice[3][1];
		dice[3][1] = temp;
	}
	
	static void diceSouthMove() { //남쪽
		int temp = dice[3][1];
		dice[3][1] = dice[2][1];
		dice[2][1] = dice[1][1];
		dice[1][1] = dice[0][1];
		dice[0][1] = temp;
	}
	
	static void diceNorthMove() { //남쪽
		int temp = dice[0][1];
		dice[0][1] = dice[1][1];
		dice[1][1] = dice[2][1];
		dice[2][1] = dice[3][1];
		dice[3][1] = temp;
	}
	
	static class Node{
		int x, y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	
	
}
