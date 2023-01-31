import java.util.*;

public class BOJ_1476 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int E = sc.nextInt();
		int S = sc.nextInt();
		int M = sc.nextInt();
		
		int e = 1;
		int s = 1;
		int m = 1;
		int result = 1;
		while(true) {
			if (e == E && s == S && m == M) {
				break;
			}
			e = e >= 15 ? 1 : e + 1;
			s = s >= 28 ? 1 : s + 1;
			m = m >= 19 ? 1 : m + 1;
			result++;
		}
		
		System.out.println(result);
	}
}
