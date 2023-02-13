package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 문제 : 숫자카드
 * 풀이 시간 : 30분
 */


public class BOJ_10815 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine()); //상근이 카드
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		
		int[] arr_n = new int[n];
		for(int i = 0; i < n; i++) {
			arr_n[i] = Integer.parseInt(st.nextToken());
		}
		
		int m = Integer.parseInt(in.readLine()); //상근이 카드
		st = new StringTokenizer(in.readLine(), " ");
		int[] arr_m = new int[m];
		for(int i = 0; i < m; i++) {
			arr_m[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr_n);
		int start = 0;
		int end = arr_n.length-1;
		for(int i = 0; i < arr_m.length; i++) {
			bs(arr_n,arr_m[i],start,end);
		}
		
	}

	
	static void bs(int[] arr, int key, int start, int end) {
		int mid = 0;
		while(start <= end){
			mid = (start+end)/2;
			if (arr[mid] == key) {
				System.out.println(1);
				return;
			} else if(key < arr[mid]) {
				end = mid -1;
			}else {
				start = mid + 1;
			}
		}
		System.out.println(0);
	}
}
