package com.yoojin.bfsearch;

import java.util.Scanner;

public class BOJ_1476 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int E = sc.nextInt();
		int S = sc.nextInt();
		int M = sc.nextInt();
		
		// 15로 i 나눈 나머지 = E 
		// 28로 i 나눈 나머지 = S
		// 19로 i 나눈 나머지 = M
		// 중 가장 작은 값  
		// 1부터 더해가면서 -> 위의 조건 일치시  
		int year = 1;
		while(true) {
			if( year%15 == E%15 && year%28==S%28 && year%19==M%19) {
				break;
			}
			year++;
		}
		System.out.println(year);
		
	}
}
