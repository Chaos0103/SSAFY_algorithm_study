package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 문제 : 두 수의 합
 * 풀이시간 : 20분
 * 사용방법 : 투포인터( 처음과 끝)
 *  
 *  */

public class BOJ_3273 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine()); 
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int[] arr = new int[n];
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int x = Integer.parseInt(in.readLine());
		
		//데이터 입력 완
		Arrays.sort(arr);
		int idx1 = 0;
		int idx2 = n-1;
		int cnt = 0;
		int sum = 0;
		
		while(idx1 < idx2) {
			sum = arr[idx1] + arr[idx2];
			if(sum > x) {
				idx2--;
			}else if(sum < x) {
				idx1++;
			}else {
				cnt++;
				idx1++;
				idx2--;
			}

		}
		System.out.println(cnt);
		
		
	}
}
