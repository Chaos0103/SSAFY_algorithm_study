package BOJ__12;

import java.io.*;
import java.util.*;

public class BOJ_1080 {
	public static int[][] arrStart;
	public static int[][] arrGoal;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		arrStart = new int[N][M];
		arrGoal = new int[N][M];
		
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				arrStart[i][j] = str.charAt(j) - '0';
			}
		}
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				arrGoal[i][j] = str.charAt(j) - '0';
			}
		}
		
		int cnt = 0;
		for (int i = 0; i < N - 2; i++) {
			for (int j = 0; j < M - 2; j++) {
				if(arrStart[i][j] != arrGoal[i][j]) {
					cnt++;
					swap(i, j);
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(arrStart[i][j] != arrGoal[i][j]) {
					System.out.println(-1);
					System.exit(0);
				}
			}
		}
		System.out.println(cnt);
	}
	
	public static void swap(int a, int b) {
		for (int i = a; i <= a + 2; i++) {
			for (int j = b; j <= b + 2; j++) {
				if(arrStart[i][j] == 1) {
					arrStart[i][j] = 0;
				} else {
					arrStart[i][j] = 1;
				}
			}
		}
	}
}
