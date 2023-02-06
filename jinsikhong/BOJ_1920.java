package BJ;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_1920 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] arr_n = new int[n];
		for(int i = 0; i < n; i++) {
			arr_n[i] = sc.nextInt();
		}
		
		int m = sc.nextInt();
		int[] arr_m = new int[m];
		for(int i = 0; i < m; i++) {
			arr_m[i] = sc.nextInt();
		}
		
		Arrays.sort(arr_n);
		for(int i = 0; i<m; i++) {
			bs(arr_n, arr_m[i], 0, arr_n.length-1);
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
