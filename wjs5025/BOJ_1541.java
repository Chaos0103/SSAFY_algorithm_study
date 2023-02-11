import java.io.*;
import java.util.*;

public class Main {
	public static int StringCalc(String str) {
		int sum = 0;
		StringTokenizer st = new StringTokenizer(str,"+");
		while (st.hasMoreTokens()) {
			sum += Integer.parseInt(st.nextToken());
		}
		
		return sum;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		boolean isFirstMinus = false;
		
		if (input.charAt(0) == '-') {
			isFirstMinus = true;
		}
		
		StringTokenizer st = new StringTokenizer(input,"-");
		int sum = 0;
		// 수식에 괄호를 적당히 넣어서 최소값을 만드는 문제.
		if (isFirstMinus) {
			sum -= StringCalc(st.nextToken());
		} else {
			sum += StringCalc(st.nextToken());
		}
		
		while(st.hasMoreTokens()) {
			sum -= StringCalc(st.nextToken());
		}
		
		System.out.println(sum);
	}
}