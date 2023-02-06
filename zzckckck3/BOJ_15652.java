package BOJ_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//15652
public class BOJ_15652 {
	public static int n;
	public static int m;
	public static int arr[];
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokenizer = new StringTokenizer(reader.readLine(), " ");
		
		n = Integer.parseInt(tokenizer.nextToken());
		m = Integer.parseInt(tokenizer.nextToken());
		arr = new int[m];
		
		dfs(1, 0);
	}

	public static void dfs(int cnt, int depth) {
		if(depth == m) {
			for (int i = 0; i < m; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
			return;
		}
		for (int i = cnt; i <= n; i++) {
			arr[depth] = i;
			dfs(i, depth + 1);
		}
	}
}