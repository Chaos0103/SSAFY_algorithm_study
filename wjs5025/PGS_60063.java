package PGS_60063;

import java.io.*;
import java.util.*;

/*
 * 최초 아이디어를 떠올렸으나,
 * 문제를 3번째 읽었을때 잘못된걸 깨달음..
 * 
 */
class Pos {
	int r;
	int c;
	int time;

	@Override
	public String toString() {
		return "Pos [r=" + r + ", c=" + c + ", time=" + time + "]";
	}

	public Pos(int r, int c, int time) {
		super();
		this.r = r;
		this.c = c;
		this.time = time;
	}
}

class Solution {
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int mode = 0; // 0은 가로모드 ,1은 세로모드

	// 시계 회전 가능한지
	static boolean canRotateRight() {
		return false;
	}

	// 반시계 회전 가능한지
	static boolean canRotateLeft() {
		return false;
	}

	static void bfs(Pos start) {
		Deque<Pos> q = new ArrayDeque<>();
		q.offer(start);
		visited[start.r][start.c] = true;

		while (!q.isEmpty()) {
			Pos current = q.poll();

			// 만약 방문한 노드라면 
			if (visited[current.r][current.c])
				continue;

			if (canRotateRight()) {
				
			} else
				continue;

			if (canRotateLeft()) {

			} else
				continue;
		}
	}

	public int solution(int[][] board) {
		int result = 0;
		map = board;
		N = board.length;
		visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				bfs(new Pos(i, j, 0));
			}
		}

		print();

		return result;
	}

	// 디버깅용 맵
	static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}

public class Main {
	public static void main(String[] args) {
		Solution solution = new Solution();
		// 답 7
		int[][] board = { { 0, 0, 0, 1, 1 }, { 0, 0, 0, 1, 0 }, { 0, 1, 0, 1, 1 }, { 1, 1, 0, 0, 1 },
				{ 0, 0, 0, 0, 0 } };
		solution.solution(board);
	}
}
