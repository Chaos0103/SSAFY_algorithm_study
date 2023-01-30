package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1476 {
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
	    int e = Integer.parseInt(st.nextToken());
	    int s = Integer.parseInt(st.nextToken());
	    int m = Integer.parseInt(st.nextToken());
	    int Earth = 0;
	    int Sun = 0;
	    int Moon = 0;
	    int year = 0;
	    
	    while (true) {
	        year++;
	        Earth++;
	        Sun++;
	        Moon++;
	        if (Earth == 16) {
	        	Earth = 1;
	        }
	        if (Sun == 29) {
	        	Sun = 1;
	        }
	        if (Moon == 20) {
	        	Moon = 1;
	        }
	        if (e == Earth && m == Moon && Sun == s) {
	        	System.out.print(year);
	        	break;
	        }
	    }
	}
}