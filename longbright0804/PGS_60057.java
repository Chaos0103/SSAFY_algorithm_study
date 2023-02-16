
/**
 * SSAFY 알고리즘 스터디 15일차 - 문자열 압축
 * 1시간 30분
 * @author YoungHwan
 *
 */
public class PGS_60057 {
	public static void main(String[] args) {
		String s = "ababcdcdababcdcd";
		System.out.println(solution(s));
	}

	private static int solution(String s) {
		int answer = Integer.MAX_VALUE;
		int cnt = 0;
		String temp = "";
		String key = "";
		StringBuilder sb;

		for (int i = 1; i <= s.length(); i++) {
			key = s.substring(0, i);	// 비교할 문자열 분리
			cnt = 1;
			sb = new StringBuilder();
			// 비교 시작
			for (int j = i; j <= s.length(); j += i) {
				// 남은 문자열이 현재 key 보다 짧으면 문자열의 끝까지만 자르기
				if (i + j > s.length()) {
					temp = s.substring(j);
				} else {
					temp = s.substring(j, i + j);
				}
				// 같으면 개수 증가
				if (temp.equals(key)) {
					cnt++;
				} 
				// 다르면 결과 문자열에 추가
				else {
					// 1개 이상이면 개수 포함
					if (cnt > 1) {
						sb.append(cnt).append(key);
					} else {
						sb.append(key);
					}
					// 탐색 문자열 변경
					key = temp;
					cnt = 1;
				}
			}
			// 추가되지 않은 마지막 문자열 추가
			sb.append(key);
			// 최솟값 갱신
			if (sb.length() < answer) {
				answer = sb.toString().length();
			}
		}
		return answer;
	}
}
