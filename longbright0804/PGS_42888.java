package algorithm_study.day31;

import java.util.HashMap;

/**
 * <pre>
 * SSAFY 알고리즘 스터디 31일차 - 오픈채팅방
 * 
 * 풀이
 * 1. HashMap 에 유저 id 와 닉네임 저장(Enter, Change 시)
 * 2. Leave 와 Enter 에 대해 HashMap 에서 닉네임을 구해옴
 * 3. 결과에 추가 
 * </pre>
 * 
 * @author SSAFY
 *
 */
class Solution {
	HashMap<String, String> map = new HashMap<>();
	String[] answer = {};
	
	public String[] solution(String[] record) {
		init(record);
		getAnswer(record);
		return answer;
	}

	private void init(String[] record) {
		int size = 0;
		int N = record.length;
		for (int i = 0; i < N; i++) {
			String[] info = record[i].split(" ");
			// Leave
			if (info[0].equals("Leave")) {
				size++;
			} 
			// Change, Enter
			else {
				map.put(info[1], info[2]);
				if (info[0].equals("Enter")) {
					size++;
				}
			}
		}
		answer = new String[size];
	}

	private void getAnswer(String[] record) {
		int N = record.length;
		int index = 0;
		for (int i = 0; i < N; i++) {
			String[] info = record[i].split(" ");
			String nickName = map.get(info[1]);
			// 입장 퇴장 문장처리
			if (info[0].equals("Leave")) {
				answer[index++] = String.format("%s님이 나갔습니다.", nickName);
			} else if (info[0].equals("Enter")) {
				answer[index++] = String.format("%s님이 들어왔습니다.", nickName);
			}
		}
	}
}
