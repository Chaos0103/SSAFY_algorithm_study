package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_3020 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		int[] crashDown = new int[H+1]; // 높이별 충돌 저장 : 종유석
		int[] crashUp = new int[H+1]; // 높이 별 충돌 저장 : 석순
		int[] crash = new int[H+1];
		for(int i = 1; i <= N; i++) {
			int height = Integer.parseInt(br.readLine());
			if(i % 2 == 1) {//석순(아래서 위로)
				crashUp[height]++;
			}else { // 종유석
				crashDown[H-height+1]++;
			}
		}
		
		
		for(int i = 1; i < H; i++) {
			int Upcur = H - i;
			crashUp[Upcur] += crashUp[Upcur+1];
			crash[Upcur] += crashUp[Upcur];
			int Downcur = i+1;
			crashDown[Downcur] += crashDown[Downcur-1];
			crash[Downcur] += crashDown[Downcur];
		}
		

		
		
		int cnt = 0;
		int min = Integer.MAX_VALUE;
		for(int i = 1; i <= H; i++) {
			if(min > crash[i]) { // 갱신
				cnt = 1;
				min = crash[i];
			} else if(min == crash[i]) { //같은 경우
				cnt++;
			}
		}
		System.out.println(min + " " + cnt);
		
		
		

	}
}
