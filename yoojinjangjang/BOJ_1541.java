package com.yoojin.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1541 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String expr = in.readLine();
		String[] exprs = expr.split("\\-");
		
		int result = 0;
		
		// - 로 나누고 각 배열 덧셈해서 빼기 하면 됨
		for(int i = 0;i<exprs.length;i++) {
			String[] plusExprs = exprs[i].split("\\+");
			int temp = 0;
			for(int j=0;j<plusExprs.length;j++) {
				temp += Integer.parseInt(plusExprs[j]);
			}
			if (i==0) {
				result += temp;
				continue;
			}
			result -= temp;
		}
		
		System.out.println(result);
	}
}
