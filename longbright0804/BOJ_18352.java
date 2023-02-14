import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * SSAFY 알고리즘 스터디 14일차 - 특정 거리의 도시 찾기
 * 1시간
 * @author YoungHwan
 *
 */
public class BOJ_18352 {
	static int n, m, x, k;
	static int[] d = new int[300001];
	static final int INF = (int) 1e9;
	static ArrayList<ArrayList<Vertex>> graph = new ArrayList<>();
	
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		
		// 그래프 초기화
		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<Vertex>());
		}
		
		// 간선 정보 입력
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph.get(a).add(new Vertex(b, 1));
		}
		
		// 최단거리 테이블 INF 로 초기화
		Arrays.fill(d, INF);
		
		dijkstra(x);
		
		boolean isValid = false;
		for (int i = 1; i <= n; i++) {
			if (d[i] == k) {
				isValid = true;
				System.out.println(i);
			}
		}
		if (!isValid) System.out.println(-1);
	}

	private static void dijkstra(int start) {
		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		// 시작 정점은 거리를 0으로 초기화 하고 우선순위 큐에 삽입
		pq.offer(new Vertex(start, 0));
		d[start] = 0;
		while(!pq.isEmpty()) {
			// 현재 최단거리 노드를 우선순위 큐에서 꺼냄
			Vertex v = pq.poll();
			int distance = v.getDistance();
			int now = v.getIndex();
			// 현재 노드가 이미 처리되었다면 무시
			if (d[now] < distance) continue;
			
			// 현재 노드를 거쳐서 다음 노드로 가는 비용 계산
            for (int i = 0; i < graph.get(now).size(); i++) {
                int cost = d[now] + graph.get(now).get(i).getDistance();
                // 현재 노드를 거쳐서, 다른 노드로 이동하는 거리가 더 짧은 경우
                if (cost < d[graph.get(now).get(i).getIndex()]) {
                    d[graph.get(now).get(i).getIndex()] = cost;
                    pq.offer(new Vertex(graph.get(now).get(i).getIndex(), cost));
                }
            }
		}
	}
}

class Vertex implements Comparable<Vertex> {
	private int index;
	private int distance;
	
	public Vertex(int index, int distance) {
		this.index = index;
		this.distance = distance;
	}

	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @return the distance
	 */
	public int getDistance() {
		return distance;
	}

	@Override
	public int compareTo(Vertex o) {
		return this.distance - o.distance;
	}
	
	
}