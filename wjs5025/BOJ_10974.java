package BJ10974;

import java.util.*;
import java.io.*;

// 1부터 N가지로 이뤄진 순열을 사전순으로 출력.
public class Main {
	static int input;
	static boolean[] used;
	static int[] 순열;
	static StringBuilder sb = new StringBuilder();

	public static void getPermutation (int idx) {
		if (idx == input) {
			for (int i = 0 ; i < 순열.length-1; i++) {
				sb.append(순열[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = 1; i <= input; i++) {
			if (used[i]) continue;
			used[i] = true;
			순열[idx] = i;
			getPermutation(idx+1);
			used[i] = false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		input = Integer.parseInt(br.readLine());
		used = new boolean[input+1];
		순열 = new int[input+1];
		
		getPermutation(0);
		System.out.println(sb);
	}
}
