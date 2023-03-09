
import java.io.*;
import java.util.*;

/**
 * 오늘도 솔루션을 봐버렸습니다.
 * N-Queen은 왜이렇게 어려운걸까요..
 * 
 */
public class Main {
	static int N;
	static int cnt = 0;
	static boolean[][] visited;
	static int[][] arr;

	static void dfs(int n) {
		// 기저조건
		if (n == N) {
			cnt++;
			return;
		}

		// 유도파트
		for (int i = 0; i < N; i++) {
			if (!visited[n][i]) {
				visited[n][i] = true;
				
				// 퀸을 놓은 자리를 1로 표현
				arr[n][i] = 1;

				checkQueen(n, i);

				dfs(n + 1);

				/* 이전상태로 돌려놔야함. */
				arr[n][i] = 0;
				
				for (int j = 0; j < N; j++) {
					Arrays.fill(visited[j], false);
				}

				/* 이전에 두었던 퀸의 위치 복원 */
				for (int j = 0; j < N; j++) {
					for (int k = 0; k < N; k++) {
						if (arr[j][k] == 1)
							checkQueen(j, k);
					}
				}
			}
		}
	}

	/* 퀸의 공격 방향 표시하는 메소드 */
    public static void checkQueen(int a, int b) {
        /* 아래 방향 */
        for (int i = a; i < N; i++) {
            visited[i][b] = true;
        }
        /* 왼쪽 대각선 */
        int c = a;
        int d = b;
        
        while (0 <= d && d < N & 0 <= c & c < N) {
        	visited[c++][d--] = true;
        }
        
        /* 오른쪽 대각선 */
        while ((0 <= a && a < N & (0 <= b && b < N))) {
        	visited[a++][b++] = true;
        }
    }

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		arr = new int[N][N];
		visited = new boolean[N][N];

		dfs(0);

		System.out.println(cnt);
	}
}
