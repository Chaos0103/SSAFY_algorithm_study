import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * SSAFY 알고리즘 스터디 16일차 - 치킨 배달 접근
 * 1시간
 * 
 * @author SSAFY
 *
 */
public class BOJ_15686 {
	static int n, m;
	static int[] used;
	static int minDistance = Integer.MAX_VALUE;
	static int[][] map;
	static ArrayList<Pos> houses;
	static ArrayList<Pos> chickens;

	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 구역 크기, 치킨 집 최대 개수 입력
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		// 2차원 배열, 집 리스트, 치킨집 리스트 생성
		map = new int[n][n];
		houses = new ArrayList<>();
		chickens = new ArrayList<>();
		// 2차원 배열, 집 리스트, 치킨집 리스트 초기화
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					houses.add(new Pos(i, j));
				} else if (map[i][j] == 2) {
					chickens.add(new Pos(i, j));
				}
			}
		}
		
		// 치킨 거리의 최솟값 구하기
		used = new int[chickens.size()];
		combination(0, 0);
		System.out.println(minDistance);
	}

	// 선택할 치킨집 조합
	private static void combination(int level, int now) {
		if (level == m) {
			updateMin();
			return;
		}
		
		for (int i = now; i < chickens.size(); i++) {
			if (used[i] == 1) continue;
			used[i] = 1;
			combination(level+1, i+1);
			used[i] = 0;
		}
	}

	// 최솟값 갱신 메소드
	private static void updateMin() {
		int totalSum = 0;
		// 집 마다 치킨 거리 계산
		for (Pos h: houses) {
			int dist = Integer.MAX_VALUE;
			// 현재 선택된 치킨 집과의 거리 갱신
			for (int i = 0; i < chickens.size(); i++) {
				if (used[i] == 1) {
					Pos c = chickens.get(i);
					dist = Math.min(dist, Math.abs(h.getX()-c.getX()) + Math.abs(h.getY() - c.getY()));
				}
			}
			// 현재 집의 치킨 거리 계산
			totalSum += dist;
		}
		minDistance = Math.min(totalSum, minDistance);
	}
}

// 집, 치킨집 위치 표현을 위한 클래스
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
