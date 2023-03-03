package BOJ_20057;

import java.io.*;
import java.util.*;

public class Main {
	static int[][] map;
	static int N;
	static int startR;
	static int startC;
	static int[] dx = { 0, 1, 0, -1 }; // 좌하우상
	static int[] dy = { -1, 0, 1, 0 };

	public static void move(int length, int moveIdx) {
		int r = startR;
		int c = startC;
		System.out.println("moveIdx" + moveIdx);
		System.out.println(moveIdx % 4);

		// 좌
		if (moveIdx == 0) {
			for (int i = startC; i >= startC - length; i--) {
				map[startR][i] = 1;
				c = i;
			}
			startC = c;
			System.out.println("이동 후 start R" + startR);
			System.out.println("이동 후 start C" + startC);
		}
		// 하
		else if (moveIdx == 1) {
			for (int i = startR; i <= startR + length; i++) {
				map[i][startC] = 1;
				r = i;
			}
			startR = r;
			System.out.println("이동 후 start R" + startR);
			System.out.println("이동 후 start C" + startC);
		}
		// 우
		else if (moveIdx == 2) {
			for (int i = startC; i <= startC + length; i++) {
				map[startR][i] = 1;
				c = i;
			}
			startC = c;
			System.out.println("이동 후 start R" + startR);
			System.out.println("이동 후 start C" + startC);
		}
		// 상
		else if (moveIdx == 3) {
			for (int i = startR; i >= startR - length; i--) {
				map[i][startC] = 1;
				r = i;
			}
			startR = r;
			System.out.println("이동 후 start R" + startR);
			System.out.println("이동 후 start C" + startC);
		}
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		map = new int[N][N];
		startR = N / 2;
		startC = N / 2;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		int length = 1;
		int moveIdx = 0;
		for (int i = 1; i <= N - 1; i++) {
			System.out.println("length" + length);
			for (int j = 0; j < 2; j++) {
				move(i, moveIdx++);
				System.out.println("l  "+length);
				print();
			}
			length++;
		}

	}

	static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
}
