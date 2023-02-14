package BOJ__14;

import java.io.*;
import java.util.*;

public class BOJ_18352 {
	private static int N, M, K, X;
	private static ArrayList<Integer>[] list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		Scanner sc = new Scanner(System.in);

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		list = new ArrayList[N + 1];

		for (int i = 0; i < list.length; i++) {
			list[i] = new ArrayList<Integer>();
		}

		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			list[Integer.parseInt(st.nextToken())].add(Integer.parseInt(st.nextToken()));
		}

		Queue<Integer> queue = new LinkedList<>();

		queue.add(X);

		int[] check = new int[N + 1];
		while (!queue.isEmpty()) {
			int buffer = queue.poll();

			for (int i = 0; i < list[buffer].size(); i++) {
				if (check[list[buffer].get(i)] == 0) {
					check[list[buffer].get(i)] = check[buffer] + 1;
					queue.add(list[buffer].get(i));
				}
			}
		}

		boolean canGo = false;
		for (int i = 1; i < check.length; i++) {
			if (check[i] == K && i != X) {
				canGo = true;
				System.out.println(i);
			}
		}
		if (!canGo)
			System.out.println(-1);

	}	
}
