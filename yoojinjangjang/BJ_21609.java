package com.yoojin.boj;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_21609 {
	static int N,M;
	static int[][] maps;
	static int[][] tempMaps;
	static PriorityQueue<AllBlock> allBlocksQueue = new PriorityQueue<>();
	
	static class AllBlock implements Comparable<AllBlock> {
		int size; 
		int rainbowCnt;
		Block base;
		List<Block> blocks = new ArrayList<>();
		public AllBlock(int size, int rainbowCnt, Block base, List<Block> blocks) {
			super();
			this.size = size;
			this.rainbowCnt = rainbowCnt;
			this.base = base;
			this.blocks = blocks;
		}
		@Override
		public String toString() {
			return "AllBlock [size=" + size + ", rainbowCnt=" + rainbowCnt + ", base=" + base + ", blocks=" + blocks
					+ "]";
		}
		@Override
		public int compareTo(AllBlock o) {
			if(this.size != o.size) {
				return Integer.compare(o.size, this.size);
			}
			if(this.rainbowCnt != o.rainbowCnt) {
				return Integer.compare(o.rainbowCnt, this.rainbowCnt);
			}
			if(this.base.x != o.base.x) {
				return Integer.compare(o.base.x, this.base.x);
			}
			
			return Integer.compare(o.base.y, this.base.y);
		}
		
	}
	
	
	static class Block implements Comparable<Block>{
		int x,y;
		int color; //색상
		public Block(int x, int y, int color) {
			super();
			this.x = x;
			this.y = y;
			this.color = color;
		}
		@Override
		public String toString() {
			return "Block [x=" + x + ", y=" + y + ", color=" + color + "]";
		}
		@Override
		public int compareTo(Block o) {
			if(this.x != o.x) {
				return Integer.compare(this.x, o.x);
			}
			return Integer.compare(this.y, o.y);
		}
		
	}
	static int result;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		maps = new int[N][N];
		for(int i =0;i<N;i++) {
			maps[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		
		while(true) {
			// step1. 블록 구릅 생성 
			/*
			 * 연결된 블록의 집합, 검은 블록은 포함하지 않음, 인접 블럭 같은색 혹은 무지개, 개수 2이상
			 * - 기준 블럭은 무지개블럭이 아님, 행번호 가장 작음, 열번호 가장 작음 
			 */
			System.out.println("================== start =========================");
			allBlocksQueue = new PriorityQueue<>(); // 모든 블럭그룹의 큐 갱신 
			for(int i =0;i<N;i++) {
				for(int j = 0;j<N;j++) {
					System.out.print(maps[i][j]+" ");
				}
				System.out.println();
			}
			System.out.println();
			// 블록 그룹 생생 메서드
			makeBlockGroups();
			
			// 블록그룹의 사이즈가 0인 경우 더이상의 블록은 없으므로 종료
			if (allBlocksQueue.isEmpty()) break;
			AllBlock maxBlockGroups = allBlocksQueue.poll();
			for(Block block: maxBlockGroups.blocks) {
				maps[block.x][block.y] = -2; // 블럭 삭제 
			}
			result += (Math.pow(maxBlockGroups.size, 2));
			
			System.out.println();
			for(int i =0;i<N;i++) {
				for(int j = 0;j<N;j++) {
					System.out.print(maps[i][j]+" ");
				}
				System.out.println();
			}
			System.out.println();
			
			// 중력 작용 
			doGravity();
			
			System.out.println();
			for(int i =0;i<N;i++) {
				for(int j = 0;j<N;j++) {
					System.out.print(maps[i][j]+" ");
				}
				System.out.println();
			}
			System.out.println();
			
			// 반시계 90도 회전
			doRotate90();
			
			
			System.out.println();
			for(int i =0;i<N;i++) {
				for(int j = 0;j<N;j++) {
					System.out.print(maps[i][j]+" ");
				}
				System.out.println();
			}
			System.out.println();
			
			// 중력작용
			doGravity();
			
			System.out.println();
			for(int i =0;i<N;i++) {
				for(int j = 0;j<N;j++) {
					System.out.print(maps[i][j]+" ");
				}
				System.out.println();
			}
			System.out.println();
		}
		
		System.out.println(result);
	}
	private static void doRotate90() {
		tempMaps = new int[N][N];
		for(int i =0;i<N;i++) {
			for(int j = 0;j<N;j++) {
				tempMaps[N-1-j][i] = maps[i][j];
			}
		}
		for(int i =0;i<N;i++) {
			maps[i] = Arrays.copyOf(tempMaps[i], N);
		}
		
	}
	
	private static void doGravity() {
		for(int j = 0;j<N;j++) {
			Queue<Integer> queue = new LinkedList<>();
			int index = N-1;
			for(int i = N-1;i>=0;i--) {
				if(maps[i][j] >= 0) {
					queue.offer(maps[i][j]);
					maps[i][j] = -2;
				} else if(maps[i][j] == -1) {
					while(!queue.isEmpty()) {
						maps[index--][j] = queue.poll();
						
					}
					index = i-1;
				}
			}
			while(!queue.isEmpty()) {
				maps[index--][j] = queue.poll();
			}
		}
	}
	static PriorityQueue<Block> blocksQueue = new PriorityQueue<>();
	static boolean[][] visited;
	
	private static void makeBlockGroups() {
		visited = new boolean[N][N];
		for(int i =0;i<N;i++) {
			for(int j = 0;j<N;j++) {
				if(visited[i][j] || maps[i][j] <= 0) continue;
				blocksQueue = new PriorityQueue<>(); // 새로운 그룹을 위한 블럭들의 우선순위 큐 생성 
				bfs(i,j);
				// 수행후의 그룹큐를 사용하여 all그룹큐에 삽입하기 
				Block base = null;
				boolean flag = true;
				int rainbowCnt = 0;
				List<Block> newGroup = new ArrayList<>();
				if(blocksQueue.size() >= 2) {
					while(!blocksQueue.isEmpty()) {
						Block current = blocksQueue.poll();
						newGroup.add(current);
						if(current.color != 0 && flag) {
							base = current;
							flag = false;
						}
						if(current.color == 0) {
							rainbowCnt++; 
						}
					}
					allBlocksQueue.add(new AllBlock(newGroup.size(), rainbowCnt, base, newGroup));
				}
			}
		}
		
	}
	
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,-1,0,1};
	private static void bfs(int x, int y) {
		Queue<Block> tempQueue = new LinkedList<>();
		visited[x][y] = true;
		int currentColor = maps[x][y];
		blocksQueue.add(new Block(x, y, currentColor));
		tempQueue.add(new Block(x, y, currentColor));
		while(!tempQueue.isEmpty()) {
			Block current = tempQueue.poll();
			for(int d = 0;d<4;d++) {
				// 4방 탐색
				int nx = current.x + dx[d];
				int ny = current.y + dy[d];
				if(nx < 0 || nx>=N||ny<0||ny>=N) continue;
				if(visited[nx][ny]) continue;
				if(maps[nx][ny] == 0 || maps[nx][ny] == currentColor) {
					// 무지개색이거나 현재 색인 경우
					visited[nx][ny] = true;
					tempQueue.add(new Block(nx,ny,maps[nx][ny]));
					blocksQueue.add(new Block(nx,ny,maps[nx][ny]));
				}
			}
			
			
			
		}
		
	}
}