package com.yoojin.boj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BJ_5052 {
	static int n;
	static List<String> strings = new ArrayList<>();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 0;t<T;t++) {
			n = Integer.parseInt(br.readLine());
			strings = new ArrayList<>();
			for(int i=0;i<n;i++) {
				strings.add(br.readLine());
			}
			
			// string 배열 정렬
			Collections.sort(strings);
			
			boolean result = true;
			// 현재값은 다음값과 비교하여 같은경우 no 출력 break;
			for(int i=0;i<strings.size()-1;i++) {
				String cur = strings.get(i);
				String next = strings.get(i+1);
				
				if(cur.length() < next.length() && cur.equals(next.substring(0,cur.length()))) {
					result = false;
					break;
				}
			}
			
			if(result) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}
	}
}
