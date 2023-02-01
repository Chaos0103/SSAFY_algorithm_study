package com.yoojin.dfsbfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_1697 {
	static boolean[] visited = new boolean[100001]; // 이놈슥히 아주 나를 화나게 해
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		getSeconds(N, K);
		
	}
	
	static void getSeconds(int N, int K) {
		

		visited[N] = true;
		Queue<List<Integer>> queue = new LinkedList<>();
		queue.add(Arrays.asList(N,0));
		
		while(!queue.isEmpty()) {
			// 큐가 비어있지 않은 경우 
			List<Integer> now = queue.poll(); // 노드 꺼내서
			int nowX = now.get(0);
			int nowSeconds = now.get(1);
			if(nowX==K) {
				System.out.println(nowSeconds);
				break;
			}
			// 1. + 1
			if(nowX+1 <= 100000 && !visited[nowX+1]) {
				// +1이 방문하지 않았다면
				visited[nowX+1] = true;
				queue.add(Arrays.asList(nowX+1,nowSeconds+1));
			}
			// 2. -1
			if(nowX-1 > -1 && !visited[nowX-1]) {
				// +1이 방문하지 않았다면
				visited[nowX-1] = true;
				queue.add(Arrays.asList(nowX-1,nowSeconds+1));
			}
			
			// 3. *2
			if(nowX*2 <= 100000 && !visited[nowX*2]) {
				// +1이 방문하지 않았다면
				visited[nowX*2] = true;
				queue.add(Arrays.asList(nowX*2,nowSeconds+1));
			}
			

		}
	}
}
