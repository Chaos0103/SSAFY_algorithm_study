import java.util.Scanner;

public class Main {
	static final long mod = 1000000000L;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		long[][] dp = new long[n+1][10];
		
		for(int i = 1; i < 10; i++) {
			dp[1][i] = 1L;
		}
		
		for(int i = 2; i <= n; i++) {
			for(int j = 0; j < 10; j++) {
				if(j == 0) {
					dp[i][0] = dp[i-1][1] ;
				}else if(j == 9) {
					dp[i][j] = dp[i-1][8];
				}else {
					dp[i][j] += dp[i-1][j-1];
					dp[i][j] += dp[i-1][j+1];
				}
				dp[i][j] = dp[i][j] % mod;
			}
		}
		
		int result = 0;
		for(int i = 0; i < 10; i++) {
			result += dp[n][i];
			result %= mod;
		}	
		System.out.println(result);
	}
}
		
		
//		for(int i = 1; i <= n; i++) {
//			for(int j = 0; j < 10; j++) {
//				System.out.print(dp[i][j] + " ");
//			}
//			System.out.println();
//		}
