import java.util.*;
import java.io.*;

// Map으로 풀 수 있대서,
// Map으로도 풀어보았습니다.

public class Main {
	static HashMap<Integer, Integer> map = new HashMap<>();
	static int N, M;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();

		for (int i = 0; i < N; i++) {
			int input = sc.nextInt();

			if (map.get(input) == null) {
				map.put(input, 1);
			} else {
				map.put(input, map.get(input) + 1);
			} 
		}

		M = sc.nextInt();

		for (int i = 0; i < M; i++) {
			int input = sc.nextInt();
			if (map.get(input) != null) {
				sb.append(map.get(input)).append(" ");
			} else {
				sb.append("0 ");
			}
		}

		System.out.println(sb.toString());
	}
}