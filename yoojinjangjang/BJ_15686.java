package com.yoojin.boj;


import java.beans.Visibility;
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
	
}
public class BJ_15686 {
	public static int n,m;
	public static int[][] maps; // 전체 지도
	public static int[][] tempMaps; // 임시 지도
	public static ArrayList<Integer[]> home = new ArrayList<Integer[]>(); // 집의 배열
	public static ArrayList<Integer[]> chickens = new ArrayList<Integer[]>(); // 치킨 집의 배열
	public static int chickenCnt;
	public static int homeCnt;
	
	public static boolean[] visited;
	public static int[] output; // 조합을 담을 배열
	public static int result = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		maps = new int[n][n];
		for(int i = 0;i<n;i++) {
			st = new StringTokenizer(in.readLine());
			for(int j =0;j<n;j++) {
				int v = Integer.parseInt(st.nextToken());
				if(v==2) {
					// 치킨집인경우
					maps[i][j] = 0; // 0으로 만들고
					chickens.add(new Integer[] {i,j}); // 치킨 배열에 넣기
				}else if(v==1) {
					maps[i][j] = 1;
					home.add(new Integer[] {i,j}); // 집 배열에 넣기 
				}else {
					maps[i][j] = 0;
				}
			}
		}
		visited = new boolean[n];
		chickenCnt = chickens.size();
		homeCnt = home.size();
		output = new int[m];
		combination(0, 0);
		System.out.println(result);
		
	}
	
	public static int cityChickenDistance;
	public static void combination(int start,int cnt) {
		if (cnt==m) { // 최대 치킨 집의개수일때
			bfs();
			result = Math.min(result, cityChickenDistance);
			return;
			
		}
		
		for(int i =start;i<chickenCnt;i++) {
			//if(visited[i]) continue;
			//visited[i] = true;
			output[cnt] = i;
			combination(i+1, cnt+1);
			//visited[i] = false;
		}
	}
	
	
	
	public static int[][] locs = {{-1,0},{1,0},{0,-1},{0,1}};
	public static void bfs() {
		
		cityChickenDistance = 0;
		for(int i =0;i<homeCnt;i++) {
			Queue<Node> queue = new LinkedList<>();
			//각 집 별 치킨 거리 초기화
			int homeChickenDis = Integer.MAX_VALUE;
			// 집의 수만 큼 반복하며
			
			Node start = new Node(home.get(i)[0],home.get(i)[1]); // 집의 위치

			
			for(int j=0;j<m;j++) {
				Node chicken = new Node(chickens.get(output[j])[0],chickens.get(output[j])[1]); // 치킨 집 
				homeChickenDis = Math.min(homeChickenDis, Math.abs(start.x-chicken.x)+ Math.abs(start.y-chicken.y));
			}
			cityChickenDistance += homeChickenDis; // 현재 도시 치킨 거리에 해당 집의 치킨 거리 더해주기 
		}
	}
}