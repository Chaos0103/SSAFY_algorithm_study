import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static int[][] graph;
	private static boolean[][] visited;
	private static int N;
	private static int M;
	private static int[] dy = {-1, 1, 0, 0};
	private static int[] dx = {0, 0, -1, 1};
	
	
	private static void bfs(int i, int j) {
		visited[i][j] = true;
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {i, j});
		while(!q.isEmpty()) {
			int y = q.peek()[0];
			int x = q.peek()[1];
			q.poll();
			for(int l=0; l<4; l++) {
				int yy = y + dy[l];
				int xx = x + dx[l];
				if(yy>=0 && yy<N && xx >=0 && xx<M) {
					if(graph[yy][xx] > 0 && !visited[yy][xx]){
						q.offer(new int[] {yy, xx});
						visited[yy][xx] = true;
					}
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new int[N][M];
		
		int yearCnt = 0;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		boolean flag = true;
		while(flag) {
			int cnt = 0;
			visited = new boolean[N][M];
			flag = false;
			for (int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(graph[i][j] > 0 && !visited[i][j]) {
						flag=true;
						bfs(i, j);
						cnt++;
					}
				}
			}
			
			if(cnt >= 2) {
				System.out.println(yearCnt);
				break;
			}
			
			int[][] updateGraph = new int[N][M];
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(graph[i][j] > 0) {
						int zeroCnt = 0;
						for(int l=0; l<4; l++) {
							int yy = dy[l] + i;
							int xx = dx[l] + j;
							if(graph[yy][xx] == 0) zeroCnt+=1;
						}
						int update = graph[i][j] - zeroCnt;
						if(update <= 0) update = 0;
						updateGraph[i][j] = update;
					}
				}
			}
			yearCnt+=1;
			graph = updateGraph.clone(); 
		}

		if(!flag) {
            System.out.println(0);
        }
	}
}
