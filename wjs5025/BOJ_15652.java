import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static int[] 순열;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();

	public static void getPermutation(int idx) {
		
		if (idx == M) {
			for (int i = 0; i < M; i++) {
				sb.append(순열[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = 1; i <= N; i++) {
			if (visited[i]) continue;
			순열[idx] = i;
			
			// i보다 작으면, 방문 처리
			for(int j =0; j < i; j++) {
				visited[j] = true;
			}
			
			// 순열[idx]가 i일 때, 다음 자리수가 올 수 있는 경우의 수
			getPermutation(idx + 1);
			
			// 순열[idx]가 i일 때의 모든 경우를 처리했으므로, 미방문 처리
			for(int j = 0; j < i; j++) {
				visited[j] = false;
			}
		}
		
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		순열 = new int[M];
		visited = new boolean[N + 1];

		getPermutation(0);

		System.out.println(sb);
	}
}