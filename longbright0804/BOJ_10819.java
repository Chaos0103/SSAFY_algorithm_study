import java.util.Scanner;

/**
 * SSAFY 알고리즘 스터디 2일차 - 차이를 최대로
 * 23.01.31 21:00 시작 - 28분소요
 * 모든 순열을 구하여 각 수열마다 getMax 메소드를 호출
 * 
 * @author YoungHwan
 *
 */
public class BOJ_10819 {
	
	static int n;
	static int[] arr;
	static int[] indexes;
	static boolean[] visited;
	static int max = Integer.MIN_VALUE;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		arr = new int[n];
		indexes = new int[n];
		visited = new boolean[n];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		permutation(0);
		System.out.println(max);
	}
	
	static void permutation(int count) {
		if (count == n) {
			getMax();
			return;
		}
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				indexes[count] = i;
				visited[i] = true;
				permutation(count+1);
				visited[i] = false;
			}
		}
	}
	
	static void getMax() {
		int sum = 0;
		for (int i = 0; i < n-1; i++) {
			sum += Math.abs(arr[indexes[i]] - arr[indexes[i+1]]);
		}
		
		max = Math.max(sum, max);
	}
}
