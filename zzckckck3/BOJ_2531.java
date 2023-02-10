package BOJ_10;

import java.util.*;
import java.io.*;
import static java.lang.Integer.*;

public class BOJ_2531 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
        int N = Integer.parseInt(st.nextToken()); // 벨트의 접시 수
        int d = Integer.parseInt(st.nextToken()); // 초밥 가지수
        int k = Integer.parseInt(st.nextToken()); // 연속 접시의 수
        int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호
        
        int arr[] = new int[N];
        boolean sushi[];
        
        for(int i = 0; i < N; i++) {
        	arr[i] = Integer.parseInt(br.readLine());
        }
        
        int start = 0;
        int end = start + 1;
        int ans = MIN_VALUE;
        
        while(true){
            if(start == N) {
            	break;
            }
            int len = 1;

            sushi = new boolean[d + 1];
            sushi[arr[start]] = true;


            while (true) {
                if (len == k){
                    sushi[c] = true; 
                    int cnt = 0;
                    for(int i = 0; i <= d; i++){
                        if(sushi[i]) {
                        	cnt++;
                        }
                    }
                    ans = Math.max(ans, cnt);
                    start++; 
                    if(start == N - 1) {
                    	end = 0;
                    }
                    else {
                    	end = start + 1;
                    }
                    break; 
                }
                sushi[arr[end++]] = true;
                len++;
                if(end == N){
                    end = end - N;
                }
            }
        }
        System.out.println(ans);
	}
}
