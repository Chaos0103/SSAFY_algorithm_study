package BOJ__11;

import java.io.*;
import java.util.*;

public class BOJ_11399 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int [] arr = new int[N + 1];
		
		arr[0] = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		int sum = 0;
		for (int i = 1; i <= N; i++) {
			arr[i] += arr[i - 1];
			sum += arr[i];
		}
		
		System.out.println(sum);
	}
}
