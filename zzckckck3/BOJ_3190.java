package BOJ__15;

import java.util.*;
import java.io.*;

public class BOJ_3190 {
	public static class TailInfo {
		int y, x;

		public TailInfo(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}
	
	public static class Info {
		char dir;
		int sec;
		
		public Info(int sec, char dir) {
			super();
			this.dir = dir;
			this.sec = sec;
		}
	}
	
	public static int N, K, L, answer;
	
	// 우 상 좌 하
	public static int[] dy = {0, -1, 0, 1};
	public static int[] dx = {1, 0, -1, 0};
	
	public static boolean[][] visited;
	public static int[][] map;
	
	public static Queue<Info> dir_q;
	public static Queue<TailInfo> tail_q;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		visited = new boolean[N][N];
		map = new int[N][N];
		
		for (int i = 0; i < K; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			
			map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = 1;
		}
		
		L = Integer.parseInt(br.readLine());
		dir_q = new LinkedList<Info>();
		for (int i = 0; i < L; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			
			dir_q.offer(new Info(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0)));
		}
		
		tail_q = new LinkedList<TailInfo>();
		go(0, 0, 0, 0);
		
		System.out.println(answer);
	}
	
	public static void go(int y, int x, int cnt, int dir) {
		// 벽에 부딪히면 멈춤
		if (y < 0 || x < 0 || y >= N || x >= N) {
			answer = cnt;
			return;
		}
		
		// 뱀 스스로의 몸통에 닿으면 멈춤
		if (visited[y][x]) {
			answer = cnt;
			return;
		}
		
		// 머리 전진
		visited[y][x] = true;
		
		// 먹을 사과가 없을 때,
		if (map[y][x] != 1) {
			// 뱀의 길이가 1일 경우에는 큐에 아무런 값이 들어있지않는다
			if (!tail_q.isEmpty()) {
				// 뱀의 길이가 1이상이고 먹을 사과가 없는 경우
				TailInfo ti = tail_q.poll();
				// 뱀의 꼬리를 앞으로 전진
				visited[ti.y][ti.x] = false;
			}
		}
		else {
			// 사과를 먹는다.
			map[y][x] = 0;
		}
		
		// 방향을 바꿀 시간이라면
		// 이때, 방향을 바꿀 시간은 시작하고나서부터 입력받은 시간 뒤에임을 주의!!
		// 즉, 시작시간을 0으로 둔 이유
		if (!dir_q.isEmpty() && cnt == dir_q.peek().sec) {
			Info changeInfo = dir_q.poll();
			
			// 방향을 맞게 바꾸어준다.
			if (changeInfo.dir == 'L') {
				dir = (dir + 1) % 4;
			}
			else if (changeInfo.dir == 'D') {
				dir = (dir - 1 < 0) ? 3 : dir - 1;
			}
		}
		
		// 방향에 맞게 전진
		int ny = y + dy[dir];
		int nx = x + dx[dir];
		
		// 꼬리의 전진방향을 알아내기위해 머리가 전진한 방향을 큐에 계속 저장
		tail_q.offer(new TailInfo(y, x));
		// 다음 과정 진행
		go(ny, nx, cnt + 1, dir);
	}
}