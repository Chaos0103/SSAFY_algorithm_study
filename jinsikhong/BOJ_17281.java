package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 시도 방법 : 정렬하고, 아웃하기 전 가장 높은 타수를 아웃 전에 넣어서 풀려고 했지만 실패 .  . .
 * 			-> 홈런이 2개 이상 존재하면 가능 하지만, 안타로만 이루어져 있으면 아웃 전에 주자가 최소한으로 있어야함
 */

public class BOJ_17281 {
	static int N;
	static boolean[] visited;
	static int[][] order;
	static int[] inningOrder;
	static int maxScore;
	static int result = 0;
	static int hiiterIdx;
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		order = new int[N+1][10];  // 타순
		
		
		StringTokenizer st;
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= 9; j++) { 
				order[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 데이터 받기 끝
		
		visited = new boolean[10];
		inningOrder = new int[10];
		inningOrder[4] = 1; // 4번 타자는 항상 1번 타자
		visited[4] = true;
		maxScore = Integer.MIN_VALUE;
		perm(2);
			
		
		System.out.println(maxScore);
		
		
	
		
	}
	
	static void perm(int cnt) {
		if(cnt == 10) { // 9명 다 채우면 종료
//			System.out.println(Arrays.toString(inningOrder));
			solve();
			return;
		}
		for(int i = 1; i <= 9; i++) {
			if(!visited[i]) { // 3번째(4번타자)는 고정
				visited[i] = true;
				inningOrder[i] = cnt;
				perm(cnt + 1);
				visited[i] = false;
			}
		}
	}
	
	
	static void solve() {
		int hitterIdx = 1;
		int score = 0;
		
		for(int j = 1; j <= N; j++) {
			int out = 0;
			int[] base = new int[4]; // 베이스 상황
			
			while(out < 3) { // 한 이닝이 종료 될때까지
				int hit = order[j][inningOrder[hitterIdx]];
				if(hit == 0) { // out
					out++;
				}else if(hit == 1) { // 1루타
					if(base[3] == 1) {
						score++;
						base[3] = 0;
					}
					if(base[2] == 1) {
						base[2] = 0;
						base[3] = 1;
					}
					if(base[1] == 1) {
						base[1] = 0;
						base[2] = 1;
					}
					base[1] = 1;
				}else if(hit == 2) { // 2루타
					if(base[3] == 1) {
						score++;
						base[3] = 0;
					}
					if(base[2] == 1) {
						score ++;
						base[2] = 0;
					}
					if(base[1] == 1) {
						base[1] = 0;
						base[3] = 1;
					}
					base[2] = 1;
				}else if(hit == 3) { // 3루타
					if(base[3] == 1) {
						score++;
						base[3] = 0;
					}
					if(base[2] == 1) {
						score ++;
						base[2] = 0;
					}
					if(base[1] == 1) {
						score++;
						base[1] = 0;
					}
					base[3] = 1;
				}else if(hit == 4) { // 홈런
					for(int i = 1; i <= 3; i++) {
						if(base[i] == 1) {
							score++;
							base[i] = 0;
						}
					}
					score++;
				}
				hitterIdx++;
				if(hitterIdx > 9) {
					hitterIdx = 1;
				}
			}
		}
		maxScore = Math.max(maxScore, score);
		return;
		
	}
}
