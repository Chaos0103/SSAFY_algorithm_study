package BJ;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_1759 {
	
	static boolean[] visited;
	static char[] parr;
	static String moum = new String("aeiou");
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int l = sc.nextInt();
		int c = sc.nextInt();
		
		char[] arr = new char[c];
		visited = new boolean[c];
		parr = new char[l];
		
		for(int i = 0; i < c; i++) {
			arr[i] = sc.next().charAt(0);
		}
		perm(arr, 0, l);
	}
	

	
	//a, e, i, o, u
	static void perm(char[] arr, int start, int n) {
	    if(start == n) {
	    	int cnt = 0;
	    	for(char x : parr) {
	    		if(moum.contains(Character.toString(x))) {
	    			cnt++;
	    		}
	    		if(cnt < 1) {
	    			return;
	    		}
	    	}
	    	Arrays.sort(parr);
	    	for(char x : parr) {
	    		System.out.print(x);
	    	}
	    	System.out.println();
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
}
