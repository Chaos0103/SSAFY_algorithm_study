package bj;

import java.util.Scanner;

public class BOJ_2559 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(); // 날짜 개수 
		int k = sc.nextInt(); // 온도 측정 범위
		int[] arr = new int[n];
		for(int i = 0; i < n ; i++) {
			arr[i] = sc.nextInt();
		}
		
		int lidx = 0;
		int ridx = k-1;
		int max = Integer.MIN_VALUE;
		
		while(ridx < n) {
			int sum = 0;
			for(int i = lidx; i <= ridx; i++) {
				sum += arr[i];
			}
			if(max < sum) {
				max = sum;
			}
			lidx++;
			ridx++;
		}
		
		System.out.println(max);
	}
}
