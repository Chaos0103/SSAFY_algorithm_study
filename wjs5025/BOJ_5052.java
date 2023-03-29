import java.util.*;
import java.io.*;

public class BOJ_5052 {
	static List<String> list;

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		int tc = sc.nextInt();

		loop1: for (int t = 0; t < tc; t++) {
			list = new ArrayList<String>();
			int N = sc.nextInt();
			sc.nextLine();

			for (int i = 0; i < N; i++) {
				list.add(sc.nextLine());
			}

			Collections.sort(list);
			for (int i = 0; i < N - 1; i++) {
				String cur = list.get(i);
				if (cur.length() < list.get(i + 1).length()) {
					if (cur.equals(list.get(i + 1).substring(0, cur.length()))) {
						System.out.println("NO");
						continue loop1;
					}
				}
			}
			System.out.println("YES");
		}
	}
}
