package Algorithm_230314;

import java.util.*;
import java.io.*;

public class BOJ_12851 {
	static Queue<Integer> queue = new LinkedList<>();
	static int arr[] = new int[100001];

	static int N, K;
	static int min, cnt;
	static int next;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); 
		K = Integer.parseInt(st.nextToken()); 

		if(N >= K) {
			System.out.println(N - K);
			System.out.println(1);
			return;
		}

		BFS();

		System.out.println(min);
		System.out.println(cnt);

	}

	static void BFS() {
		min = Integer.MAX_VALUE / 16;
		cnt = 0;
		queue.offer(N);
		arr[N] = 1;

		while(!queue.isEmpty()) {
			int time = queue.poll();

			if(min < arr[time]) {
				return;
			}

			for(int i = 0; i < 3; i++) {

				switch(i) {
					case 0: 
						next = time + 1;
						break;
					case 1: 
						next = time - 1;
						break;
					default: 
						next = time * 2;
				}


				if(next == K) {
					min = arr[time];
					cnt ++;
				}
			
	
				if( rangeCheck() && (arr[next] == 0 || arr[next] == arr[time] + 1) ) {
					queue.offer(next);
					arr[next] = arr[time] + 1;
				}

			}
		}


	}

	static boolean rangeCheck() {
		return (next >= 0 && next <= 100000);
	}


} 