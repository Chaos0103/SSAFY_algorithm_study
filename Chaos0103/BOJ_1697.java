import java.util.*;

class Node {
	private int position;
	private int count;
	
	public Node(int position, int count) {
		this.position = position;
		this.count = count;
	}
	public int getPosition() {
		return position;
	}
	public int getCount() {
		return count;
	}
}
public class Main {
	
	private static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int target = sc.nextInt();
		
		Queue<Node> q = new LinkedList<>();
		boolean[] visited = new boolean[100001];
		q.add(new Node(n, 0));
		
		while (!q.isEmpty()) {
			Node node = q.poll();
			if (node.getPosition() == target) {
				System.out.println(node.getCount());
				break;
			}
			int[] next = {node.getPosition() * 2, node.getPosition() - 1, node.getPosition() + 1};
			for(int i=0; i<3; i++) {
				if (0 <= next[i] && next[i] <= 100000) {
					if (!visited[next[i]]) {
						visited[next[i]] = true;
						q.add(new Node(next[i], node.getCount() + 1));
					}
				}
			}
		}
	}
}
