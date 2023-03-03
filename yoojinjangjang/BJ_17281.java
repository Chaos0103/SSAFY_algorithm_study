package com.yoojin.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BJ_17281 {
	static int N;
	static int[][] players; // 각 이닝별로 선수가 친 것
	static boolean[] visited;
	static int[] arr;
	static int[] output;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		players = new int[N][9];
		for(int i=0;i<N;i++) {
			players[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		
		visited = new boolean[9];
		output = new int[8];
		permutation(0);
		
		System.out.println(max);
	}
	static int scores;
	static int max = Integer.MIN_VALUE;
	private static void permutation(int cnt) {
		if(cnt == 8) {
			// 해당 선수들을 이용하여 게임을 진행
			doGame();
			// 최대 점수를 갱신
			max = Math.max(max, scores);
			return;
		}

		
		for(int i =1;i<9;i++) {
			if(visited[i]) continue;
			output[cnt] = i;
			visited[i] = true;
			permutation(cnt+1);
			visited[i] = false;
		}
	}
	
	
	private static void doGame() {
		Queue<Integer> queue = new LinkedList<>();
		for(int i =0;i<9;i++) {
			if(i == 3) {
				queue.offer(0);
			} else if (i > 3) {
				queue.offer(output[i-1]);
			} else {
				queue.offer(output[i]);
			}
		}
		scores = 0;
		for(int i =0;i<N;i++) {
			// 각 이닝을 반복하면서 
			int[] runners = new int[3];
			int outCnt = 0;
			while(true) {
				int currentPlayer = queue.poll();
				int result = players[i][currentPlayer];
				if(result == 0) { // 현재 아웃인 경우
					outCnt++;
					if(outCnt == 3) {
						queue.offer(currentPlayer);
						break; // 3아웃인 경우 
					}
				} else { // 아웃이 아닌 경우
					
					for(int j = 0;j<3;j++) {
						if(runners[j] == 1) {
							// 해당 루수에 주자가 있음
							int move = j - result;
							if(move < 0 ) {
								scores++;
							} else {
								runners[move] = 1;
							}
							runners[j] = 0;
						}
					}
					if(result == 4) {
						scores++;
					} else {
						runners[3-result] = 1;
					}
				}
				queue.offer(currentPlayer);
			}
			
		}
	}
}