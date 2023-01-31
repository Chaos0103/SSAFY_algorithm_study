package BJ;

import java.util.Scanner;
/*
 * 
 * 참고 : 이전 조합 함수 참고
 * 풀이 시간 :30분
 * 
 */


public class BOJ_15652 {
	public static int n, m;
	public static int[] arr;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		arr = new int[m];
		
		combination(1, 0);
	}
	
	static void combination(int start, int d) {
	    if(d == m) {
	    	for(int i = 0; i < arr.length; i++) {
	    		System.out.print(arr[i] + " ");
	    	}
	    	System.out.println();
	        return;
	    } 

	    for(int i=start; i<=n; i++) {
	    	arr[d] = i;
	    	combination(i, d+1);
	    }
	}
}
