package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class SEA_보물상자비밀번호 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			Set<Integer> set = new HashSet<>();
			String pw = br.readLine();
			
			String[] pw_arr = new String[4];
			for(int j = 0; j < n / 4; j++) {
				for(int i = 0; i < 4; i++) {
					set. add(Integer.parseInt(pw.substring(0 +(i * n/4), n/4 + (i * n/4)), 16));
				}
				pw = pw.charAt(pw.length()-1) + pw.substring(0,pw.length()-1);
			}

			Object[] result = set.toArray();
			Arrays.sort(result);
			
			System.out.println(result[result.length - k]);
					

//			}
		}
	}
}
