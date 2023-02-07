package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


/*
 * 문제 : 두 용액
 * 풀이시간 : 1시간
 * 풀이방법 : 투포인터
 * 참고 : 투포인터 개념 검색.
 */

public class BOJ_2470 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine()); //용액 개수
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int[] arr = new int[n];
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		int idx1 = 0;
		int idx2 = arr.length-1;
		
		int min_idx1 = 0;
		int min_idx2 = 0;
		
		int min = 2000000001; // Integer.MAX_VALUE를 쓰자 ..........................!!
		while(idx1 < idx2) {
			int sum = arr[idx2] + arr[idx1]; //한쪽이 양수니 +
			int mix = Math.abs(sum);
			if(mix == 0) {
				min = mix;
				min_idx1 = idx1;
				min_idx2 = idx2;
				break;
			}
			if(min > mix) {
				min = mix;
				min_idx1 = idx1;
				min_idx2 = idx2;
			}
			if(sum > 0) { // idx2(양수 부)가 더 높은 상태 idx2를 줄여야함
				idx2--;
			}else { //0 일때 위에서 break sum이 더 낮으면 idx1 높여야됨
				idx1++;
			}
		}
		if(arr[min_idx1] > arr[min_idx2]) {
			int temp = min_idx2;
			min_idx2 = min_idx1;
			min_idx1 = temp;
		}else {
			System.out.println(arr[min_idx1] + " " + arr[min_idx2]);	
		}
	}
}
