package BOJ_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_10974 {
	public static int n;
	public static int arr[];
	public static boolean selected[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(reader.readLine());
		arr = new int[n];
		selected = new boolean[n];
		
		permutation(0);
	}
	
	public static void permutation (int depth) {
		if(depth == n) {
			for (int i = 0; i < n; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
			return;
		}
		
		for (int i = 0; i < n; i++) {
			if(selected[i]) {
				continue;
			}
			
			arr[depth] = i + 1;
			selected[i] = true;
			permutation(depth + 1);
			selected[i] = false;
		}
	}
}
