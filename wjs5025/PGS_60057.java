package PG_문자열압축;

/*
 * 오늘자 실습이랑 과제를 하고나니 시간이 1시30분 이어서
 * 그때부터 도전해보았는데 풀이하지 못했습니다....
 * 
 * 조금 생각하면 할 수 있을 것 같은데
 * 잠이 너무와서 집중이 안돼요....나중에 다시 풀이하겠습니다.
 */

public class Solution {
	static String s;

	static int getLength(String word) {
		int wordLen = word.length();
		System.out.println(word);
		s = s.replace(word, "-");
		System.out.println(s);
		
		int maxCnt = Integer.MIN_VALUE;
		int cnt = 0; // -가 연속으로 등장하는 갯수 = cnt

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '-') {
				cnt = 1;

				for (int j = i + 1; j < s.length(); j++) {
					if (s.charAt(j) == '-')
						cnt++;
					else
						break;
				}
				maxCnt = Math.max(maxCnt, cnt);
			}
		}

		for (int i =0; i <maxCnt; i++) {
			s = s.replaceFirst("-","");
		}
		System.out.println(s);
		s = Integer.toString(maxCnt) + word + s;
		
		return s.length() + Integer.toString(maxCnt).length() + wordLen;
	}

	public int solution(String s) {
		this.s = s;
		// 압축해서
		// 가장 짧은 것의 길이를 return 하도록 solution 함수를 완성해주세요.
		int answer = 0;
		int minLength = Integer.MAX_VALUE;

		// substring , start포함, end 직전까지.

		for (int i = 1; i <= s.length() / 2; i++) {
			for (int idx = 0; idx < s.length() - i; idx++) {
				String word = this.s.substring(idx, idx + i);
				getLength(word);
			}
		}

		System.out.println(s);
		return answer;

	}
}