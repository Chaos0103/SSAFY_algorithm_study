package BJ1476;

import java.util.*;
import java.io.*;

public class Main {

	public static int addE(int E) {
		return (E > 14) ? E = 1 : E+1;
	}

	public static int addS(int S) {
		return (S > 27) ? S = 1 : S+1;
	}

	public static int addM(int M) {
		return (M > 18) ? M = 1 : M+1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		String input = br.readLine();
			
		int yearCnt = 0;
		int[] years = {0,0,0};
		
		while (true) {
			sb.append(years[0]);
			sb.append(" ");
			sb.append(years[1]);
			sb.append(" ");
			sb.append(years[2]);
			
			if (input.equals(sb.toString())) {
				bw.write(Integer.toString(yearCnt));
				bw.flush();
				return;
			} else {
				sb.setLength(0);
				years[0] = addE(years[0]);
				years[1] = addS(years[1]);
				years[2] = addM(years[2]);
				yearCnt += 1;
			}
		}
	}
}
