package BJ;

import java.util.Scanner;

/*
 * 풀이 시간 : 5분
 */

public class BJ_1476 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int E = sc.nextInt();
		int S = sc.nextInt();
		int M = sc.nextInt();
		
		int year = 1;
		int e = 1;
		int s = 1;
		int m = 1;
		
		
		while((E != e) || (S != s) || (M != m)) {
			e++; s++; m++; year++;
			if(e > 15) {
				e = 1;
			}
			if(s > 28) {
				s = 1;
			}
			if(m > 19) {
				m = 1;
			}
		}
		System.out.println(year);
	}
}
