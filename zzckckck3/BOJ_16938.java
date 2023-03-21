package Algorithm_230319;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16938 {
	static int N,L,R,X;
	static int question[], pick[];
	static int ans = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		question = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			question[i] = Integer.parseInt(st.nextToken());
		}
		
		pick = new int[N];
		comb(0, 0, 0);
		System.out.println(ans);
	}
	static void comb(int idx, int sidx, int sum) {
		if(idx == N) {
			if(sum >= L && sum <= R) {
				int max = 0;
				int min = Integer.MAX_VALUE;
				for (int i = 0; i < sidx; i++) {
					max = Math.max(max, pick[i]);
					min = Math.min(min, pick[i]);
				}
				if((max - min) >= X) {
					ans++;
				}
			}
			return;
		}
		
		pick[sidx] = question[idx];
		comb(idx+1, sidx+1, sum+question[idx]);
		comb(idx+1, sidx, sum);
	}
}