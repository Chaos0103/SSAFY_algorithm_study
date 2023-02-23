package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class SEA_5656 {
	static int[][] map;
	static int N;
	static int W;
	static int H;
	static int[] bead; // 구슬 쏠 열을 알려주는 좌표
	static int[] dx = {0,0,-1,1};
	static int[] dy = {-1,1,0,0};
	static int min;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // 구슬 개수
			W = Integer.parseInt(st.nextToken()); // 너비(열)
			H = Integer.parseInt(st.nextToken()); // 높이(행)
			map = new int[H][W];
			bead = new int[N];
			min = Integer.MAX_VALUE;
			for(int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			perm(0);
			System.out.println(min);
//			Node n1 = find(2);
//			breakbrick(n1);
//			downbrick();
//			Node n2 = find(2);
//			breakbrick(n2);
//			downbrick();
//			Node n3 = find(6);
//			breakbrick(n3);
//			downbrick();
//			
//			for(int[] arr : map) {
//				for(int x : arr) {
//					System.out.print(x + " ");
//				}
//				System.out.println();
//			}
		} //test case
		
		
	}//main
	
	static void solve(int[] perm) { //중복 순열 , 
		int tempmap[][] = new int[H][W];
		for(int i = 0; i < H; i++) {
			for(int j = 0; j <W; j++) {
				tempmap[i][j] = map[i][j];
			}
		}
		for(int i = 0; i < perm.length; i++) {
			Node no = find(perm[i], tempmap);
			if(no == null) {
				continue;
			}
			breakbrick(no, tempmap);
			downbrick(tempmap);
		}
//		for(int[] arr : tempmap) {
//			for(int x : arr) {
//				System.out.print(x + " ");
//			}
//			System.out.println();
//		}
		int result = getcnt(tempmap);
		min = Math.min(min, result);
		
	}
	
	static int getcnt(int[][] tempmap) {
		int sum = 0;
		for(int i = 0; i < H; i++) {
			for(int j = 0; j <W; j++) {
				if(tempmap[i][j] > 0) {
					sum++;
				}
			}
		}
		return sum;
	}
	
	
	static Node find(int w, int[][] map) { //좌표 찾기 -> 있으면 좌표(노드) 리턴 / 없으면 null 리턴
		for(int i = 0; i < H; i++) {
			if(map[i][w] > 0) {
				return new Node(i ,w);
			}
		}
		return null;
	}
	
	
	static void breakbrick(Node no, int[][] tempmap) {
		Queue<Node> q = new ArrayDeque<>();
		q.offer(no);
		while(!q.isEmpty()) {//q 가 빌때까지(폭발하는게 없을때까지)
			Node cur = q.poll();
			int range = tempmap[cur.x][cur.y]-1;
			tempmap[cur.x][cur.y] = 0; //0으로 바꾸기
			int nx;
			int ny;
			for(int i = 0; i < 4; i ++) { // 4방향 탐색
				nx = cur.x;
				ny = cur.y;
				for(int j = 0; j < range; j++) { // 범위만큼
					nx += dx[i];
					ny += dy[i];
					if(nx >= 0 && nx < H && ny >= 0 && ny < W) {// 폭발 범위가 맵에서 안벗어나면
						if(tempmap[nx][ny] > 1) { //1보다  크면 주변 폭발 발생
							q.offer(new Node(nx, ny));
						}else {
							tempmap[nx][ny] = 0;
						}
					}
				}
			}
			
		}
	}
	
	private static void downbrick(int[][] tempmap) {
		for(int i = 0; i < W; i++) {
			Stack<Integer> stack = new Stack<>();
			int idx = 0;
			while(idx < H) {
				if(tempmap[idx][i] > 0) {
					stack.add(tempmap[idx][i]);
					tempmap[idx][i] = 0;
				}
				idx++;
			}
			idx = H-1;
			while(!stack.isEmpty()) {
				tempmap[idx][i] = stack.pop();
				idx--;
			}
		}
	}
	
	
	
	
	private static void perm(int cnt) {
		if(cnt == N) {
			solve(bead);
//			System.out.println(Arrays.toString(bead));
			return;
		}
		for(int i = 0; i < W; i++) {
			bead[cnt] = i;
			perm(cnt + 1);
		}
	}

	
	static class Node{
		int x;
		int y;
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
