package com.yoojin.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
	int x;
	int y;
	public Node(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	@Override
	public String toString() {
		return "Node [x=" + x + ", y=" + y + "]";
	}
	
	
	
}
public class BJ_14502 {
	public static int n,m; // 세로, 가로
	public static int[][] maps; // 지도
	public static int[][] tempMaps; // 임시 지도 (조합 별로 갱신해줄 예정)
	public static ArrayList<Node> virus = new ArrayList<>(); // 바이러스의 위치를 담을 배열
	public static ArrayList<Node> blanks = new ArrayList<>(); // 빈칸을 담을 배열
	
	public static int[] blankCombi; // 빈칸 중 3개의 조합을 담을 배열 
	
	public static int safeArea = Integer.MIN_VALUE; // 최대 안전 영역 
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		maps = new int[n][m];
		for(int i =0;i<n;i++) {
			st = new StringTokenizer(in.readLine());
			for(int j =0;j<m;j++) {
				int v = Integer.parseInt(st.nextToken());
				if(v == 2) {
					// 바이러스인 경우
					virus.add(new Node(i,j));
				} else if (v == 0) {
					// 빈칸 인 경우
					blanks.add(new Node(i,j));
				} 
				maps[i][j] = v;
			}
		}
		
		
		blankCombi = new int[3];
		// combination 실행
		combination(0, 0);
		System.out.println(safeArea);
	}
	
	
	public static void combination(int start, int cnt) {
		if (cnt == 3) {
			// 3개를 다 뽑은 경우
			// temp배열 초기화 
			tempMaps = new int[n][m];
			for(int i =0;i<n;i++) {
				System.arraycopy(maps[i], 0, tempMaps[i], 0,m);
		
			}
			
			for(int i =0;i<3;i++) {
				Node wall = blanks.get(blankCombi[i]);
				tempMaps[wall.x][wall.y] = 1; // 해당 빈공간의 위치를 벽으로 만듦
			}

			
			// 뽑힌 3개의 조합을 가지고 bfs를 수행한다. 
			
			bfs(); 
			// bfs 수행 후 
			// 안전영역(0)의 개수를 센다. 
			int areas = 0;
			for(int i =0;i<n;i++) {
				for(int j = 0;j<m;j++) {
					if (tempMaps[i][j] == 0) areas++; 
				}
			}
			// safeArea와 비교하여 max 값으로 갱신한다.
			safeArea = Math.max(safeArea, areas);
			return;
		}
		
		for(int i = start;i<blanks.size();i++) {
			blankCombi[cnt] = i;
			combination(i+1, cnt+1);
		}
	}
	
	
	public static int[][] locs = {{1,0},{-1,0},{0,1},{0,-1}};
	public static void bfs() {
		for(int i =0;i<virus.size();i++) {
			Queue<Node> queue = new LinkedList<>();
			// 바이러스위치 꺼내기
			Node start = virus.get(i);
			queue.offer(start); // 큐 삽입
			while(!queue.isEmpty()) {
				// 큐가 빌때까지 반복하며
				Node cur = queue.poll();
				int x = cur.x;
				int y = cur.y;
				for(int[] loc: locs) {
					int nx = x+loc[0];
					int ny = y+loc[1];
					if (nx < 0 || nx >= n || ny <0 ||  ny >= m) continue;
					if (tempMaps[nx][ny]!=0) continue; // 벽이거나 이미 방문한 바이러스인 경우 무시
					tempMaps[nx][ny] = 2; // 바이러스 전파
					queue.offer(new Node(nx,ny)); // 큐에 삽입 
				}
			}
		}
	}
}
// 0(빈칸), 1(벽), 2(바이러스)
// 빈칸들의 리스트를 받고 => 조합 ?  (빈칸의 개수에서 3개 뽑기)
// 조합 빈칸을 1로 만든 후 bfs 수행 
// bfs를 수행한 후의 배열에서 0의 개수를 센다 .=> 안전 영역 크기 
// 각 조합의 안전 영역 크기의 최댓값 구하기

// bfs 
// 각 바이러스가 있는 위치를 시작으로
// 1. 큐에 삽입 
// 2. 큐가 빌떄까지 반복하며
//   - 큐에서 값 빼기 
//   - x, y 설정
//   - 사방탐색 {
//      - 범위검사
//      - 위치가 1인지 검사
//      - 2로 표시 후 큐에 삽입 


