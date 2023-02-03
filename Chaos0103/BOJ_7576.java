import java.util.*;

class Node {
	private int x;
	private int y;
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
public class Main {

	private static int[] dx = {0,0,1,-1};
	private static int[] dy = {1,-1,0,0};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Queue<Node> q = new LinkedList<>();
		
		int m = sc.nextInt();
		int n = sc.nextInt();
		int map[][] = new int[n][m];
		
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == 1) {
					q.offer(new Node(i,j));
				}
			}
		}
		
		while (!q.isEmpty()) {
			Node node = q.poll();
			for(int i=0;i<4;i++) {
				int nx = node.getX() + dx[i];
				int ny = node.getY() + dy[i];
				if (0 <= nx && nx < n && 0 <= ny && ny < m) {
					if (map[nx][ny] == 0) {
						map[nx][ny] = map[node.getX()][node.getY()] + 1;
						q.offer(new Node(nx, ny));
					}
				}
			}
		}
		
		int result = 0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if (map[i][j] == 0) {
					System.out.println(-1);
					return;
				}
				result = Math.max(result, map[i][j]);
			}
		}	
		
		System.out.println(result-1);
	}
}
