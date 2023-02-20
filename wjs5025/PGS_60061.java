package PGS_60061;

import java.io.*;
import java.util.*;

/*
 * 오늘 재도전 해보겠습니다.
 * 
 */
class Node{
    // 기둥인지 보인지 (근데 사실 시작점 끝점만 가지고도 이건 알 수 있을 듯?)
    // 시작점
    // 끝점
}
class Solution {
	public int[][] solution(int n, int[][] build_frame) {
		int[][] answer = {};
		
		return answer;
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Solution s = new Solution();
		int[][] build_frame = { { 1, 0, 0, 1 }, { 1, 1, 1, 1 }, { 2, 1, 0, 1 }, { 2, 2, 1, 1 }, { 5, 0, 0, 1 },
				{ 5, 1, 0, 1 }, { 4, 2, 1, 1 }, { 3, 2, 1, 1 } };
		int n = 5;
		
		s.solution(n, build_frame);
	}
}
