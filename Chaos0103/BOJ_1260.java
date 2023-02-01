import java.util.*;

public class Main {
	
	private static int n, m, v;
	private static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		v = sc.nextInt();
		
		for(int i=0; i<=n; i++) {
			graph.add(new ArrayList<Integer>());
		}
		
		for(int i=0; i<m; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		
		for(int i=1; i<=n; i++) {
			Collections.sort(graph.get(i));
		}
		
		dfs(v, new boolean[n+1]);
		System.out.println();
		bfs(v, new boolean[n+1]);
	}
	
	private static void dfs(int start, boolean[] visited) {
		System.out.print(start + " ");
		visited[start] = true;
		for(int i=0; i<graph.get(start).size(); i++) {
			if (!visited[graph.get(start).get(i)]) {
				dfs(graph.get(start).get(i), visited);
			}
		}
		
	}
	
	private static void bfs(int start, boolean[] visited) {
		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		visited[start] = true;
		while (!q.isEmpty()) {
			int now = q.poll();
			System.out.print(now + " ");
			for(int i=0; i<graph.get(now).size(); i++) {
				if (!visited[graph.get(now).get(i)]) {
					q.add(graph.get(now).get(i));
					visited[graph.get(now).get(i)] = true;
				}
			}
		}
	}
}
