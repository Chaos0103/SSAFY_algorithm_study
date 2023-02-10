package BOJ__11;

import java.io.*;
import java.util.*;

public class BOJ_1931 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N =  Integer.parseInt(br.readLine());
		
		int[][] startEnd = new int[N][2];
		
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			startEnd[i][0] = Integer.parseInt(st.nextToken());
			startEnd[i][1] = Integer.parseInt(st.nextToken());
		}
		
		// 종료시간 기준으로 정렬, 근데 시작시간이 빠른게 앞으로
	}
}
