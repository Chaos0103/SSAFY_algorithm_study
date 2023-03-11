package com.yoojin.programmers;

import java.awt.Point;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PGS_42888 {

    public static String[] solution(String[] record) {
        
    	String[] answer = {};
    	Map<String, String> nickname = new HashMap<String, String>();
    	for(int n = 0;n<record.length;n++) {
    		String[] inst = record[n].split(" ");
    		if(!inst[0].equals("Leave")) {
    			// 떠나기가 아니면 갱신
    			nickname.put(inst[1], inst[2]);
    		}
    	}
    	
    	ArrayList<String> ans = new ArrayList<>(); 
    	for(int n = 0;n<record.length;n++) {
    		String[] inst = record[n].split(" ");
    		if(inst[0].equals("Enter")) {
    			ans.add(nickname.get(inst[1]) + "님이 들어왔습니다.");
    		}else if(inst[0].equals("Leave")) {
    			ans.add(nickname.get(inst[1]) + "님이 나갔습니다.");
    		}
    	}
    	
    	answer = new String[ans.size()];
    	for(int i =0;i<ans.size();i++) {
    		answer[i]= ans.get(i); 
    	}
        return answer;
    }
    
    public static void main(String[] args) {
    	String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};
    	
    	solution(record);
	}
    
    
}