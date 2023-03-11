package com.yoojin.programmers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PGS_17677 {
	
    public static int solution(String str1, String str2) {
        int answer = 0;
        Map<String, Integer> aMap = new HashMap<>();
        Map<String, Integer> bMap = new HashMap<>();
        
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        
        for(int i =0;i<str1.length()-1;i++) {
        	char one = str1.charAt(i);
        	char two = str1.charAt(i+1);
        	if((int)one < 97 || (int)one>122 || (int)two < 97 || (int)two >122) continue;
        	String newStr = "" + one + two ;
        	aMap.put(newStr, aMap.getOrDefault(newStr, 0)+1);
        }
        
        for(int i =0;i<str2.length()-1;i++) {
        	char one = str2.charAt(i);
        	char two = str2.charAt(i+1);
        	if((int)one < 97 || (int)one>122 || (int)two < 97 || (int)two >122) continue;
        	String newStr = "" + one + two;
        	bMap.put(newStr, bMap.getOrDefault(newStr, 0)+1);
        }
        
        if(aMap.size() ==0 && bMap.size() == 0) return 65536;
        int unionCnt = 0;
        int interCnt = 0;
        for(String key: aMap.keySet()) {
        	if(!bMap.containsKey(key)) {
        		unionCnt += aMap.get(key);
        	} else {
        		interCnt += Math.min(aMap.get(key), bMap.get(key));
        		unionCnt += Math.max(aMap.get(key), bMap.get(key));
        		bMap.remove(key);
        	}
        }
        
        for(String key:bMap.keySet()) {
        	unionCnt += bMap.get(key);
        }
        
        
        answer = (int)(((double)interCnt/unionCnt) * 65536);
        
        return answer;
    }
    public static void main(String[] args) {
    	System.out.println(solution("E=M*C^2","e=m*c^2"));
	}
}
