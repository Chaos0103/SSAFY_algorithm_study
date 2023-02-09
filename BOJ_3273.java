package BOJ_10;

import java.io.*;
import java.util.*;
import static java.lang.Integer.*;

public class BOJ_3273 {
	public static int ans = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = parseInt(br.readLine());
		int[] arr = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);	
		
		int x = parseInt(br.readLine());
		
		int start = 0;
		int end = n - 1;
		int sum = 0;
		
		while (start < end) {
			sum = arr[start] + arr[end];
			if(sum == x) {
				ans++;
			}
			if(sum <= x) {
				start++;
			} else {
				end--;
			}
		}
		
		System.out.println(ans);
	}
}
