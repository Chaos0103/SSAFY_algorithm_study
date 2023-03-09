package BJ;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class PGS_42888 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};
		Solution solve = new Solution();
		record = solve.solution(record);
		for(String s : record) {
			System.out.println(s);
		}
	}
}


class IdName{
	String order, id;
	public IdName(String order, String id) {
		this.order = order;
		this.id = id;
	}
}


class Solution {
    public String[] solution(String[] record) {
        Map<String, String> m = new HashMap<>();
        ArrayList<IdName> result = new ArrayList<>();
        for(String s : record) {
        	String[] split = s.split(" ");
        	if(split[0].equals("Enter")) {
        		result.add(new IdName(split[0], split[1])); // 출력 형식과 id ㅇㅇ
        		if(m.containsKey(split[1])) { // 다시 들어오는 경우 
        			m.replace(split[1], split[2]); // 닉네임 변경
        		}else { // 처음 등장
        			m.put(split[1], split[2]);
        		}
        	}else if(split[0].equals("Change")) {
        		m.replace(split[1], split[2]); // 닉네임 변경
        	}else { // 떠~나갑니다.
        		result.add(new IdName(split[0], split[1]));
        	}
        }
        String[] answer = new String[result.size()];
        for(int i = 0; i < result.size(); i++) {
        	String order = result.get(i).order;
        	if(order.equals("Enter")) {
        		answer[i] = m.get(result.get(i).id) + "님이 들어왔습니다.";
        	}else {
        		answer[i] = m.get(result.get(i).id) + "님이 나갔습니다.";
        	}
        
        }
    	
        return answer;
    }
}
