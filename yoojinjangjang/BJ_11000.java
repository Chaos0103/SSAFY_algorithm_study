package com.yoojin.boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_11000 {
	static int N;
	static PriorityQueue<Integer> pq = new PriorityQueue<>();
	static int[][] arr;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][2];
		
		for(int i =0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			arr[i][0] = start;
			arr[i][1] = end;
		}
		
		Arrays.sort(arr,new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0] == o2[0]) return o1[1] - o2[1];
				return o1[0] - o2[0];
			}
			
		});
		
		
		pq.offer(arr[0][1]); // 배열의 첫 번째 end값을 큐에 넣는다. 
		
		// 배열을 두 번째 값부터 순회하면서 
		for(int i =1;i<N;i++) {
			// start가 pq의 peek() 보다 작거나 같다면 pq에서 하나 뺀다. 
			if(pq.peek() <= arr[i][0]) pq.poll();
			
			// 순회하면서, 현재 end값을 새로 pq에 넣어준다. 
			pq.offer(arr[i][1]);
		}
		
		System.out.println(pq.size());
	}
	
}
