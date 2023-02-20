package BOJ__17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1647 {
	public static boolean union(int x, int y) {
		x = find(x);
		y = find(y);
		if(x==y) return false;
		p[x] = y;
		return true;
	}
	public static int find(int x) {
		if(p[x] == x) return x;
		
		return p[x] = find(p[x]);
	}
	static int[] p;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		p = new int[n];
		for(int i=0;i<n;i++) {
			p[i] = i;
		}//자기자신을 부모로 초기화
		
		PriorityQueue<node> q = new PriorityQueue<node>();
		
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			int w = Integer.parseInt(st.nextToken());
			q.add(new node(a, b, w));
		}
		int cnt = 0;
		int res = 0;
		while(!q.isEmpty()) {
			node now = q.poll();
			if(union(now.x, now.y)) {
				cnt++;
				res += now.w;
			}
			if(cnt==n-2) break;
		}
		System.out.println(res);
	}
}

class node implements Comparable<node>{
	int x,y,w;

	public node(int x, int y,int w) {
		super();
		this.x = x;
		this.y = y;
		this.w = w;
	}

	@Override
	public int compareTo(node o) {
		// TODO Auto-generated method stub
		return this.w-o.w;
	}
	
}