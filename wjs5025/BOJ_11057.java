import java.util.*;
import java.io.*;

// 점화식 규칙은 찾았는데,
/*
 * 1 -> 10
 * 2 -> 55 (10+9+8+7+6+5+4+3+2+1)
 * 3 -> sum(10~1) + sum(9~1) + ... + sum(1~1)
 * 4 -> sum(sum(10~1)) + sum(sum(9~1)) + ... + sum(sum(1~1))
 * ...
 */
// 이전 N에 대해서만 수행결과를 저장하려고 해서
// 계속 구현을 못했습니다.

// 솔루션을 참고해서 2차원 배열로, 각 결과를 모두 저장해가면서 더해야한다는
// 점을 깨달았습니다...솔루션 그만 봐야할텐데....ㅎㅠ

public class Main {
	static int[][] memo;

	public static void sum(int n) {
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j <10; j++) {
				for (int k = j; k < 10; k++) {
					memo[i][j] += memo[i-1][k];
					memo[i][j] %= 10007;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		int input = sc.nextInt();
		
		memo = new int[input+1][10];
		
		for (int i =0; i < 10; i++) {
			memo[0][i] = 1;
		}
		
		if (input == 1) {
			System.out.println(10);
		} else if (input == 2) {
			System.out.println(55);
		} else {
			sum(input);
			System.out.println(memo[input][0]);
		}
	}
}