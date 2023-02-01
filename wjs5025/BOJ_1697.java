import java.util.*;
import java.io.*;

/*
 * 실패....
 * 주말에 보완하도록 하겠습니다.
 * 
 * */
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
