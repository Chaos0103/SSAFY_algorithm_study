import java.util.Scanner;

public class Main {
	static long[] memo;
	
	public static void getResult(int n) {
		memo[1] = 1;
		memo[2] = 2;
		memo[3] = 4;
		
		for(int i = 4; i<=n; i++) {
			memo[i] = (memo[i-1]+memo[i-2]+memo[i-3])%1000000009;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int cnt = sc.nextInt();
		
		
		for (int i =0; i < cnt ; i++) {
			int input = sc.nextInt();
			if (input == 1) {
				sb.append(1).append("\n");
				continue;
			} else if (input == 2) {
				sb.append(2).append("\n");
				continue;
			} else if (input == 3) {
				sb.append(4).append("\n");
				continue;
			}
			memo = new long[input+1];
			getResult(input);
			sb.append(memo[input]).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}