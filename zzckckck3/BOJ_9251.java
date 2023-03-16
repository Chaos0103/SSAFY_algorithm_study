package Algorithm_230316;

import java.io.*;
import java.util.*;

public class BOJ_9251 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String A = br.readLine();
		String B = br.readLine();
		
		int[][] LCS = new int[A.length()+1][B.length()+1];
		
		for (int i = 0; i <= A.length(); i++) {
			for (int j = 0; j <= B.length(); j++) {
				if (i == 0 || j == 0) {
					LCS[i][j] = 0;
				}
				else if (A.charAt(i - 1) == B.charAt(j - 1)) {
					LCS[i][j] = LCS[i-1][j-1] + 1; 
				}
				else {
					LCS[i][j] = Math.max(LCS[i-1][j], LCS[i][j-1]);
				}
			}
		}
		System.out.println(LCS[A.length()][B.length()]);
	}
}