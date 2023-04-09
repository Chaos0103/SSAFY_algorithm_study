import java.io.*;
import java.util.*;

public class BOJ_20040 {
	static int[] parent;
	static int N, M;

	static void union(int a, int b) {
		a = findParent(a);
		b = findParent(b);

		if (a < b) {
			parent[b] = a;
		} else {
			parent[a] = b;
		}
	}

	static int findParent(int a) {
		if (a == parent[a])
			return a;
		return parent[a] = findParent(parent[a]);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] tmp = br.readLine().split(" ");
		N = Integer.parseInt(tmp[0]);
		M = Integer.parseInt(tmp[1]);

		parent = new int[N];

		for (int i = 0; i < N; i++) {
			parent[i] = i;
		}
		for (int i = 1; i <= M; i++) {
			tmp = br.readLine().split(" ");

			int a = Integer.parseInt(tmp[0]);
			int b = Integer.parseInt(tmp[1]);

			if (findParent(a) == findParent(b)) {
				System.out.println(i);
				return;
			} else {
				union(a,b);
			}
		}
		System.out.println(0);
	}
}
