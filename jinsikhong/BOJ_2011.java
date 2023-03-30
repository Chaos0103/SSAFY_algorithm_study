package BJ;

import java.util.Scanner;

public class BJ_2011 {
	public static void main(String[] args) {
		int mod = 1000000;
		Scanner sc = new Scanner(System.in);
		String num = sc.next();
		int[] dp = new int[num.length()+1];
		
		//dp[n] = dp[n-1] + dp[n-2]
		
		//이전 숫자가 1이면 dp[n] = dp[n-1] + dp[n-2]
		//이전 숫자가 2면 지금 온 숫자가 0~6 이면 dp[n] = dp[n-1] + dp[n-2]
		if(num.charAt(0) == '0') {
			System.out.println(0);
			return;
		}
		
		dp[0] = 1;
		dp[1] = 1;
		
//		if(num.charAt(0) == '1') {
//			dp[2] = 2;
//		}else if(num.charAt(0) == '2' && num.charAt(1) > 0 && num.charAt(1) < 7) {
//			dp[2] = 2;
//		}else {
//			dp[2] = 1;
//		}
		
		for(int i = 2; i <= num.length(); i++) {
			if(num.charAt(i-1) =='0') {
				if(num.charAt(i-2) == '1' || num.charAt(i-2) == '2') {
					dp[i] = dp[i-2] % mod;
				}else {
					break;
				}
			}else {
				int res = Integer.parseInt(num.substring(i-2, i));
				if(res < 27 && res > 9) {
					dp[i] = (dp[i-1] + dp[i-2]) % mod;
				}else {
					dp[i] = dp[i-1] % mod;
				}
			}
			
			
//			if(num.charAt(i-1) == '1') {
//				dp[i] = dp[i-1] + dp[i-2];
//			}else if(num.charAt(i-2) == '2' && num.charAt(i-1) -'0' < 7) {
//				dp[i] = dp[i-1] + dp[i-2];
//			}else {
//				dp[i] = dp[i-1];
//			}
		}
		System.out.println(dp[num.length()] % mod);
	}
}
