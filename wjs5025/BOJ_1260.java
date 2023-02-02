import java.util.*;
import java.io.*;
/*
 * 이론학습 직후에 예시코드를 참고해서 코드를 작성해서,
 * 온전히 제 코드로 만들지는 못했습니다.
 * 차차 제 껄로 만들겠습니다 ㅎ
 */
public class Main {
	static boolean[] visited;
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
	static int N, M, V;
	static StringBuilder sb = new StringBuilder();

	public static void bfs(int start) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		visited[start] = true;

		while (!q.isEmpty()) {
			int x = q.poll();
			sb.append(x).append(" ");
			
			for (int i = 0; i < graph.get(x).size(); i++) {
				int y = graph.get(x).get(i);
				if (!visited[y]) {
					q.offer(y);
					visited[y] = true;
				}
			}
		}
	}

	public static void dfs(int x) {
		visited[x] = true;
		sb.append(x).append(" ");
		
		for (int i = 0; i < graph.get(x).size(); i++) {
			int y = graph.get(x).get(i);
			if (!visited[y])
				dfs(y);
		}
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		V = sc.nextInt();
		visited = new boolean[N+1]; // 피드백 반영

		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Integer>());
		}

		for (int i = 0; i < M; i++) {
			int a = sc.nextInt();  // 피드백 반영
			int b = sc.nextInt();  // 피드백 반영
			graph.get(a).add(b);
			graph.get(b).add(a);
		}

		// 피드백 반영 = 정렬을 dfs 함수 밖으로 빼기.
		for (int i=0; i <graph.size(); i++) {
			Collections.sort(graph.get(i));
		}
		
		dfs(V);
		sb.append("\n");

		for (int i = 0; i <= N; i++) {  // 피드백 반영
			visited[i] = false;
		}

		bfs(V);
		System.out.println(sb);
	}
}