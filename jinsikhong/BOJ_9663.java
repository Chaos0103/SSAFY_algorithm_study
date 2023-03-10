package BJ;

import java.util.Scanner;

public class BOJ_9663 {
	static int N, col[], answer;;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		col = new int[N+1];
		setQueen(1);
		System.out.println(answer);
	}
	private static void setQueen(int row) {
		if(!isAvailable(row-1)) return;
		if(row > N) {
			answer += 1;
			return;
		}
		
		for(int i = 1; i <= N; i++) {
			col[row] = i;
			setQueen(row+1);
		}
	}
	private static boolean isAvailable(int row) {
		
		for(int k = 1; k < row; k++) {
			if(col[k] == col[row] || Math.abs(col[k] - col[row]) == row-k) {
				return false;
			}
		}
		return true;
	}
}
