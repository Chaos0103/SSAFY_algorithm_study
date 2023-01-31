public import java.util.*;
import java.io.*;

public class Main {
	static int L, C;
	static char[] alphas;
	static char[] password;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();

	// 모음 1개, 자음 2개 이상인가?
	static boolean checkPassword() {
		int vowelCnt = 0;
		int consonantCnt = 0;
		
		for (char alpha : password) {
			if (alpha == 'a' || alpha == 'e' || alpha == 'i' || alpha == 'o' || alpha == 'u') {
				vowelCnt++;
			} else {
				consonantCnt++;
			}
		}
		
		return (vowelCnt >= 1 && consonantCnt >= 2) ? true : false;
	}

	// 순열 구하기
	static void getPermutation(int idx) {
		if (idx == L) {
			if(checkPassword()) {
				for (int i = 0; i < L; i++) {
					sb.append(password[i]);
				}
				sb.append("\n");
			}
			return;
		}
		
		for (int i = 0; i < C; i++) {
			if (visited[i])
				continue;

			for (int j = 0; j <= i; j++) {
				visited[j] = true;
			}
			
			password[idx] = alphas[i];

			getPermutation(idx + 1);

			for (int j = 0; j <= i; j++) {
				visited[j] = false;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		L = sc.nextInt();
		C = sc.nextInt();

		alphas = new char[C];
		visited = new boolean[C];
		password = new char[L];

		// 입력받기.
		for (int i = 0; i < C; i++) {
			alphas[i] = sc.next().charAt(0);
		}
		
		Arrays.sort(alphas);
		getPermutation(0);
		System.out.println(sb);
	}
} {
    
}
