package algorithm_study.day18;

import java.util.Stack;

/**
 * SSAFY 알고리즘 스터디 18일차 - 괄호변환
 * 1시간 풀이, 솔루션
 * 입력 문자열 w 를 u 와 v 로 분리하는 부분을 접근못함
 * -> 다른 부분도 그렇게 맞지는 않은듯
 * 눙물
 * @author YoungHwan
 *
 */
public class PGS_60058 {
	public static void main(String[] args) {
		Solution s = new Solution();
		String p = ")(";
		String answer = s.solution(p);
		System.out.println(answer);
	}
}

class Solution {
	public String solution(String p) {
		String answer = "";
		if (isValid(p)) {
//			System.out.println("읭");
			return p;
		}

//		System.out.println("해치웠나");
		answer = recursive(p);
		return answer;
	}

	// 재귀함수
	private String recursive(String p) {
		String result = "";
		if (p.equals("")) {
			return p;
		}
		int idx = 0;
		int cnt = 0;
		int len = p.length();
        while(idx<len){
            char c = p.charAt(idx++);
            if(c=='(') cnt++;
            else cnt--;
            if(cnt==0) break;
        }
        
        String u = p.substring(0,idx);
        String v = p.substring(idx,len);
        
		// u 가 올바른 문자열이면 v에 대해 재귀 수행
		if (isValid(u)) {
			result = u + recursive(v);
		}
		// u 가 올바른 문자열이 아닌 경우
		else {
			// 빈 문자열에 괄호를 열고 v 에 대한 재귀의 결과를 붙이고 괄호를 닫음
			result = "(" + recursive(v) + ")";
			// u 의 첫번째와 마지막 문자(")를 제거
			// 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙임
			len = u.length();
			for (int i = 1; i < len-1; i++) {
				if (u.charAt(i) == '(') result += ')';
				else result += '(';
			}
		}

		return result;
	}

	public boolean isValid(String str) {
		Stack<Character> st = new Stack<>();

		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '(') {
				st.push('(');
			} else {
				if (st.isEmpty() || st.peek() == ')') {
					return false;
				} else {
					st.pop();
				}
			}
		}
		return true;
	}
}