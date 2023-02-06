package BOJ_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10819 {
	public static int n;
	public static boolean[] visited;
	public static int[] selected;
	public static int[] arr;
	public static int max = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(reader.readLine());
		StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
		
		arr = new int[n];
		visited = new boolean[n];
		selected = new int[n];
		
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(tokenizer.nextToken());
		}
		
		dfs(0);
		
		System.out.println(max);
	}
	
	public static void dfs(int cnt) {
		if(cnt == n) {
			max = Math.max(getResult(), max);
			return;
		}
		
		for (int i = 0; i < n; i++) {
			if(!visited[i]) {
				visited[i] = true;
				selected[cnt] = arr[i];
				dfs(cnt + 1);
				visited[i] = false;
			}
		}
	}
	
	public static int getResult() {
		int sum = 0;
		for (int i = 0; i < n - 1; i++) {
			sum += Math.abs(selected[i] - selected[i + 1]);
		}
		
		return sum;
	}
}