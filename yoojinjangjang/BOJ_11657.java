package com.yoojin.shortest_path;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class NodeB {
	private int curNode; //현재 노드
	private int nextNode; // 다음 노드
	private int cost; //거리 비용
	public NodeB(int curNode, int nextNode, int cost) {
		this.curNode = curNode;
		this.nextNode = nextNode;
		this.cost = cost;
	}
	public int getCurNode() {
		return curNode;
	}
	public int getNextNode() {
		return nextNode;
	}
	public int getCost() {
		return cost;
	}
	
}

public class BOJ_11657 {
	private static final int INF = (int)1e9; //무한을 의미하는 값으로 10억을 설정
	private static int n,m; //노드의 개수, 간선의 개수
	private static long[] d = new long[501]; //최단 거리 테이블 
	private static ArrayList<NodeB> edges = new ArrayList<>(); //모든 간선에 대한 정보를 담는 리스트
	
	// 벨만 포드 알고리즘
	private static boolean bf(int start) {
		//시작 노드 초기화
		d[start] = 0;
		//전체 n-1번의 라운드를 반복
		for(int i =0;i<n;i++) {
			// 매 반복마다 모든 간선을 확인 
			for(int j = 0;j<m;j++) {
				int curNode = edges.get(j).getCurNode();
				int nextNode = edges.get(j).getNextNode();
				int cost = edges.get(j).getCost();
				// 현재 간선을 거쳐서 다른 노드로 이동하는 거리가 더 짧은 경우
				if(d[curNode] != INF && d[nextNode] > d[curNode] + cost) {
					d[nextNode] = d[curNode] + cost;
					// n번째 라운드에서도 값이 갱신된다면 음수 순환이 존재
					if(i==n-1) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		
		//최단 거리 테이블 무한으로 초기화
		Arrays.fill(d, INF);
		
		//모든 간선 정보 입력
		for(int i=0;i<m;i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();
			
			//a번 노드에서 b번 노드로 가는 비용이 c
			edges.add(new NodeB(a,b,c));
		}
		
		boolean isCycle = bf(1); 
		
		if(isCycle) {
			System.out.println(-1);
		} else {
			// 1번 노드를 제외한 다른 모든 노드로 가기위한 최단 거리를 출력
			for(int i = 2;i<=n;i++) {
				//도달할 수 없는 경우 -1 출력
				if(d[i]==INF) {
					System.out.println(-1);
				} else {
					//도달할 수 있는 경우 거리 출력
					System.out.println(d[i]);
				}
			}
		}
	}
}
