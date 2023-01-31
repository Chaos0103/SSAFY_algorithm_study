import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[] nums;
	static int[] 순열;
	static int max = 0;
	static boolean[] visited;
	
	static int getResult(int idx) {
		if (idx == (N - 2))
			return Math.abs(순열[idx] - 순열[idx + 1]);
		else
			return Math.abs(순열[idx] - 순열[idx + 1]) + getResult(idx + 1);
	}

	static void getPermutation(int idx) {
		if (idx == N) {
			if (max < getResult(0)) {
				max = getResult(0);
			}
			return;
		}
		for (int i =1; i <=N; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			순열[idx] = nums[i-1];
			getPermutation(idx+1);
			visited[i] = false;
		}
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		
		순열 = new int[N];
		nums = new int[N];

		for (int i = 0; i < N; i++) {
			nums[i] = sc.nextInt();
		}

		visited = new boolean[N+1];
		
		getPermutation(0);
		
		System.out.println(max);
	}
}
