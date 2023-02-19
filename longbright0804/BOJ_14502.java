package algorithm_study.day17;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * SSAFY 알고리즘 스터디 17일차 - 연구소
 * 
 * @author SSAFY
 *
 */
public class BOJ_14502 {
	// 상하좌우 방향 배열
	static final int[] dx = { -1, 1, 0, 0 };
	static final int[] dy = { 0, 0, -1, 1 };

	static int n, m;
	static int answer;
	static int[] numbers = new int[3];
	static int[][] lab, map;
	static ArrayList<Pos> blankList = new ArrayList<>();
	static ArrayList<Pos> virusList = new ArrayList<>();
	
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 연구실 크기 입력
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		// 연구실 상태를 나타낼 배열 lab, 시뮬레이션을 할 배열 map
		lab = new int[n][m];
		map = new int[n][m];
		// 연구실 정보 입력
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				lab[i][j] = Integer.parseInt(st.nextToken());
				// 벽을 세울 수 있는 위치 후보를 리스트에 추가
				if (lab[i][j] == 0) {
					blankList.add(new Pos(i, j));
				}
				// bfs 의 시작점인 바이러스 위치를 리스트에 추가
				if (lab[i][j] == 2) {
					virusList.add(new Pos(i, j));
				}
			}
		}
		combination(0,0);
		System.out.println(answer);
	}

	private static void combination(int level, int now) {
		// 3개의 지역을 선택하면 해당 영역을 벽으로 변경하고 bfs 수행
		if (level == 3) {
			// 영역 초기화
			initMap();
			// 벽 세우기
			for (int i = 0; i < 3; i++) {
				Pos p = blankList.get(numbers[i]);
				map[p.getX()][p.getY()] = 1;
			}
			// 바이러스가 있는 위치들에서 bfs 수행
			for (int i = 0; i < virusList.size(); i++) {
				bfs(virusList.get(i));
//				System.out.println("바이러스 위치: " + virusList.get(i).getX() + " " + virusList.get(i).getY());
			}
			// 최댓값 갱신
			updateMax();
			return;
		}
		for (int i = now; i < blankList.size(); i++) {
			numbers[level] = i;
			combination(level+1, i+1);
		}
	}

	private static void updateMax() {
		// 각 수행마다 안전영역 계산
		int count = 0;
		for (int x = 0; x < n; x++) {
			for (int y = 0; y < m; y++) {
				if (map[x][y] == 0) count++;
//				System.out.print(map[x][y] + " ");
			}
//			System.out.println();
		}
//		System.out.println(count);
		answer = Math.max(answer, count);
	}

	// 배열 초기화
	private static void initMap() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				map[i][j] = lab[i][j];
			}
		}
	}

	private static void bfs(Pos start) {
		Queue<Pos> q = new LinkedList<Pos>();
		q.add(start);
		while(!q.isEmpty()) {
			Pos now = q.poll();
			int x = now.getX();
			int y = now.getY();
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				// 영역을 벗어나거나 이미 확산 되어있거나, 벽이면  pass
				if (nx < 0 || nx > n-1 || ny < 0 || ny > m-1 || map[nx][ny] == 1 || map[nx][ny] == 2) {
					continue;
				}
				// 이동 가능하면 바이러스로 변경하고 큐에 추가
				map[nx][ny] = 2;
				q.add(new Pos(nx, ny));
			}
		}
	}
}

class Pos {
	private int x;
	private int y;

	public Pos(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}
}