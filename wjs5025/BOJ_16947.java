package BOJ_16947;

/**
 * 120분
 * 솔루션 참고.
 */
import java.io.*;
import java.util.*;

class Node {
	int v;
	int count;

	public Node(int v, int count) {
		this.v = v;
		this.count = count;
	}
}

public class Main {
	static int N;
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	static boolean[] containCycle; // 각 노드가 싸이클에 포함되는지를 검사
	static boolean[] visited;
	static int[] distance;

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		N = sc.nextInt();
		containCycle = new boolean[N + 1];
		distance = new int[N + 1];

		// 그래프 초기화
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		// 그래프 입력받기
		for (int i = 0; i < N; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			graph.get(from).add(to);
			graph.get(to).add(from);
		}

		for (int i = 1; i <= N; i++) {
			if (cycleDFS(i, i, i))
				break;
		}
		visited = new boolean[N + 1];

		for (int i = 1; i <= N; i++) {
			if (!containCycle[i])
				distance[i] = bfs(i);
		}

		for (int i = 1; i <= N; i++) {
			sb.append(distance[i]).append(" ");
		}
		System.out.println(sb);
	}

	// 싸이클에 포함되면 1리턴
	static boolean cycleDFS(int start, int prev, int now) {
		containCycle[now] = true;
		for (int c : graph.get(now)) {
			if (!containCycle[c]) {
				if (cycleDFS(start, now, c))
					return true;
			} else if (prev != c && start == c)
				return true;
		}
		containCycle[now] = false;
		return false;
	}

	// 순환선을 만날때까지의 거리.
	public static int bfs(int node) {
        Queue<Node> q = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        q.add(new Node(node, 0));
        visited[node] = true;
 
        while(!q.isEmpty()) {
            Node current = q.poll();
            if(containCycle[current.v]) return current.count;
 
            for(int i = 0; i < graph.get(current.v).size(); i++) {
                int next = graph.get(current.v).get(i);
                if(!visited[next]) {
                    visited[next] = true;
                    q.add(new Node(next, current.count + 1));
                }
            }
        }
        return 0;
    }
}
