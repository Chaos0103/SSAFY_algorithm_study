package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SEA_등산로조성 {
	static int map[][];
	static boolean visited[][];
	static int n;
	static int K;
	static ArrayList<Node> bongwori;
	static ArrayList<Node> bongworiBFS; //bfs용 봉우리
	static int[] dx = {0,0,-1,1};
	static int[] dy = {-1,1,0,0};
	static int result;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[n][n];
			result =  Integer.MIN_VALUE;
			int high = Integer.MIN_VALUE;
			bongwori = new ArrayList<>();
			bongworiBFS = new ArrayList<>();
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(high == map[i][j]) {
						bongwori.add(new Node(i, j, map[i][j], 1));
					}else if(high < map[i][j]) {
						high = map[i][j];
						bongwori.clear();
						bongwori.add(new Node(i, j,  map[i][j], 1));
					}
				}
			}
			
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					for(int a = 0; a <= K; a++) {
						map[i][j] -= a;

						getMoutainLoad();
						map[i][j] += a;
						bongworiBFS.clear(); //bfs용 봉우리 초기화
					}
				}
			}
			System.out.println("#" + tc + " " + result);
		}
	}
	

	
	static void getMoutainLoad() {
		for(int i = 0; i < bongwori.size(); i++) {
			visited = new boolean[n][n];
			int x = bongwori.get(i).x;
			int y = bongwori.get(i).y;
			dfs(new Node(x,y,map[x][y], 1));
		}
	}
	static void dfs(Node node) {
		Node cur = node;
		for(int i = 0; i < 4; i++) {
			int nx = cur.x + dx[i];
			int ny = cur.y + dy[i];
			if(nx < 0 || nx >= n || ny < 0 || ny >= n) {
				continue;
			}
			if(visited[nx][ny]) {
				continue;
			}
			if(map[nx][ny] >= cur.high) {
				continue;
			}
			visited[nx][ny] = true;
			dfs(new Node(nx, ny, map[nx][ny], cur.depth+1));
			visited[nx][ny] = false;
		}
		result = Math.max(result, node.depth);
	}
	
	
	
	
//	static int bfs(Node node) {
//		Queue<Node> q = new ArrayDeque<>();
//		visited[node.x][node.y] = true;
//		q.offer(node);
//		if(node.x == 2 && node.y == 4) {
//			System.out.println("bfs node ------start-----");
//		}
//		int maxDepth = node.depth;
//		while(!q.isEmpty()) {
//			Node cur = q.poll();
//			if(node.x == 2 && node.y == 4) {
//				System.out.println(cur.toString());
//			}
//			for(int i = 0; i < 4; i++) {
//				int nx = cur.x + dx[i];
//				int ny = cur.y + dy[i];
//				if(nx < 0 || nx >= n || ny < 0 || ny >= n) {
//					continue;
//				}
//				if(visited[nx][ny]) {
//					continue;
//				}
//				if(map[nx][ny] >= cur.high) {
//					continue;
//				}
//				visited[nx][ny] = true;
//				q.offer(new Node(nx, ny, map[nx][ny], cur.depth + 1));
//				maxDepth = Math.max(maxDepth, cur.depth + 1);
//			}
//		}
//
//		return maxDepth;
//		
//	}
	
	
	static class Node{
		int x, y, high, depth;
		public Node(int x, int y, int high, int depth) {
			this.x = x;
			this.y = y;
			this.high = high;
			this.depth = depth;
		}
		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + ", high=" + high + ", depth=" + depth + "]";
		}
		
	}
}
