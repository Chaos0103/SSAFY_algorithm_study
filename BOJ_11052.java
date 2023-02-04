import java.util.Scanner;

/**
 * 문제 : 카드 구매하기 / DP
 * 풀이시간 : 50분 (10분 구현, 40분 알고리즘 풀이)
 * 검색 : dp 점화식 검색(i번째 카드는 1번째 카드펙 + i-1번째 카드팩의 최대 경우의 수)
 *
 */
public class BOJ_11052 {
    /*
    문제 : 카드 구매하기
    풀이시간 : 40분 
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }
        int[] dp = new int[n+1];

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= i; j++){
                dp[i] = Math.max(dp[i],(arr[j-1] + dp[i-j]));
            }
        }
        System.out.println(dp[n]);
    }
}
