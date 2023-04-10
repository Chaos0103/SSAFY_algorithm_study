package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


public class BOJ_1963 {
	static boolean[] sosu = new boolean[10000];
	static boolean[] visited;
	static int[] cntArr;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		sosu[0] =  sosu[1] = true;
		for(int i = 2; i * i < 10000; i++) {
			if(!sosu[i]) {
				for(int j = i*i; j<10000; j+=i) {
					sosu[j] = true;
				}
			}
		} // 소수 만들기
		
		for(int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			visited = new boolean[10000];
			cntArr = new int[10000];
			bfs(a);
			
			System.out.println(cntArr[b]);
		}
		
	
	}
	
	static void bfs(int start) {
		Queue<Integer> q = new ArrayDeque<>();
		visited[start] = true;
		q.add(start);
		while(!q.isEmpty()) {
			int cur =  q.poll();
			for (int i = 3; i >= 0; i--) { 
                for (int j = 0; j <= 9; j++) { 
                    if (i == 3 && j == 0) { // 천의 자리 검사
                        continue;
                    }
                    String s = Integer.toString(cur);
                    char[] tempArr = s.toCharArray();
                    int idx = tempArr[Math.abs(i-3)] -'0';
                    int temp = (int)Math.pow(10, i) * idx;
                    int k = cur - temp;
                    k += (int)Math.pow(10, i) * j;
//                    System.out.println(" i : " + i + " j : " + j + "cur :"  + cur + " k :" + k);
                    if (!sosu[k] && !visited[k]) { 
                        q.add(k); 
                        visited[k] = true; 
                        cntArr[k] = cntArr[cur] + 1; 
                    }
                }
            }
		}
	}
}
