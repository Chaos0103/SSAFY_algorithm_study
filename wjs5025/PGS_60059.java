package PGS_60059;

import java.util.*;
import java.io.*;

/**
 * 문제는 이해했는데
 * 구현방법이 떠오르지 않습니다..
 * 주말에 재도전해보겠습니다.
 */
class Solution {
	static int M, N;
	static int[][] key; // 원본 키
	static int[][] lock; // 자물쇠
	static int[][] tmpKey; // 중간중간 키 변환 결과 저장을 위한

	// 열쇠 우회전
	public int[][] rotateR() {
		int[][] tmp = new int[M][M];

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				tmp[i][j] = tmpKey[M-j-1][i];
			}
		}
		System.out.println(Arrays.deepToString(tmp));
		
		return tmp;
	}

	// 열쇠 좌회전
	public int[][] rotateL() {
		int[][] tmp = new int[M][M];

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				tmp[i][j] = tmpKey[j][M-i-1];
			}
		}
		System.out.println(Arrays.deepToString(tmp));
		
		return tmp;
	}

	// 제출용
	public boolean solution(int[][] key, int[][] lock) {
		boolean answer = true;
		M = key.length;
		N = lock.length;
		
		tmpKey = new int[M][M];
		
		for (int i =0; i<M; i++) {
			tmpKey[i] = key[i].clone();
		}

		return answer;
	}
}

public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		int[][] key = new int[][] { { 0, 0, 0 }, { 1, 0, 0 }, { 0, 1, 1 } };
		int[][] lock = new int[][] { { 1, 1, 1 }, { 1, 1, 0 }, { 1, 0, 1 } };

		System.out.println(s.solution(key, lock));
		s.rotateR();
		s.rotateL();

	}
}