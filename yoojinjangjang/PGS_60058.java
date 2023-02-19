package com.yoojin.programmers;

import java.util.Scanner;

public class PGS_60058 {
	
	
    public static String solution(String p) {
        if(p.length() == 0) return "";
        
        int cnt = 0;
        StringBuilder u = new StringBuilder();
        int i;
        for(i =0;i<p.length();i++) {
        	if(p.charAt(i) == '(') cnt += 1;
        	else cnt-=1;
        	u.append(p.charAt(i));
        	if(cnt==0) break;
        }

        StringBuilder v = new StringBuilder();
        for(int j = i+1;j<p.length();j++) {
        	v.append(p.charAt(j));
        }
        
        if(u.toString().charAt(0) == '(') {
        	// 올바른 문자열
        	u.append(solution(v.toString()));
        	return u.toString();
        }
        
        // 올바른 문자열이 아닌경우
        StringBuilder ans = new StringBuilder();
        ans.append('(');
        ans.append(solution(v.toString()));
        ans.append(')');
        for(int k=1;k<u.toString().length()-1;k++) {
        	if (u.toString().charAt(k) == '(') ans.append(')');
        	else ans.append('(');
        }
        
        return ans.toString();
    }
    
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	System.out.println(solution(sc.next()));
	}
}
