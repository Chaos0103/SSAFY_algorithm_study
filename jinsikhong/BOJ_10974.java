package BJ;

import java.util.Arrays;
import java.util.Scanner;

/*
 * 검색 : 14889 참고
 * 풀이 시간 : 30분
 */

public class BJ_10974 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] arr = new int[n];
		boolean[] visited = new boolean[n];
//		for(int i = 0; i < n; i++) {
//			arr[i] = i+1;
//		}
		
		combination(arr,visited,0,n);
		
	}
	static void combination(int[] arr, boolean[] visited, int start, int n) {
	    if(start == n) {
	    	for(int i = 0; i < arr.length; i++) {
	    		System.out.print(arr[i] + " ");
	    	}
	    	System.out.println();
	        return;
	    } 

	    for(int i=0; i<n; i++) {
	    	if(visited[i] != true) {
	    		visited[i] = true;
	    		arr[start] = i+1;
	    		combination(arr, visited, start + 1,n);
	    		visited[i] = false;
		    }
	    }
	}
}
