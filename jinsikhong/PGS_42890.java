package BJ;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import sun.swing.UIAction;

public class PGS_42890 {
	public static void main(String[] args) {
		
		String[][] relation = { {"a","1","aaa","c","ng"},
				{"a","1","bbb","e","g"},
				{"c","1","aaa","d","ng"},
				{"d","2","bbb","d","ng"}};
//		String[][] relation = {{"100","ryan","music","2"},{"200","apeach","math","2"},{"300","tube","computer","3"},{"400","con","computer","4"},{"500","muzi","music","3"},{"600","apeach","music","2"}};
		Solution s = new Solution();
		System.out.println(s.solution(relation));
	}
	
	
	
	static class Solution {
		int[] result;
		ArrayList<String> unique = new ArrayList<>();
		
	    public int solution(String[][] relation) {
	    	int n = relation[0].length;
	    	
	    	for(int i = 0; i < n; i++) {
	    		result = new int[i+1];
	    		combi(0,0,n,i+1, relation);
	    	}
	    	
	    	int answer = unique.size();
	    	for(int i = unique.size()-1; i >= 0; i--) { // 문자 전체 검사
	    		String s = unique.get(i); // 검사할 문자
	    		for(int j = 0; j < i; j++) { // 검사할 문자의 아래부터 검사 시작
	    			char[] temp = unique.get(j).toCharArray();
//	    			boolean flag = true;
	    			int cnt = 0;
	    			//전부 다 포함 하고 있으면 안됨.
	    			for(int k = 0; k < temp.length; k++) { // 체크
	    				if(s.contains(temp[k]+"")){
	    					cnt++;
	    				}
	    			}
	    			if(cnt == temp.length) { // 전부 다 포함 하고 있으면
	    				answer--;
	    				break;
	    			}
	    			
	    			if(s.contains(unique.get(j))) {
	    				answer--;
	    				break;
	    			}
	    		}
	    	}
//	    	System.out.println(answer);
	    	return answer;
	    }
	    
	    
	   
	   void combi(int start, int cnt, int n,int k, String[][] relation) {
		   if(cnt == k) {
			   findUnique(relation);
			   return;
		   }
		   for(int i = start; i < n; i++) {
			   result[cnt] = i;
			   combi(i + 1, cnt + 1, n, k, relation); 
	    	}
	   }
	   
	   
	   void findUnique(String[][] relation) {
		   Set<String> set = new HashSet<>();
		   for(int i = 0; i < relation.length; i++) {
	    		String s = "";
	    		for(int j = 0; j < result.length; j++) {
	    			s += relation[i][result[j]];
	    		}
	    		set.add(s);
		   }
		   if(set.size() == relation.length) {
			   	String s = "";
	    		for(int j = 0; j < result.length; j++) {
	    			s += result[j];
	    		}
	   			unique.add(s);
	   		}
		   
	   }
	
	}
	
	
	
}
