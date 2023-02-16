package BJ;

import java.util.ArrayList;
import java.util.Scanner;
/*
조합 + 백트래킹
*/


public class BOJ_15686 {
	static int n;
	static int m;
	static int[][] arr;
	static boolean[] visited;
	static int min = Integer.MAX_VALUE;
	static ArrayList<Node> chicken = new ArrayList<>();  
    static ArrayList<Node> house = new ArrayList<>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		arr = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				arr[i][j] = sc.nextInt();
				if(arr[i][j] == 1) {
					house.add(new Node(i, j)); //집 저장
				}
				if(arr[i][j] == 2) {
					chicken.add(new Node(i, j)); // 치킨 저장
				}
			}
		}
		visited = new boolean[chicken.size()];
		
		combi(0,0);
		System.out.println(min);
		
		
		
	}
	public static void combi(int cnt, int s) {
		if(cnt == m) {
			solve();
			return;
			
		}
		for(int i = s; i < chicken.size(); i++) {
			if(visited[i] == false) {
				visited[i] = true;
				combi(cnt+1, i+1);
				visited[i] = false;
			}
		}
	}
	
	public static void solve() { // 무조건 치킨집이 최소거리에 집 1개가 있어야한다는 조건 x
		int sum_of_sum = 0;
		for(int i = 0; i < house.size(); i++) { // 배열 전체 다 돌 필요 없이 집 좌표 1개 -> 치킨 집 비교
			int sum = Integer.MAX_VALUE;
			for(int j = 0; j < chicken.size(); j++) {
				if(visited[j] == true) {// 조합 선택
					int chicken_d = Math.abs(house.get(i).x - chicken.get(j).x)+ Math.abs(house.get(i).y - chicken.get(j).y);
					sum = Math.min(sum,chicken_d);
				}
			}
			sum_of_sum += sum; //거리중 가장 짧은거를 최종합에 더하기
		}
		min = Math.min(min, sum_of_sum); 
	} 
	
	
	public static class Node{
		int x;
		int y;
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
