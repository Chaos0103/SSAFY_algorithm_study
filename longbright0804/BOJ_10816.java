import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * SSAFY 알고리즘 스터디 8일차 - 숫자카드 2
 * HashMap 사용 20분
 * 이진탐색 도전 => 실패
 * -> 솔루션
 * 
 * @author YoungHwan
 *
 */
public class BOJ_10816 {
	static int n, m;
	static Map<Integer, Integer> map = new HashMap<>();
	static StringBuilder result = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		// map 입력(보유중인 수)
		for (int i = 0; i < n; i++) {
			int key = parseInt(st.nextToken());
			map.put(key, map.getOrDefault(key, 0) + 1);	// key 값 1 증가
		}
		m = parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		// 입력받은 key 값 별로 map 출력
		for (int i = 0; i < m; i++) {
			int key = parseInt(st.nextToken());
			result.append(map.getOrDefault(key, 0)).append(" ");
		}
		System.out.println(result);
	}
}
