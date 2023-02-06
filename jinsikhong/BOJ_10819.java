package BJ;

import java.util.Scanner;

/*
 * 
 * 참고 : 10974 순열 함수 참고
 * 풀이 시간 :30분
 * 시도한 방법 : 모든 경우의수 마다 temp 함수를 사용하여 각 원소를 바꾼 후 합 구하기 
 * 모든 경우의 수 찾는 방법을 순열을 통해 구하는 방법으로 변경
 * 
 */





public class BOJ_10819 {
	static int[] parr ;
	static boolean[] visited;
	static int sum = 0;
	static int max = -1;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
//		int max = -1;
		int n = sc.nextInt();
		int[] arr = new int[n];
		for(int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		
		parr = new int[n];
		visited = new boolean[n];
	
		perm(arr, 0, n);
		System.out.println(max);
	}
	
	
	static int sum(int[] arr) {
		int sum = 0;
		for(int i = 0; i < arr.length-1; i++)
			sum += Math.abs(arr[i] - arr[i+1]);
		return sum;
	}
	
	static void perm(int[] arr, int start, int n) {
	    if(start == n) {
	    	sum = sum(parr);
	    	if(sum > max)	max = sum;
	        return;
	    } 

	    for(int i=0; i<n; i++) {
	    	if(visited[i] != true) {
	    		visited[i] = true;
	    		parr[start] = arr[i];
	    		perm(arr, start + 1,n);
	    		visited[i] = false;
		    }
	    }
	}
	
	
//	static int[] temp(int[] arr, int idx1, int idx2) {
//		int[] temp_arr ;
//		int temp;
//		temp = arr[idx1];
//		arr[idx1] = arr[idx2];
//		arr[idx2] = temp;
//		temp_arr = arr;
//		return temp_arr;
//		
//	}
}
