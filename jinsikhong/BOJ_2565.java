package BJ;

import java.util.Arrays;
import java.util.Scanner;

// 2565 전깃줄
public class BOJ_2565 {
	static int n;
	static EWire[] arr;
	static int[] dp;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		arr = new EWire[n];
		dp = new int[n];
		for(int i = 0; i < n; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			arr[i] = new EWire(x, y);
		}
		Arrays.sort(arr);
//		for(int i = 0; i < n; i ++) {
//			System.out.print(arr[i].y + " ");
//		}
//		System.out.println();
		
		int j = 0;
		int i = 1;
		dp[0] = arr[0].y;
		while(i<n) {
			if(dp[j] < arr[i].y) {
				dp[j+1] = arr[i].y;
				j+=1;
			}else {
				int pos = bs(0, j, arr[i].y);
				dp[pos] = arr[i].y;
			}
			i+=1;
		}
//		for(int x : dp) {
//			System.out.print(x + " ");
//		}
//		System.out.println(j + 1);
		System.out.println(n - (j + 1));
	}
	
	
	static class EWire implements Comparable<EWire>{
		int x, y;
		public EWire(int x, int y) {
			this.x =x ;
			this.y =y;
		}
		@Override
		public int compareTo(EWire o) {
			return this.x - o.x;
		}
	}
	
	static int bs(int left, int right, int target) {
		int mid;
		while(left < right) {
			mid = (left + right) / 2;
			if(dp[mid] < target) {
				left = mid+1;
			} else {
				right = mid;
			}
		}
		return right;
	}
}
