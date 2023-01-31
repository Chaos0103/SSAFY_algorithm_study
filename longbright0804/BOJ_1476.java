import java.util.Scanner;

/**
 * SSAFY 알고리즘 스터디 1일차 - 날짜 계산
 * 23.01.30
 * @author YoungHwan
 *
 */
public class BOJ_1476 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int e = sc.nextInt();
		int s = sc.nextInt();
		int m = sc.nextInt();

		int cnt = 0;
		while (true) {
			if ((cnt % 15) + 1 == e && (cnt % 28) + 1 == s && (cnt % 19) + 1 == m) {
				break;
			}
			cnt++;
		}
		System.out.println(++cnt);
	}
}
