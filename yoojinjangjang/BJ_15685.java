package com.yoojin.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class BJ_15685 {
	static class Point {
		int x;
		int y;
		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + "]";
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Point other = (Point) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}
		
		
	}
	static int N;
	static Deque<Point> beforeQueue = new ArrayDeque<>();
	static Stack<Point> stack = new Stack<>();
	static Queue<Point> newQueue = new LinkedList<>();
	static boolean[][] visited;
	static int[][] dragons;
	static Map<Point, Point> maps = new HashMap<Point, Point>();
	static int[][] locs = {{0,1},{-1,0},{0,-1},{1,0}};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dragons = new int[N][4];
		for(int i= 0;i<N;i++) {
			dragons[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		
		maps.put(new Point(1,0), new Point(0,-1));
		maps.put(new Point(0,-1), new Point(-1,0));
		maps.put(new Point(-1,0), new Point(0,1));
		maps.put(new Point(0,1), new Point(1,0));
		
		visited = new boolean[102][102];
		int cnt = 0;

		for(int n = 0;n<N;n++) {
			// 각 용을 돌면서 
			
			// 0세대 드래곤을 선 수행
			stack = new Stack<>();
			int nx = dragons[n][1] + locs[dragons[n][2]][0];
			int ny = dragons[n][0] + locs[dragons[n][2]][1];
			stack.add(new Point(dragons[n][1],dragons[n][0])); // 시작점 먼저 넣고
			stack.add(new Point(nx,ny)); // 끝점 넣기
			visited[nx][ny] = true;
			visited[dragons[n][1]][dragons[n][0]] = true;
			// 현재 0세대 드래곤
			
			for(int g = 1;g<=dragons[n][3];g++) {
				
				Point pop = stack.pop();
				Point insertNode = new Point(pop.x,pop.y);
				Point currentNode = new Point(pop.x,pop.y);
				beforeQueue.offer(pop);
				while(!stack.isEmpty()) {
					Point compareNode = stack.pop();
					int dx = compareNode.x - currentNode.x;
					int dy = compareNode.y - currentNode.y;
					Point loc = maps.get(new Point(dx,dy));
					insertNode = new Point(insertNode.x+loc.x,insertNode.y+loc.y);
					newQueue.offer(insertNode);
					beforeQueue.offer(compareNode);
					currentNode = new Point(compareNode.x,compareNode.y);
				}
				
				while(!beforeQueue.isEmpty()) {
					Point poll = beforeQueue.pollLast();
					visited[poll.x][poll.y]= true; 
					stack.add(poll);
				}
				
				while(!newQueue.isEmpty()) {
					Point poll = newQueue.poll();
					visited[poll.x][poll.y]= true; 						
					stack.add(poll);
				}
			}
			

			
		}
		int[][] locFor3 = {{0,1},{1,0},{1,1}};
		
		for(int i =0;i<101;i++) {
			for(int j = 0;j<101;j++) {
				boolean flag = true;
				if(visited[i][j]) {
					for(int[] loc: locFor3) {
						if(!visited[i+loc[0]][j+loc[1]]) {
							flag = false;
							break;
						}
						
					}
					if(flag) cnt++;
				}
				
			}
		}
		
		System.out.println(cnt);
		
	}
}
