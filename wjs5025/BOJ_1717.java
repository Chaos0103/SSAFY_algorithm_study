import java.io.*;
import java.util.*;

public class BOJ_1717 {
	static int N, M;
	static int[] parent;
	// N + 1개의 집합
	// M은 입력으로 주어지는 연산의 개수

	// 부모 찾기 (find)
	public static int getParent(int target) {
		if (target == parent[target])
			return target;
		return parent[target] = getParent(parent[target]);

	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		N = sc.nextInt();
		M = sc.nextInt();

		// 부모테이블 만들기
		parent = new int[N + 1];

		// 부모테이블 초기화
		for (int i = 0; i <= N; i++) {
			parent[i] = i;
		}

		// 집합 입력받기
		for (int i = 0; i < M; i++) {
			int input = sc.nextInt();
			int a = getParent(parent[sc.nextInt()]);
			int b = getParent(parent[sc.nextInt()]);

			// 합집합일 때
			if (input == 0) {
				if (a != b) {
					parent[b] = a;
				} else
					parent[a] = b;
				
			} else {
				//
				if (getParent(parent[a]) == getParent(parent[b])) {
					sb.append("YES").append("\n");
				} else {
					sb.append("NO").append("\n");
				}
			}
		}

		System.out.println(sb);

	}
}