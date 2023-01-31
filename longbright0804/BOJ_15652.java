import java.util.Scanner;

/**
 * SSAFY 알고리즘 스터디 2일차 - N 과 M(4)
 * 23.02.01 00:20 시작 - 10분 소요 
 * 중복 순열을 이용
 * 매 순열마다 isValid() 메소드를 활용하여 비내림차순인지 여부 확인 후 출력
 * @author YoungHwan
 *
 */
public class BOJ_15652 {
	static int n;
	static int m;
	static int[] arr;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		arr = new int[m];
		permutation(0);
	}
	
	static void permutation(int count) {
		if (count == m) {
			if(isValid()) {
				for (int i = 0; i < m; i++) {
					System.out.print(arr[i] + " ");
				}
				System.out.println();
			}
			return;
		}
		for (int i = 1; i <= n; i++) {
			arr[count] = i;
			permutation(count+1);
		}
	}
	
	static boolean isValid() {
		for (int i = 0; i < m-1; i++) {
			if (arr[i] > arr[i+1]) {
				return false;
			}
		}
		return true;
	}
}
