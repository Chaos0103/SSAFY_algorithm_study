package BOJ_1644;
 
import java.util.*;
import java.io.*;

public class Main {
	static int[] primes;
	static int N;
	static int cnt = 0;

	static void twoPointerSearch() {
		int end = primes.length - 1;
		int intervalSum = 0;

		for (int start = primes.length - 1; start >= 0; start--) {
			while (intervalSum < N && end >= 0) {
				intervalSum += primes[end];
				end--;
			}
			if (intervalSum == N) cnt++;
			intervalSum -= primes[start];
		}
			
	}

	public static void eratosthenes() {
		ArrayList<Integer> list = new ArrayList<>();
		boolean[] array = new boolean[N + 1];

		for (int i = 2; i < Math.sqrt(N)+1; i++) {
			if (!array[i]) {
				int j = 2;
				while (i * j <= N) {
					array[i * j] = true;
					j += 1;
				}
			}
		}

		for (int i = 2; i < N + 1; i++) {
			if (!array[i])
				list.add(i);
		}
		primes = list.stream().mapToInt(Integer::intValue).toArray();
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();

		// 범위에 해당하는 소수 목록 구하기 (primes)
		eratosthenes();
		twoPointerSearch();

		System.out.println(cnt);
	}
}
