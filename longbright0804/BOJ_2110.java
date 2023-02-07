import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * SSAFY 알고리즘 8일차 - 공유기 설치
 * 접근을 못함
 * @author YoungHwan
 *
 */
public class BOJ_2110 {
	static int n, c;
	static int[] arr;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = parseInt(st.nextToken());
		c = parseInt(st.nextToken());
		arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = parseInt(br.readLine());
		}
		// 이진탐색을 위한 정렬
		Arrays.sort(arr);
		
		for (int i = 0; i < n; i++) {
			min = binarySearch(arr[i]);
		}
	}
	
	static int binarySearch(int key) {
		int start = 0;
		int end = arr.length;
		int count = 1;
		
		while (start <= end) {
			int mid = (start + end) / 2;
		}
		return 0;
	}
}
