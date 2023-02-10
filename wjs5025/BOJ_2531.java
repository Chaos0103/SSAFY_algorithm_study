import java.util.*;
import java.io.*;

// N = 벨트에 놓인 접시 수
// d = 초밥의 가짓수
// k = 연속해서 먹는 접시 수
// c = 쿠폰 번호
// 출력 = 주어진 벨트에서 먹을 수 있는 초밥 가짓수의 최대값.

public class Main {
	static HashMap<Integer, Integer> map = new HashMap<>();
	static int N, d, k, c;
	static int[] sushi;
	static int maxCnt;

	// 초밥 몇 종류 먹었나 반환
	static int getSomeKind() {
		int count = 0;
		
		for (int key : map.keySet()) {
			if (map.get(key) != 0) {
				count++;
			}
		}

		return count;
	}

	// 투포인터 탐색
	static void twoPointerSearch() {
		int start = 0;
		int end = k - 1;

		// 슬라이딩 윈도우 하면서, 다음값 1더하고 이전값 1빼주기

		// 초기값 세팅
		for (int i = 0; i < k; i++) {
			map.put(sushi[i], map.get(sushi[i]) + 1);
		}
		maxCnt = Math.max(maxCnt, getSomeKind());

		while (start != N) {
			end++;
			map.put(sushi[start % N], map.get(sushi[start % N]) - 1);
			map.put(sushi[end % N], map.get(sushi[end % N]) + 1);
			maxCnt = Math.max(maxCnt, getSomeKind());
			start++;
		}
		
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		d = sc.nextInt();
		k = sc.nextInt();
		c = sc.nextInt();

		sushi = new int[N];

		for (int i = 0; i < N; i++) {
			sushi[i] = sc.nextInt();
			map.put(sushi[i], 0);
		}
		map.put(c, 1);

		twoPointerSearch();
		System.out.println(maxCnt);
	}
}