import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_11657 {
	static final int INF = (int) 1e9;
	static int n, m;
	static long[] d;
	static ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// n, m 입력
		st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		d = new long[n+1];
		
		// 그래프 생성 및 최단거리 테이블 초기화
		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<Edge>());
			d[i] = INF;
		}
		
		// 간선 정보 입력
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph.get(a).add(new Edge(b, c));
		}
		
		// 벨만포드 알고리즘 수행
		if (bellmanFord()) {
			System.out.println(-1);
		} else {
			for (int i =2; i <= n; i++) {
				if (d[i] == INF) {
					System.out.println(-1);
				} else {
					System.out.println(d[i]);
				}
			}
		}
	}

	// 벨만 포드 알고리즘
	private static boolean bellmanFord() {
		// 시작 정점까지의 거리는 0
		d[1] = 0;
		boolean update = false;
		
		for (int i = 1; i < n; i++) {
			update = false;
			for (int j = 1; j <= n; j++) {
				for (Edge next: graph.get(j)) {
					// 갈 수 없는 경우 종료
					if (d[j] == INF) {
						break;
					}
					// 최솟값 갱신
					if (d[next.node] > d[j] + next.cost) {
						d[next.node] = d[j] + next.cost;
						update = true;
					}
				}
			}
			
			// 최단거리 초기화가 일어나지 않을 경우 반복 종료
			if (!update) {
				break;
			}
		}
		
		// (정점의 개수 - 1)번까지 계속 업데이트가 발생했을 경우
		// (정점의 개수)번 간선도 업데이트가 발생하면 음수 사이클이 발생한 것
		if (update) {
			for (int i = 1; i <= n; i++) {
				for (Edge next: graph.get(i)) {
					// 갈 수 없는 경우 종료
					if (d[i] == INF) {
						break;
					}
					// 최솟값 갱신
					if (d[next.node] > d[i] + next.cost) {
						return true;
					}
				}
			}
		}
		return false;
	}
}

class Edge {
	int node;
	int cost;
	
	public Edge(int node, int cost) {
		this.node = node;
		this.cost = cost;
	}
}
