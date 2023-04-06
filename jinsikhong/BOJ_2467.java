package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2467 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[n];
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		boolean flag = false;
		int max = Integer.MAX_VALUE;
		int leftIdx = -1;
		int rightIdx = -1;
		
		//이분 탐색 풀이 ! 
		for(int i = 0; i < n; i++) {
			if(flag) {
				break;
			}
			int start = i+1;
			int end = n-1;
			while(start <= end) {
				int mid = (start+end) / 2;
				
				int temp = arr[i] + arr[mid];
				if(Math.abs(temp) < max) {
					leftIdx = i;
					rightIdx = mid;
					max = Math.abs(temp);
				}
				
				if(temp == 0) {
					flag = true;
					leftIdx = i;
					rightIdx = mid;
					break;
				}
				
				if(temp < 0) {
					start = mid + 1;
				}
				
				if(temp > 0) {
					end = mid - 1;
				}
			}
		}
		System.out.println(arr[leftIdx] + " " + arr[rightIdx]);
		
	}
}
