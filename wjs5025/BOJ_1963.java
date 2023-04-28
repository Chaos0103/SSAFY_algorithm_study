import java.io.*;
import java.util.*;

public class BOJ_1963 {
	static StringBuilder sb = new StringBuilder();
	static ArrayList<Integer> decimals = new ArrayList<>();
	static int cnt = 0;
	static String start;
	static String goal;
	static boolean[] visited;

	// 소수저장하는 노드
	static class Node {
		String num;
		int cnt;

		@Override
		public String toString() {
			return "Node [num=" + num + ", cnt=" + cnt + "]";
		}

		public Node(String num, int cnt) {
			super();
			this.num = num;
			this.cnt = cnt;
		}

	}

	// 소수 init
	static void getDecimals() {
		// 에라토스테네스의 체
		boolean[] array = new boolean[10000 + 1];

		for (int i = 2; i < Math.sqrt(10000) + 1; i++) {
			if (!array[i]) {
				int j = 2;
				while (i * j <= 10000) {
					array[i * j] = true;
					j += 1;
				}
			}
		}

		for (int i = 1000; i < 10000 + 1; i++) {
			if (!array[i])
				decimals.add(i);
		}
	}

	static boolean isDecimial(String num) {
		return decimals.contains(Integer.parseInt(num));
	}

	static void bfs(Node start) {
		Queue<Node> q = new ArrayDeque<>();
		q.offer(start);
		visited[Integer.parseInt(start.num)] = true;

		while (!q.isEmpty()) {
			Node current = q.poll();

			if (current.num.equals(goal)) {
				cnt = current.cnt;
				return;
			}

			for (int i = 0; i < 4; i++) {
				for (int j = 0; j <= 9; j++) {
					StringBuilder tmp = new StringBuilder(current.num);
					tmp.setCharAt(i, (char) ((char) j + '0'));

					String str = tmp.toString();
					if (visited[Integer.parseInt(str)])
						continue;

					if (isDecimial(str)) {
						visited[Integer.parseInt(str)] = true;
						q.offer(new Node(str, current.cnt + 1));
					}
				}
			}

		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int tc = Integer.parseInt(br.readLine());

		getDecimals(); // 소수 구하기

		for (int t = 0; t < tc; t++) {
			String[] tmp = br.readLine().split(" ");
			start = tmp[0];
			goal = tmp[1];
			cnt = 0;
			visited = new boolean[10000];

			bfs(new Node(start, 0));
			sb.append(cnt).append("\n");
		}
		System.out.println(sb);
	}
}
