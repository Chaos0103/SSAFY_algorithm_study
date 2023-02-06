package BOJ_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1759 {
	public static int a;
	public static int n;
	public static char arr[];
	public static char pwd[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokenizer = new StringTokenizer(reader.readLine(), " ");
		a = Integer.parseInt(tokenizer.nextToken());
		n = Integer.parseInt(tokenizer.nextToken());
		
		arr = new char[n];
		pwd = new char[a];
		
		tokenizer = new StringTokenizer(reader.readLine());
		
		for (int i = 0; i < n; i++) {
			arr[i] = tokenizer.nextToken().charAt(0);
		}
		
		Arrays.sort(arr);
		
		dfs(0, 0);
	}
	
	public static boolean checkVowelConso() {
		int vowel = 0;
		int conso = 0;
		
		for (int i = 0; i < pwd.length; i++) {
			if (pwd[i] == 'a' || pwd[i] == 'e' || pwd[i] == 'i' || pwd[i] == 'o' || pwd[i] == 'u') {
				vowel++;
			} else {
				conso++;
			}
		}
		if(vowel >= 1 && conso >= 2) {
			return true;
		}
		return false;
	}
	
	public static void dfs(int x, int idx) {
		if(idx == a) {
			if(checkVowelConso()) {
				System.out.println(pwd);
			}
			return;
		}
		
		for (int i = x; i < n; i++) {
			pwd[idx] = arr[i];
			dfs(i + 1, idx + 1);
		}
	}
}
