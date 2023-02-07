import java.util.*;
import java.io.*;

/*
 * 솔루션을 보면서 이해하려고 했는데 (https://st-lab.tistory.com/277)
 * 원리 자체를 잘 이해하지 못했습니다. 복습하겠습니다.
 * 
 * */
public class Main {
	static int N, C;
	static int[] house;

	// 현재 위치(d)에서 설치가능한 공유기 대수
	public static int counting(int d) {
		// 거리 d를 간격으로 두고 1번 집부터 차례로 설치할건데
		int cnt = 1; 
		int lastHouse = house[0];

		// 다음집까지의 거리가 d보다 크면 공유기 설치
		for (int i = 1; i < N; i++) {
			if (house[i] - lastHouse >= d) {
				cnt++;
				lastHouse = house[i];
			}
		}
		
		// 총 몇대 설치했는지 반환
		return cnt;
	}

//	현재 m에서 (h를 늘리면서) 설치가능한 최대 공유기 개수를 구한다. 
// 최대 공유기 개수가 C개에 도달하면 l을 줄이면서 최대 거리를 구한다?
	public static void binarySearch(int l, int h) {
		int m;

		while (l < h) {
			m = (h + l) / 2; // 인덱스가 아닌 '거리=distance'라고 생각을 하자.

			// 만약 설치 대수가 C보다 많아버리면,
			// 여유로운 것이니, l을 늘려서 최대 거리를 찾는다.
			if (counting(m) >= C) {
				l = m + 1;
			} else {
				// 설치대수가 C보다 작으면
				// h를 줄여 거리를 좁힌다.
				h = m;
			}

		}
		
		// 왜 l-1이 최대 값이 될까? = 이해가 안되는 부분.
		System.out.println(l - 1);
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		C = sc.nextInt();
		house = new int[N];

		for (int i = 0; i < N; i++) {
			house[i] = sc.nextInt();
		}

		Arrays.sort(house);
		binarySearch(0, house[N - 1] - house[0]);
	}
}