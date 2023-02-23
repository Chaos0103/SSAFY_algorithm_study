package com.yoojin.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_20055 {

	
	static int N,K; // N: 컨베이어 길이, K: 내구도 0 칸 개수
	static int[] A; // 내구도 배열
	static int[] robots; // 로봇 위치 배열
	
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		A = new int[2*N];
		
		st = new StringTokenizer(in.readLine());
		for(int i =0;i<2*N;i++) {
			// 각 컨베이어 벨트 가중치를 돌면서 
			A[i] = Integer.parseInt(st.nextToken()); // 가중치 배열 갱신
			
		}
		
		int head = 0; // 현재 시작(헤드)의 위치 (가장 앞의 컨베이어 벨트위치)
		robots = new int[N]; // 로봇 위치 배열 (0부터N-1까지)
		
		int stage = 0;
		
		while(true) {
			stage++; // 단계 올리기
			
			head--; // 컨베이어의 시작 위치가 전 벨트칸을 가르키도록 변경
			if(head==-1) { 
				head = 2*N-1; // -1까지 떨어진 경우에 제일 마지막 값을 가르키도록 변경
			}
			
			for(int i = N-2;i>=0;i--) {
				// 벨트와 함께 로봇도 이동
				if(i+1==N-1) {
					robots[i+1] = 0;
					continue;
				}
				robots[i+1] = robots[i];
			}
			robots[0] = 0;
			
			
			for(int i = N-2;i>=0;i--) { // 로봇 배열을 보면서
				if(robots[i] == 1) { // 해당 위치에 로봇이 있고
					if(robots[i+1] == 0 && A[(head+i+1) % (2*N)] != 0) { // 로봇이 다음 위치에 없고 해당 벨트칸의 내구성도가 0이 아닐 때
						robots[i] = 0;
						if(i+1 != N-1) {
							robots[i+1] = 1; // 로봇 이동
						}
						A[(head+i+1) % (2*N)]--;
					}
				}
			}
			
			if(A[head] != 0) {
				robots[0] = 1;
				A[head]--;
			}
			int cnt = 0;
			for(int i =0;i<2*N;i++) {
				if (A[i] == 0) {
					cnt++;
				}
			}
			if(cnt >= K) break;
		}
		
		System.out.println(stage);
	}
}
