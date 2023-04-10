package BOJ_11054;

import java.io.*;
import java.util.*;

/**
 * https://chanhuiseok.github.io/posts/algo-49/ https://squareyun.tistory.com/27
 * 
 * LIS (Longes Increasing Subsequence)
 * 추후 재학습하겠습니다.
 * 
 * 
 * @author jeon
 *
 */

/*
접근방법은 비슷하게 생각해봤는데, 구현이 안되어 솔루션을 참고.

 * 한쪽(왼 or 오) 에서 한쪽(왼or오)으로 가면서 각 인덱스에서
 * 가장 긴 수열의 한쪽 길이를 저장.
 * 반대쪽도 똑같이 수행 후,
 * 두 부분을 합쳐준다. (+) 
 * 겹치는 부분인 가운데 때문에 -1을 해준 후 결과 출력
 * */
public class Main {
	static int N;
	static int[] sequence;
	static int max = 0;
	static int[] dpLR,dpRL;

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();

		dpLR = new int[N + 1];
		dpRL = new int[N + 1];
		sequence = new int[N+1];
		
		for (int i = 1; i <= N; i++) {
			sequence[i] = sc.nextInt();
		}

		// 왼쪽 -> 오른쪽 LIS
		for (int i = 1; i <= N; i++) {
			dpLR[i] = 1;
			for (int j = 1; j < i; j++) {
				if (sequence[i] > sequence[j]) {
					dpLR[i] = Math.max(dpLR[j] + 1, dpLR[i]);
				}
			}
		}

		// 오른쪽 -> 왼쪽 LIS
		for (int i = N; i > 0; i--) {
			dpRL[i] = 1;
			for (int j = N; j > i; j--) {
				if (sequence[i] > sequence[j]) {
					dpRL[i] = Math.max(dpRL[j] + 1, dpRL[i]);
				}
			}
		}
		
		// 최대값 찾기
		for (int i  =1 ; i <=N; i++) {
			max = Math.max(max,  dpLR[i] + dpRL[i]);
		}
		
		System.out.println(max - 1);
	}
}
