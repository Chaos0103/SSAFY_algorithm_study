package BJ1697;

import java.util.*;
import java.io.*;

/* Node 만들어서 여러 데이터를 가지게 해서
해결하는 방법
을 알려준 우택이형 감사합니댜 긋긋 
 */
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
	static int N, K;
	static int max = 100001;
	static boolean[] visited = new boolean[max];
	static int minCnt = Integer.MAX_VALUE;

	public static void bfs(int start) {
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(start, 0));
		visited[start] = true;

		while (!q.isEmpty()) {
			Node poped = q.poll();

			for (int i = 0; i < 3; i++) {
				if (poped.getPosition() == K) {
					if (minCnt > poped.getCount()) {
						minCnt = poped.getCount();
					}
					return;
				}

				if (i == 0 && poped.getPosition() - 1 >= 0 && !visited[poped.getPosition() - 1]) {
					q.offer(new Node(poped.getPosition() - 1, poped.getCount() + 1));
					visited[poped.getPosition() - 1] = true;
				}

				if (i == 1 && poped.getPosition() + 1 < max && !visited[poped.getPosition() + 1]) {
					q.offer(new Node(poped.getPosition() + 1, poped.getCount() + 1));
					visited[poped.getPosition() + 1] = true;
				}

				if (i == 2 && poped.getPosition() * 2 < max && !visited[poped.getPosition() * 2]) {
					q.offer(new Node(poped.getPosition() * 2, poped.getCount() + 1));
					visited[poped.getPosition() * 1] = true;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		K = sc.nextInt();

		bfs(N);

		System.out.println(minCnt);
	}
}

/*
import java.util.*;
import java.io.*;

/*
 * 실패....
 * 주말에 보완하도록 하겠습니다.
 * 
 */
/*
public class Main {
	static int N, K;
	static int max = 1000001;

	public static int bfs(int start) {
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[max];
		q.offer(start);
		int depth = 0;
		visited[start] = true;
		
		while (!q.isEmpty()) {
            int now = q.poll();
            int next;
  
            depth++;
            visited[now] = true;
            
            for (int i =0; i <3; i++) {
            	if (i==0)
            		next = now - 1;
            	else if (i==1)
            		next = now +1;
            	else 
            		next = now *2;
            	
            	if (next == K) {
            		depth++;
            		break;
            	}
            }
		}
		return 0;
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		K = sc.nextInt();

		bfs(N);

	}
}
 */