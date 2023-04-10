import java.util.*;
import java.io.*;

public class BOJ_9251 {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		
		String str1 = sc.next();
		String str2 = sc.next();
		
		int[][] memo = new int[str1.length()+1][str2.length()+1];
		
		// 0번 방들은 0으로 초기화
		for(int i = 0; i <= str1.length(); i++)
			memo[i][0] = 0;
		for(int i = 0; i <= str2.length(); i++)
			memo[0][i] = 0;
		
		
		/* 순환식
		 * if(x[i] == y[i])
				memo[i][j] = memo[i - 1][j - 1] + 1;
			else
				memo[i][j] = max(c[i-1][j], c[i][j-1]);
		 * */
		// 순환식에 따라 메모이제이션 완성
		for(int i = 1; i <= str1.length(); i++) {
			for(int j = 1; j <= str2.length(); j++) {
				if(str1.charAt(i-1) == str2.charAt(j-1))
					memo[i][j] = memo[i-1][j-1] +1;
				else 
					memo[i][j] = Math.max(memo[i-1][j], memo[i][j-1]);
			}
		}
		
		System.out.println(memo[str1.length()][str2.length()]);
		
		return;
	}
}

//https://binghedev.tistory.com/12