package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BOJ_5052 {
	static int n;
	static String[] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc = 0; tc < T; tc++) {
			n = Integer.parseInt(br.readLine());
			arr = new String[n];
			for(int i = 0; i < n; i++) {
				String num = br.readLine();
				arr[i] = num;
			}
			Arrays.sort(arr);
			

			boolean result = check();
			if(result) {
				System.out.println("YES");
			}else {
				System.out.println("NO");
			}
		}
		
	}
	
	static boolean check() {
		for(int i = 0; i < n-1; i++) {
			if(arr[i].length() >= arr[i+1].length()) {
				continue;
			}
			String temp = arr[i+1].substring(0, arr[i].length());
			if(temp.equals(arr[i])) {
				return false;
			}
		}
		return true;

		
	}
	
	
	static class Phone implements Comparable<Phone>{
		String num;
		int length;
		
		public Phone(String num, int length) {
			this.num = num;
			this.length = length;
		}
		
		
		@Override
		public String toString() {
			return "Phone [num=" + num + ", length=" + length + "]";
		}


		@Override
		public int compareTo(Phone o) {
			// TODO Auto-generated method stub
			return this.length - o.length;
		}
	}

}
