package BOJ__10;

import java.io.*;
import java.util.*;

public class BOJ_1644 {
	static boolean pArr[] = new boolean[4000001];
	static List<Integer> pList = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        int n = 4000000;
        // 소수가 아니면 true
        // https://ko.wikipedia.org/wiki/%EC%97%90%EB%9D%BC%ED%86%A0%EC%8A%A4%ED%85%8C%EB%84%A4%EC%8A%A4%EC%9D%98_%EC%B2%B4
        pArr[0] = true;
        pArr[1] = true;

        for (int i = 2; i * i <= n; i++) {
            if (!pArr[i]) {
                for (int j = i * i; j <= n; j += i) {
                    pArr[j] = true;
                }
            }
        }

        for (int i = 0; i < 4000001; i++) {
            if (!pArr[i]) {
                pList.add(i);
            }
        }
        
        int ans = 0;
        int left = 0;
        int right = 0;
        
        while (true) {
        	int sum = 0;
        	for (int i = left; i <= right; i++) {
				sum += pList.get(i);
			}
        	if(sum == N) {
        		ans++;
        		right++;
        	} else if (sum < N) {
				right++;
			} else {
				left++;
			}
        	if (right == pList.size() || left == pList.size() || left > right) {
                break;
            }
        }
        System.out.println(ans);
	}
}
