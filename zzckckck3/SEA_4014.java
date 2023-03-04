package Algorithm_230304;

import java.io.*;
import java.util.*;

public class SEA_4014 {
	static int[][] map = new int[20][20];
	static int[][] mapReverse = new int[20][20];
	static int N, X, cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for (int testCase = 1; testCase <= T; testCase++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			cnt = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					mapReverse[j][i] = map[i][j];
				}
			}
			for (int i = 0; i < N; i++) {
				if (canBuild(map, i)) {
					cnt++;
				}
				if (canBuild(mapReverse, i)) {
					cnt++;
				}
			}
			System.out.println("#" + testCase + " " + cnt);
		}
		
		
	}
	
	static boolean canBuild(int[][] map, int idx) {
		int cnt = 1;
		int height = map[idx][0];

		for (int i = 1; i < N; i++) {
			if (height == map[idx][i]) {
				cnt++;
			} 
			else if (map[idx][i] - height == 1) {
				if (cnt < X) {
					return false;
				} else {
					cnt = 1;
					height = map[idx][i];
				}
			}
			else if (height - map[idx][i] == 1) {
				if (N < X + i) {
					return false;
				}
				for (int j = 1; j < X; j++) {
					if (height - map[idx][++i] != 1) {
						return false;
					}
				}
				height = map[idx][i];
				cnt = 0;
			}
		}
		return true;
	}
}
