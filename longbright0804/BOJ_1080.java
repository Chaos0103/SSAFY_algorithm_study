import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SSAFY 알고리즘 스터디 12일차 - 행렬
 * 일단 해보자 30분
 * 
 * @author YoungHwan
 *
 */
public class BOJ_1080 {
	static int n, m;
	static int result;
	static int[][] A, B;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		A = new int[n][m];
		B = new int[n][m];
		// A 입력
		for (int i = 0; i < n; i++) {
			String input = br.readLine();
			for (int j = 0; j < m; j++) {
				A[i][j] = input.charAt(j) - '0';
			}
		}
		// B 입력
		for (int i = 0; i < n; i++) {
			String input = br.readLine();
			for (int j = 0; j < m; j++) {
				B[i][j] = input.charAt(j) - '0';
			}
		}
		
		// 탐색
		for (int i = 0; i < n-2; i++) {
			for (int j = 0; j < m-2; j++) {
				if (A[i][j] != B[i][j]) {
					// 연산 수행
					filpRow(i, j);
					result++;
				}
			}
		}
		// 일치여부 검사
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <m; j++) {
				if (A[i][j] != B[i][j]) {
					System.out.println(-1);
					return;
				}
			}
		}
		System.out.println(result);
	}

	private static void filpRow(int i, int j) {
		for (int x = i; x < i+3; x++) {
			for (int y = j; y < j+3; y++) {
				A[x][y] = A[x][y] ^ 1;
			}
		}
	}
}