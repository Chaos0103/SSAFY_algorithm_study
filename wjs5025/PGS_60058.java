import java.io.*;
import java.util.*;

class Solution {

	// 균형잡힌 괄호인가
	public boolean isBalanced(String str) {
		int open = 0;
		int close = 0;

		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '(')
				open++;
			else
				close++;
		}
		if (open == close)
			return true;
		return false;
	}

	// 올바른 괄호인가
	public boolean isCorrect(String str) {
		Stack<Character> s = new Stack<>();

		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '(') {
				s.push(str.charAt(i));
			} else {
				if (s.size() > 0)
					s.pop();
				else
					return false;
			}
		}
		if (s.size() == 0)
			return true;
		return false;
	}

	// 균형 -> 올바른으로 만드는 재귀함수
	public String change(String w) {
		// 1. 입력이 빈 문자열이면 빈 문자열 반환
		if (w.equals(""))
			return "";
		if (isCorrect(w)) return w;
		
		String u = "";
		String v = "";

		// w를 두 균형잡힌 괄호 문자열 u,v로 분리
		for (int i = 0; i <= w.length(); i++) {
			u = w.substring(0, i);
			v = w.substring(i, w.length());
			if (!u.equals("") && isBalanced(u)) {
				break;
			}
				
		}

		// 3. 문자열 u가 "올바른 괄호 문자열" 이라면 문자열 v에 대해 1단계부터 다시 수행합니다.
		// 3-1. 수행한 결과 문자열을 u에 이어 붙인 후 반환합니다.
		if (isCorrect(u))
			return u + change(v);
		
//		// 4. 문자열 u가 "올바른 괄호 문자열"이 아니라면 아래 과정을 수행합니다
		String tmp = "(" + change(v) + ")";
		// 4-1. 빈 문자열에 첫 번째 문자로 '('를 붙입니다.
		// 4-2. 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙입니다.
		// 4-3. ')'를 다시 붙입니다.

		u = u.substring(1, u.length() - 1);
		// 4-4. u의 첫 번째와 마지막 문자를 제거하고,
		// 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다.
		for (int i = 0; i < u.length(); i++) {
			if (u.charAt(i) == '(')
				tmp += ")";
			else if (u.charAt(i) == ')')
				tmp += "(";
		}
		return tmp;
	}

	public String solution(String p) {
		String answer = "";

		// 처음부터 올바른 괄호면 리턴
		if (isCorrect(p))
			return p;

		if (isBalanced(p))
			answer = change(p);

		return answer;
	}
}

public class PGS_60058 {
	public static void main(String[] args) {
		Solution s = new Solution();
		String p1 = "(()())()";
		String p2 = ")(";
		String p3 = "()))((()";
		

		System.out.println(s.solution(p3));
	}

}
