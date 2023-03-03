import java.io.*;
import java.util.*;

/**
 * 엄청난 빡구현 문제...
 * 
 */
public class BOJ_20056 {
	static class FireBall implements Comparable<FireBall> {
		int m; // 질량m
		int d; // 방향d
		int s; // 속력s
		boolean canMove;

		@Override
		public String toString() {
			return "F / m : " + m;
//			return "FireBall [m=" + m + ", d=" + d + ", s=" + s + ", canMove=" + canMove + "]";
		}

		public FireBall(int m, int d, int s, boolean canMove) {
			super();
			this.m = m;
			this.d = d;
			this.s = s;
			this.canMove = canMove;
		}

		public int compareTo(FireBall o) {
			return Boolean.compare(o.canMove, this.canMove);
		}

	}

	static int N, M, K;
	static int[] dx = { 1, 1, 0, -1, -1, -1, 0, 1 };
	static int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static ArrayList<ArrayList<PriorityQueue<FireBall>>> map = new ArrayList<>();

	// 파이어볼 이동
	static void move(int r, int c, FireBall fb) {
		fb.canMove = false;
		// 북쪽으로 이동
		if (fb.d == 0) {
			int nextR = (r - fb.s) % N;
			if (nextR < 0)
				nextR += N;
			map.get(nextR).get(c).add(fb);
		}

		// 북동쪽으로 이동
		if (fb.d == 1) {
			int nextR = (r - fb.s) % N;
			int nextC = (c + fb.s) % N;
			if (nextR < 0)
				nextR += N;
			map.get(nextR).get(nextC).add(fb);
		}

		// 동쪽으로 이동
		if (fb.d == 2) {
			int nextC = (c + fb.s) % N;
			map.get(r).get(nextC).add(fb);
		}

		// 남동쪽으로 이동
		if (fb.d == 3) {
			int nextR = (r + fb.s) % N;
			int nextC = (c + fb.s) % N;
			map.get(nextR).get(nextC).add(fb);
		}

		// 남쪽으로 이동
		if (fb.d == 4) {
			int nextR = (r + fb.s) % N;
			map.get(nextR).get(c).add(fb);
		}

		// 남서쪽으로 이동
		if (fb.d == 5) {
			int nextR = (r + fb.s) % N;
			int nextC = (c - fb.s) % N;
			if (nextC < 0)
				nextC += N;
			map.get(nextR).get(nextC).add(fb);
		}

		// 서쪽으로 이동
		if (fb.d == 6) {
			int nextC = (c - fb.s) % N;
			if (nextC < 0)
				nextC += N;
			map.get(r).get(nextC).add(fb);
		}

		// 북서쪽으로 이동
		if (fb.d == 7) {
			int nextR = (r - fb.s) % N;
			int nextC = (c - fb.s) % N;
			if (nextR < 0)
				nextR += N;
			if (nextC < 0)
				nextC += N;
			map.get(nextR).get(nextC).add(fb);
		}

	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();

		for (int i = 0; i < N; i++) {
			map.add(new ArrayList<>());
			for (int j = 0; j < N; j++) {
				map.get(i).add(new PriorityQueue<>());
			}
		}

		for (int i = 0; i < M; i++) {
			int r = sc.nextInt() - 1;
			int c = sc.nextInt() - 1;
			int m = sc.nextInt();
			int s = sc.nextInt();
			int d = sc.nextInt();

			map.get(r).get(c).offer(new FireBall(m, d, s, true));
		}

		while (K > 0) {
			// 0. K 줄여주기
			K--;

			// 1. 모든 파이어볼 자신의 방향 d로 속력 s칸 만큼 이동 (넘어가면 연결된 곳으로)
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					// 해당 칸에 파이어볼이 있으면
					if (map.get(i).get(j).size() != 0) {
						// 모두 이동 시킨다.

						PriorityQueue<FireBall> q = map.get(i).get(j);

						while (!q.isEmpty()) {
							if (q.peek().canMove) {
								FireBall current = q.poll();

								if (current.canMove) {
									move(i, j, current);
								}
							} else {
								break;
							}
						}
					}
				}
			}

			// 2. 이동이 모두 끝난 뒤, 2개 이상의 파이어볼이 있는 칸에서는 다음과 같은 일이 일어난다
			// 2-1. 파이어볼 하나로 합치기.
			// 2-2. 4개로 나누기
			// 질량 = 질량의 합 / 5 (버림)
			// 속력 = 속력의 합 / 파이어볼 개수

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					// 파이어볼이 있으면 이동가능하게 처리해주기.
					PriorityQueue<FireBall> q = map.get(i).get(j);
					for (FireBall fb : q) {
						fb.canMove = true;
					}

					int mSum = 0;
					int sSum = 0;

					if (q.size() >= 2) {
						int size = q.size();
						ArrayList<Integer> directions = new ArrayList<Integer>();
						while (!q.isEmpty()) {
							FireBall current = q.poll();
							mSum += current.m;
							sSum += current.s;
							directions.add(current.d);
						}

						int oddCnt = 0;
						int evenCnt = 0;

						for (int d : directions) {
							if (d % 2 == 1) {
								oddCnt++;
							} else {
								evenCnt++;
							}
						}

						int[] dirs = new int[4];
						if (oddCnt == size || evenCnt == size) {
							dirs[0] = 0;
							dirs[1] = 2;
							dirs[2] = 4;
							dirs[3] = 6;
						} else {
							dirs[0] = 1;
							dirs[1] = 3;
							dirs[2] = 5;
							dirs[3] = 7;
						}

						// 만약 질량의 합의 버림이 0이면 파이어볼 사라짐.
						if (Math.ceil(mSum / 5) == 0) {
							continue;
						}

						// 질량이 1이상이면 4개로 나눔
						else {
							for (int k = 0; k < 4; k++) {
								q.add(new FireBall((int) Math.ceil(mSum / 5), dirs[k], sSum / size, true));
							}
						}

					}

				}
			}
		}
		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				PriorityQueue<FireBall> q = map.get(i).get(j);
			
				while(!q.isEmpty()) {
					sum += q.poll().m;
				}
			}
		}
		System.out.println(sum);
	}

	static void print() {
		for (int i = 0; i < N; i++) {
			System.out.println(map.get(i));
		}
	}
}