import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * SSAFY 알고리즘 스터디 12일차 - 잃어버린 괄호
 * 30분
 * @author YoungHwan
 *
 */
public class BOJ_1541 {
	static String[] add;
	static String[] sub;
	static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		sub = br.readLine().split("-"); // 빼기 기준 분리
		for (int i = 0; i < sub.length; i++) {
			int sum = 0;
			add = sub[i].split("\\+");	// 덧셈 기준 분리
			for (int j = 0; j < add.length; j++) {
				sum += Integer.parseInt(add[j]);
			}
			
			if (i == 0) {
				result += sum;
			} else {
				result -= sum;
			}
		}
		System.out.println(result);
	}
}
