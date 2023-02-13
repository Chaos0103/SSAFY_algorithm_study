package BOJ_7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_10816 {
	public static int n, m;
	public static int[] arr1;
	public static int[] arr2;
	public static int ans = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(st.nextToken());
		arr1 = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr1[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		arr2 = new int[m];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			arr2[i] = Integer.parseInt(st.nextToken());
		}
		//=============================
		Arrays.sort(arr1);
		
		for (int i = 0; i < m; i++) {
			ans = upperBound(arr1, arr2[i]) - lowerBound(arr1, arr2[i]);
			sb.append(ans);
			sb.append(" ");
		}
		System.out.print(sb);
	}
	
	public static int lowerBound(int[] data, int target) {
	    int begin = 0;
	    int end = data.length;
	    
	    while(begin < end) {
	    	int mid = (begin + end) / 2;
	        
	        if(data[mid] >= target) {
	        	end = mid;
	        }
	        else {
	        	begin = mid + 1;
	        }
	    }
	    return end;
	}
	
	private static int upperBound(int[] data, int target) {
	    int begin = 0;
	    int end = data.length;
	    
	    while(begin < end) {
	    	int mid = (begin + end) / 2;
	        
	        if(data[mid] <= target) {
	        	begin = mid + 1;
	        }
	        else {
	        	end = mid;
	        }
	    }
	    return end;
	}
}
