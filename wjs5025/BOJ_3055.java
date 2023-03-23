
import java.io.*;
import java.util.*;

/*
 * 도전시간 : 70분
 * 메모리 초과로 펑~~
 * 조금만 더하면 해결가능할 거 같은데....
 * 
 */
class Pos {
	int r;
	int c;
	String str;
	int time = 0;

	@Override
	public String toString() {
		return String.format("%s(%d)", str, time);
	}

	public Pos(int r, int c, String str, int time) {
		super();
		this.r = r;
		this.c = c;
		this.str = str;
		this.time = time;
	}
}

public class BOJ_3055 {
	static int R, C;
	static Pos[][] map;
	static ArrayList<Pos> waterStarts = new ArrayList<>();
	static int[] dx = { 1, 0, -1, 0 }; // 상우하좌
	static int[] dy = { 0, 1, 0, -1 };
	static boolean[][] visited;
	static Pos start;
	static int time = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] tmp = br.readLine().split(" ");
		R = Integer.parseInt(tmp[0]);
		C = Integer.parseInt(tmp[1]);

		map = new Pos[R][C];
		visited = new boolean[R][C];

		for (int i = 0; i < R; i++) {
			tmp = br.readLine().split("");
			for (int j = 0; j < C; j++) {
				map[i][j] = new Pos(i, j, tmp[j], 0);
				if (map[i][j].str.equals("*")) {
					waterStarts.add(map[i][j]);
				}
				if (map[i][j].str.equals("S")) {
					map[i][j].time = 1;
					start = map[i][j];
				}
			}
		}

		floodFill();
//		print();
		visited = new boolean[R][C];
		move(start);

		if (time == 0) {
			System.out.println("KAKTUS");
		} else {
			System.out.println(time -= 1);
		}

	}

	static void move(Pos now) {
//		System.out.println("고슴도치 이동 nx : "+now.r+"/ ny : "+now.c +"/ now.t : " +now.time +"/ map.str" + map[now.r][now.c].str);

		if (map[now.r][now.c].str.equals("D")) {
			time = now.time;
			return;
		}

		for (int i = 0; i < 4; i++) {
			int nx = now.r + dx[i];
			int ny = now.c + dy[i];

			// 범위안에 안들면 패스
			if (!(nx >= 0 && ny >= 0 && nx < R && ny < C))
				continue;
			if (map[nx][ny].str.equals("*") && map[nx][ny].time == 0)
				continue;
			if (map[nx][ny].time > now.time || map[nx][ny].str.equals("D")) {
				move(new Pos(nx, ny, "S", now.time + 1));

			}
		}
		// *이고 현재시간보다 크면 이동가능 (같아도안됨)
	}

	static void floodFill() {
		Deque<Pos> q = new ArrayDeque<Pos>();
		for (Pos p : waterStarts) {
			q.offer(p);
			visited[p.r][p.c] = true;
		}

		while (!q.isEmpty()) {
			Pos current = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = current.r + dx[i];
				int ny = current.c + dy[i];

				// 범위안에 안들면 패스
				if (!(nx >= 0 && ny >= 0 && nx < R && ny < C))
					continue;
				if (visited[nx][ny])
					continue;
				if (map[nx][ny].str.equals(".")) {
					map[nx][ny].str = "*";
					map[nx][ny].time = current.time+1;

					q.offer(map[nx][ny]);
					visited[nx][ny] = true;
				}

			}
		}

	}

	static void print() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.print(map[i][j] + " ");
			}

			System.out.println();
		}
	}
}
