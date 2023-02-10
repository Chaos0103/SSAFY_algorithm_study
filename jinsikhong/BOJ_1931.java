package bj;

import java.util.Arrays;
import java.util.Scanner;

/*
 * 문제 : 회의실 배정
 * 검색 : 2차원 배열 정렬
 * 
 */

public class BOJ_1931 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[][] arr = new int[n][2];
		
		for(int i = 0; i < n; i++) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			arr[i][0] = start;
			arr[i][1] = end;
		}
		//return o1[1]-o2[1]; // 두번째 숫자 기준 오름차순 {2,10}{4,20}{1,30}{5,40}{3,50}
		Arrays.sort(arr, (o1, o2) -> {
			return o1[0]-o2[0];
		});
		Arrays.sort(arr, (o1, o2) -> {
			return o1[1]-o2[1];
		});

		
		
		int s = -1;
		int e = -1;
		int cnt = 0;
		for(int i = 0; i < n; i++) {
			if(arr[i][0] >= e) { //start 가 end랑 같거나 크다면
				cnt++;
				s = arr[i][0];
				e = arr[i][1];
			}
		}
		System.out.println(cnt);
	}
}
