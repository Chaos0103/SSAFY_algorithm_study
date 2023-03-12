package com.yoojin.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_2696_solution {
	static int N;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 0;t<T;t++) {
			PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
			PriorityQueue<Integer> minHeap = new PriorityQueue<>();
			
			StringTokenizer st = null;
			int N = Integer.parseInt(br.readLine()); // N의 개수 입력받기 
			
			System.out.println((N+1)/2); // 중앙값의 개수 출력
			
			int cnt = 0; // 줄 간격 띄우기 용도
			
			for(int i =0;i<N;i++) {
				if(i%10 == 0) { // 10의 배수이면 10칸을 넘은것이므로 새로 읽어들이기
					st = new StringTokenizer(br.readLine()); // 0일때도 읽어드린다.
				}
				
				int x = Integer.parseInt(st.nextToken()); // 해당 값을 읽어서 
				
				// 입력받는 값들을 차례로 왼쪽, 오른쪽에 넣는다. 
				if(maxHeap.size() == minHeap.size()) {
					maxHeap.offer(x);
				} else {
					minHeap.offer(x);
				}
				
				// minHeap(최소힙, 가장 작은것이 제일 앞에)의 개수가 0개가 아닌 경우 
				// maxHeap(최대힙, 가장 큰것이 제일 앞에)은 중앙값이하의 값만 가지고 있을 수 있도록 
				if(!minHeap.isEmpty()) {
					if(maxHeap.peek() > minHeap.peek()) {
						int t1 = maxHeap.poll();
						int t2 = minHeap.poll();
						
						minHeap.offer(t1);
						maxHeap.offer(t2);
					}
				}
				
				
				// 인덱스는 0부터 시작하므로 짝수 번째 인덱스를 탐색할 때마다 출력해준다. 
				if(i%2==0) {
					// 한줄에는 최대 10개만 가능 
					if(cnt==9 || i == N-1) {
						System.out.println(maxHeap.peek());
						cnt = 0;
					}else {
						System.out.print(maxHeap.peek()+" ");
					}
					cnt++;
				}
			}
		}
		
	}
}
