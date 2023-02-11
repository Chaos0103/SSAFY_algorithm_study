package bj;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_11399 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] arr = new int[n];
		for(int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		Arrays.sort(arr);
		int sum = 0;
		int result = 0;
		for(int i = 0; i < n; i++) {
			sum += arr[i];
			result += sum;
		}
		System.out.println(result);
	}
	
}
