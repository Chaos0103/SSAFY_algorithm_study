package com.yoojin.programmers;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PGS_60057 {
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String s = in.readLine();
		int n = s.length();
		System.out.println(n);
		if(n==1) {
			System.out.println(1);
			return;
		}
		int min = Integer.MAX_VALUE;
		for(int i = 1;i<=n/2;i++) {
			int stringNum = i;
			int cnt = 1;
			int j;
			for(j = i;j+(i-1) <n;j+=i) {
				if(s.substring(j-i,j).equals(s.substring(j,j+i))) {
					// 두 문자열 이 같다면
					if(cnt != 1) { 
						if(String.valueOf(cnt+1).charAt(0) == '1' && String.valueOf(cnt+1).length() > 1 &&  Integer.parseInt(String.valueOf(cnt+1).substring(1))==0) {
							stringNum+=1;
						}
						cnt++;
					} else {// 개수가 0개이면 
						stringNum += 1;
						cnt++;
					}
					continue;
					
				}
				// 같지않다면
				cnt = 1;
				stringNum += i;
			}
			if (j<n) {
				stringNum += (n-j); 
			}
			min = Math.min(min, stringNum);
		}
		
		System.out.println(min);
	}
}