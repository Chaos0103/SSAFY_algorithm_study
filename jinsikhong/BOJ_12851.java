package BJ;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_12851 {
	static int n, k;
	static int[] dx = {-1, 1, 2};
	static int FT = 0; // fast time
	static int cnt = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		k = sc.nextInt();
		bfs(new Node(n, 0));
		System.out.println(FT);
		System.out.println(cnt);
		
	}
	
	static class Node {
		int x, time;
		public Node(int x, int time) {
			this.x =x;
			this.time = time;
		}
	}
	
	
	static void bfs(Node node) {
		Queue<Node> q = new ArrayDeque<>();
		q.offer(node);
		boolean flag = false;
		int fastTime = -1;
		int nx = -1;
		
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			nx = -1;

			if(cur.time > fastTime && flag) {
				FT = fastTime;
				break;
			}
			for(int i = 0; i < 3; i++) {
				if(i == 2) {
					nx = cur.x * dx[i];
				}else {
					nx = cur.x + dx[i];
				}
				if(nx < 0 || nx >= 100001) {
					continue;
				}
				

				q.offer(new Node(nx, cur.time + 1));
				if(nx == k) {
					cnt++;
					flag = true;
					fastTime = cur.time+1;
				}
			}
		}
	}
}
