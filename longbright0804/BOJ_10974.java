import java.util.Scanner;

/**
 * SSAFY 알고리즘 스터디 1일차 - 순열
 * 23.01.30
 * @author YoungHwan
 *
 */
public class BOJ_10974 {
	private static int n;		// 입력 받을 수
	private static int[] arr;	// 순열 저장을 위한 배열
	private static boolean[] visited;	// 방문 여부 확인을 위한 배열
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		arr = new int[n];
		visited = new boolean[n+1];
		permutation(0);
	}
	
	static void permutation(int count) {
		// 종료 조건
		if (count == n) {
			for (int i = 0; i < n; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
		}
		
		for (int i = 1; i <=n; i++) {
			if (!visited[i]) {			// 미방문 시 추가
				arr[count] = i;
				visited[i] = true;		// 방문 처리
				permutation(count+1);	// 다음 숫자로 이동
				visited[i] = false;		// 다시 미방문으로 초기화
			}
		}
	}
}
