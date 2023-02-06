package BOJ_7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1920 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n, m;
		int[] arr1;
		int[] arr2;
		
		n = Integer.parseInt(st.nextToken());
		arr1 = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr1[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		arr2 = new int[m];
		for (int i = 0; i < m; i++) {
			arr2[i] = Integer.parseInt(st.nextToken());
		}
		//=============================
		Arrays.sort(arr1);
		
		for (int i = 0; i < m; i++) {
			int ans = 0;
			int start = 0;
			int end = n - 1;
			while (start <= end) {
				int mid = (start + end) / 2;
				if (arr1[mid] == arr2[i]) {
					ans = 1;
					break;
				} else if (arr1[mid] > arr2[i]) {
					end = mid - 1;
				} else {
					start = mid + 1;
				}
				ans = 0;
			}
			System.out.println(ans);
		}
	}
}
