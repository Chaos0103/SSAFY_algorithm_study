import java.util.*;
import java.io.*;

public class BOJ_5052 {
	static List<String> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));

		int tc = Integer.parseInt(br.readLine());

		loop1: for (int t = 0; t < tc; t++) {
			list = new ArrayList<String>();
			int N = Integer.parseInt(br.readLine());

			for (int i = 0; i < N; i++) {
				list.add(br.readLine());
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
