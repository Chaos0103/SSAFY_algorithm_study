import java.util.*;
import java.io.*;

/*
캬ㅑㅑ 이맛에 백준풀지
새벽 2시반.. 드디어 잔다...
DP 생각보다 재밌네요 ㅎ
*/

public class Main {
	static int[] cardPack;
	static int[] price;

	public static void dp(int N) {
		for (int i = 2; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i % j == 0) {
					for (int k=1; k <= i/j; k++) {
						int tmp = price[k] + price[(i/j)-k];
						if (price[i] < tmp) {
							price[i] = tmp;
						}
					}
					
				} else {
					if (price[i] < price[i-1] + price[1]) {
						price[i] = price[i-1] + price[1];
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		cardPack = new int[N + 1];
		price = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			cardPack[i] = sc.nextInt();
			price[i] = cardPack[i];
		}

		if (N == 1) {
			System.out.println(cardPack[1]);
			return;
		}

		dp(N);
		System.out.println(price[N]);

	}
}