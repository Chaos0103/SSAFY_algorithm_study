package com.yoojin.swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// pq를 사용해서 생명력이 높은 세포를 먼저 꺼내서 배양해주기
// map의 크기는 무한히 크다는 조건에 따라서 k(시간)을 비교해서 r+K*2+4
// 몇시간뒤 활성화하니까 원래 oglife와 curlife를 2배로 줘서 비교해주며 따져주고 
// 시간별로 진행을 하니까 temp에 넣어줘서 원래 pq가 다 돌고 temp에 있는 거 다시 넣어주기 

public class SWEA_줄기세포배양_solution {
	static int N,M,K,days;
	static boolean[][] visited; // 방문 배열 
	static int[][] map; // 줄기세포 배양 위치 배열 (크기를 넉넉히 잡는다.)
	static int[] dx = {0,1,0,-1}; 
	static int[] dy = {1,0,-1,0};
	
	static Queue<Pos> temp;
	static PriorityQueue<Pos> pq; 
	
	static class Pos implements Comparable<Pos> {
		int i, j, originalLife, curLife; // 위치 i,j 원래 생명력 originalLife, 현재 생명력 curLife
		
		
		public Pos(int i, int j, int originalLife, int curLife) {
			super();
			this.i = i;
			this.j = j;
			this.originalLife = originalLife;
			this.curLife = curLife;
		}


		@Override
		public String toString() {
			return "Pos [i=" + i + ", j=" + j + ", originalLife=" + originalLife + ", curLife=" + curLife + "]";
		}


		@Override
		public int compareTo(Pos o) {
			return -Integer.compare(this.originalLife, o.curLife); // 생명력으로 내림차순 정렬
		}
		
	}
	
	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("swea_줄기세포.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int testNum=1;testNum<=T;testNum++) {
			st = new StringTokenizer(br.readLine()," ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			N = r+K*2+4;
			M = c+K*2+4; // 크기 넉넉하게 잡기
			map = new int[N][M];
			visited = new boolean[N][M];
			
			pq = new PriorityQueue<>();
			temp = new ArrayDeque<>();
			
			for(int i=N/2-1;i<N/2-1+r;i++) {
				st = new StringTokenizer(br.readLine()," ");
				for(int j = M/2-1;j<M/2-1+c;j++) {
					int num = Integer.parseInt(st.nextToken());
					if(num != 0) {
						map[i][j] = num;
						pq.offer(new Pos(i,j,num,num*2));
						visited[i][j] = true;
					}
				}
			}
			
			bfs();
			
			System.out.println("#" + testNum+" "+pq.size());
			
		}
	}
	
	private static void bfs() {
		for(int t = 1;t<=K;t++) { // 시간을 돌면서 
			while(!pq.isEmpty()) {
				// 큐가 비어있지 않다면
				Pos cur = pq.poll(); // 하나를 꺼내서 
				
				cur.curLife -= 1; // 현재 생명력을 -1씩 감소
				
				if(cur.originalLife > cur.curLife) { // 현재 생명력을 2배로 초기화 하였기 때문에 원본 생명력이 더 커지는 경우 활성화 상태가 되었다고 판단
					for(int i =0;i<4;i++) { //4탐을 하며 줄기세포 배양을 시작한다. 
						int nx = cur.i + dx[i];
						int ny = cur.j + dy[i];
						if(nx<0||nx>N||ny<0||ny>M) continue; // 범위검사
						
						if(visited[nx][ny]) continue; // 이미 자리 차지 된 경우 
						
						visited[nx][ny] = true;
						// 시간별로 나누기 위해 temp로 넣어두고 pq를 모두 다 돌고나면 (1시간 지남)  temp에 있는 것(새로생긴애들)을 다시 pq에 넣어준다. 
						temp.offer(new Pos(nx,ny,cur.originalLife,cur.originalLife*2));
						
					}
					
				}
				
				if(cur.curLife != 0) { // 죽지 않은 경우 다시 넣어준다. 
					temp.offer(new Pos(cur.i,cur.j,cur.originalLife,cur.curLife));
				}
			}
			
			while(!temp.isEmpty()) {
				pq.offer(temp.poll());
			}
			
		}
		

	}
}
