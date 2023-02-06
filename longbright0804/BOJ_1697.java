import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * SSAFY 알고리즘 스터디 3일차 - 숨바꼭질 
 * 21:34 시작 -> 1시간 소요 못품
 * 
 * @author YoungHwan
 *
 */
public class BOJ_1697 {
	static int n, k;			// 입력 값 n, k
	static int[] visited;		// 방문 배열(0 또는 현재 몇초인지 저장)
	static Queue<Integer> q;	// bfs 해결을 위한 큐

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		k = sc.nextInt();
		visited = new int[100001];
		
		// n == k 인 경우 0 반환. 아니면 bfs 수행
		if (n == k) {
			System.out.println(0);
		} else {
			System.out.println(bfs());			
		}
	}

	
    static int bfs() {
    	q = new LinkedList<>();
        visited = new int[100001];
        q.add(n);
        visited[n] = 1;
        while (!q.isEmpty()) {
            int x = q.poll();
            
            // 아래 로직을 생각을 모담
            // -1, +1, * 2 에 대한 경우를 확인      
            for (int i = 0; i < 3; i++) {
                int nx;
                if (i == 0)
                    nx = x - 1;
                else if (i == 1)
                    nx = x + 1;
                else
                    nx = x * 2;

                // 이동한 위치가 목표 지점일 경우 종료
                if (nx == k)
                    return visited[x];
                // 범위 내에 있고 미방문 지점인 경우 큐에 추가하고 1 증가 (1초 흐름)
                if (nx >= 0 && nx < visited.length && visited[nx] == 0) {
                    visited[nx] = visited[x] + 1;
                    q.add(nx);
                }
            }
        }
        return 0;
    }
}
