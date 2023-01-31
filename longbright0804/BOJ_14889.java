import java.util.Scanner;

/**
 * SSAFY 알고리즘 스터디 1일차 - 스타트와 링크 
 * 23.01.30 21:30 시작 조합을 이용하여 팀 분할 후 최솟값 계산
 * @author YoungHwan
 *
 */
public class BOJ_14889 {
	private static int n;
	private static int[][] S;
	private static boolean[] visited;
	private static int min = Integer.MAX_VALUE;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 입력
		n = sc.nextInt();
		S = new int[n][n];
		visited = new boolean[n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				S[i][j] = sc.nextInt();
			}
		}
		combination(0, 0);
		System.out.println(min);
	}

	private static void combination(int cnt, int start) {
		if (cnt == n / 2) {
			getMin();
			return;
		}
		for (int i = start; i < n; i++) {
			if (!visited[i]) {
				visited[i] = true;
				combination(cnt + 1, i + 1);
				visited[i] = false;
			}
		}
	}

	private static void getMin() {
		int startTeam = 0;
		int linkTeam = 0;
		int diff = 0;

		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				// i 번째 사람과 j 번째 사람이 true라면 스타트팀으로 점수 플러스
				if (visited[i] && visited[j]) {
					startTeam += S[i][j];
					startTeam += S[j][i];
				}
				// i 번째 사람과 j 번째 사람이 false라면 링크팀으로 점수 플러스
				else if (!visited[i] && !visited[j]) {
					linkTeam += S[i][j];
					linkTeam += S[j][i];
				}
			}
		}
		diff = Math.abs(startTeam - linkTeam);

		// 최솟값 계산
		min = Math.min(min, diff);
	}
}
