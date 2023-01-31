package BJ;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;




/*
 * 검색 : java 조합, java int 최대값, 
 * 참고 사이트 : https://bcp0109.tistory.com/15
 * 풀이 시간 : 총 2시간
 * 시도한 방법 : 1. 조합을 2차원 list에  담어 각 경우의 수 마다 계산
 * 			-> team/2가 조합 될 때마다 최소값 찾아서 비교
 */


public class BJ_14889 {


	static int idx = 0;
	static int[][] point;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int team = sc.nextInt();
		
		point = new int[team][team]; //member point arr
		for(int i = 0; i < team; i++) {
			for(int j = 0; j < team; j++) {
				point[i][j] = sc.nextInt();
			}
		}
		
		int[] arr = new int[team]; //team 1 ~ n
		boolean[] visited = new boolean[team];
		
		for(int i = 0; i < team; i++) {
			arr[i] = i+1;
		}
		combination(arr, visited, 0, team, team/2);
		System.out.println(min);
		
//		int[] team_1 = new int[team/2];
//		combi(0,0,team, team/2);
//		System.out.println(result);

		
	}

	
	
	
	static void combination(int[] arr, boolean[] visited, int start, int n, int r) {
	    if(r == 0) {
	    	point_sum(visited);
	        return;
	    } 

	    for(int i=start; i<n; i++) {
	        visited[i] = true;
	        combination(arr, visited, i + 1, n, r - 1);
	        visited[i] = false;
	    }
	}
	
	static void point_sum(boolean[] visited) {
		int s = 0;
		int l = 0;
		
		for(int i = 0; i < visited.length-1; i++) {
			for(int j = i+1; j < visited.length; j++) {
				if(visited[i] && visited[j]) {
					s += point[i][j];
					s += point[j][i];
				}else if(!visited[i] && !visited[j]) {
					l += point[i][j];
					l += point[j][i];
				}
			}
		}
		int result = Math.abs(s - l);
		if (min > result) {
			min = result;
		}
	}
	
	
	
//	public static void combi(int start, int cnt, int n,int r) {
//		int [] team = new int[n/2];
//		if(cnt == r) {
//			result.add(team);
//			return;
//		}
//		
//		for(int i=start;i<=n;i++) {
//			team[cnt] = arr[i];
//			combi(i+1,cnt+1,n, r);
//		}
//	}
	

	
}
