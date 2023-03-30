import java.io.*;
import java.util.*;

public class BOJ_3020 {
	static int[] map;
	static int N, H;
	static int min = Integer.MAX_VALUE;
	static int cnt = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] tmp = br.readLine().split(" ");
		N = Integer.parseInt(tmp[0]);
		H = Integer.parseInt(tmp[1]);

		map = new int[H + 1];

		for (int j = 1; j <= N; j++) {
			int input = Integer.parseInt(br.readLine());
				// 짝수면 석순
			for (int i = 1; i <= H; i++) {
				if (j % 2 == 1) {
					if ((H - input) <= i) {
						map[i]++;
					}
				} else {
					if (input > i) {
						map[i]++;
					}
				}
				
				if (j == N) {
					min = Math.min(min, map[i]);
				}
			}
		}

		// 정렬
		Arrays.sort(map);
//		System.out.println(Arrays.toString(map));
		binarySearch(1, H, min);
		System.out.println(String.format("%d %d", min, cnt));
	}

	static int binarySearch(int start, int end, int key) {
		if (start <= end) {
			int mid = (start + end) / 2;

			if (key == map[mid]) {
				while (end >= 1) {
					if (map[mid] == min) {
						cnt++;
						end--;
					}
				}
				return mid;
			} else if (key < map[mid]) {
				return binarySearch(start, mid - 1, key);
			} else {
				return binarySearch(mid + 1, end, key);
			}
		}
		return -1;
	}

	static void print(int[][] map) {
		System.out.println();
		for (int i = 1; i <= H; i++) {
			for (int j = 1; j <= N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}