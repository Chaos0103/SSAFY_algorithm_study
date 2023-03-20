
import java.io.*;
import java.util.*;

public class Main {
	static int N, L, R, X;
	static int[] A;
	public static int[] combination;
	static int cnt = 0;

	static boolean check() {

		int sum = 0;
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;

		for (int i = 0; i < combination.length; i++) {
			max = Math.max(combination[i], max);
			min = Math.min(combination[i], min);
			sum += combination[i];
		}

		if (sum < L || sum > R) {
			return false;
		}
		if (max - min < X) {
			return false;
		}
		return true;
	}

	public static void getCombination(int depth, int idx, int len) {
		if (depth == len) {
			if (check()) {
				cnt++;
			}
			return;
		}

		for (int i = idx; i < N; i++) {
			combination[depth] = A[i];
			getCombination(depth + 1, i + 1, len);
		}
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		L = sc.nextInt();
		R = sc.nextInt();
		X = sc.nextInt();

		A = new int[N];
		for (int i = 0; i < N; i++) {
			A[i] = sc.nextInt();
		}

		for (int i = 2; i <= N; i++) {
			combination = new int[i];
			getCombination(0, 0, i);
		}

		System.out.println(cnt);
	}
}
