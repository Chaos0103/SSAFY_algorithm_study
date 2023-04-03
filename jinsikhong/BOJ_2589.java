import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static char[][] map;
	static boolean[][] visited;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int L;
	static int W;
	static int result = Integer.MIN_VALUE;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		map = new char[L][W];
//		visited = new boolean[L][W];
		for(int i = 0; i < L; i++) {
			char[] temp = br.readLine().toCharArray();
			for(int j = 0; j < W; j++) {
				map[i][j] = temp[j];
			}
		}
		for(int i = 0; i < L; i++) {
			for(int j = 0; j < W; j++) {
				if(map[i][j] == 'L') {
					visited = new boolean[L][W];
					bfs(new Node(i,j,0));
				}
				
			}
		}
		System.out.println(result);
	
	}
	
	static void bfs(Node node) {
		Queue<Node> q = new ArrayDeque<>();
		q.offer(node);
		visited[node.x][node.y] = true;
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			result = Math.max(result, cur.depth);
			for(int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				if(nx < 0 || nx >= L || ny < 0 || ny >= W) {
					continue;
				}
				if(visited[nx][ny]) {
					continue;
				}
				if(map[nx][ny] == 'W') {
					continue;
				}
				q.offer(new Node(nx, ny, cur.depth+1));
				visited[nx][ny] = true;
			}
		}
	}
	
	
	
	static class Node {
		int x, y, depth;
		
		public Node(int x, int y, int depth) {
			this.x = x;
			this.y = y;
			this.depth = depth;
		}
	}
}
