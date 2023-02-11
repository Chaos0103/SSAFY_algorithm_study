package BOJ__12;

import java.io.*;
import java.util.*;

public class BOJ_1541 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] collectPlus = br.readLine().split("-");
		int ans = 0;
		
		for(int i = 0; i < collectPlus.length; i++) {
			int sum = 0;
			String[] cal = collectPlus[i].split("\\+");
			
			for(int j = 0; j < cal.length; j++) {
				sum += Integer.parseInt(cal[j]);
			}
			
			if(i == 0) {	// 0번째는 앞에 -가 없다
				ans += sum;
			} else {
				ans -= sum;
			}
		}
		System.out.println(ans);
	}
}
