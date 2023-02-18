package com.yoojin.programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class Archi implements Comparable<Archi>{
	int x;
	int y;
	int type; // 기둥 - 0, 보 -1 
	public Archi(int x, int y, int type) {
		super();
		this.x = x;
		this.y = y;
		this.type = type;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean ret = false;
		if(obj != null && obj instanceof Archi) {
			Archi objArchi = (Archi) obj;
			if(objArchi.x == this.x && objArchi.y == this.y && objArchi.type == this.type) 
				ret = true; // 세개가 모두 같은 경우 return true
		}
		return ret; // 하나라도 다른 경우 return false
		
	}

	@Override
	public int compareTo(Archi o) {
		if(this.x != o.x) { 
			if(this.x < o.x) return -1; 
			else return 1;
		}
		
		if(this.y < o.y)return -1;
		else return 1;
	}

	@Override
	public String toString() {
		return "Archi [x=" + x + ", y=" + y + ", type=" + type + "]";
	}
	
	
	
}

public class PGS_60061 {
	public static ArrayList<Archi> includeArchitects = new ArrayList<>(); // 포함하고 있는 기둥혹은보
	
    public static int[][] solution(int n, int[][] build_frame) {
        for(int i =0;i<build_frame.length;i++) {
        	// 들어오는 입력을 하나씩 보면서 
        	Archi checkArchi = new Archi(build_frame[i][0], build_frame[i][1], build_frame[i][2]);
        	if(build_frame[i][3] == 1) { 
        		// 삽입 인 경우
        		// 1. 삽입 가능 여부 판단
        		if(checkInsert(checkArchi)) {
        			// 2. true 인 경우 리스트에 append
        			includeArchitects.add(checkArchi);
        		}
        	} else {
        		// 삭제 인 경우
        		
        		// 1. 삭제 가능 여부 판단
        		if(checkDelete(checkArchi)) {
        			// 2. true 인 경우 리스트에서 remove
        			includeArchitects.remove(includeArchitects.indexOf(checkArchi));
        		}
        	}
        }
        
        Collections.sort(includeArchitects);
        // arraylist를 answer 배열에 담기
        int[][] answer = new int[includeArchitects.size()][3];
        for(int i=0;i<includeArchitects.size();i++) {
        	Archi cur = includeArchitects.get(i);
        	answer[i] = new int[]{cur.x,cur.y,cur.type};
        }
        return answer;
    }
    
    
    public static boolean checkInsert(Archi checkArchi) {
    	int x = checkArchi.x;
    	int y = checkArchi.y;
    	int type = checkArchi.type;
    	if(type == 0) {
    		// 기둥 인 경우
    		if (y==0) return true; // 바닥인 경우
    		if (includeArchitects.contains(new Archi(x-1, y, 1)) || includeArchitects.contains(new Archi(x, y, 1))) {
    			return true; // 기둥 밑에 보가 존재 하는 경우
    		}
    		if (includeArchitects.contains(new Archi(x, y-1, 0))) return true; // 아래 기둥이 존재하는 경우
    	} else {
    		// 보인 경우
    		if(includeArchitects.contains(new Archi(x, y-1, 0)) || includeArchitects.contains(new Archi(x+1, y-1, 0))) return true; // 아래 기둥이 존재하는 경우
    		// 양 쪽 보가 존재하는 경우
    		if(includeArchitects.contains(new Archi(x-1, y, 1)) && includeArchitects.contains(new Archi(x+1, y, 1))) return true;
    		
    	}
    	return false;
    }
    
    
    public static boolean checkDelete(Archi checkArchi) {
    	int x = checkArchi.x;
    	int y = checkArchi.y;
    	int type = checkArchi.type;
    	if(type == 0) {
    		// 기둥인 경우
    		// 1. 위에 보가 존재 시
    		if(includeArchitects.contains(new Archi(x, y+1, 1))) {
    			// 위에 보가 존재하지만 해당 보의 양쪽보가 존재하는 경우 return true; (삭제할수있음)
    			if(includeArchitects.contains(new Archi(x-1, y+1,1)) && includeArchitects.contains(new Archi(x+1, y+1, 1))) {
    				return true;
    			}
    			return false;
    		}
    		// 2. 위에 기둥이 존재시
    		if(includeArchitects.contains(new Archi(x, y+1, 0))) return false;

    	} else {
    		// 보인 경우
    		// 1. 위에 기둥이 존재시 
    		if (includeArchitects.contains(new Archi(x, y, 0))) return false; 
    		// 2. 양쪽에 보가 존재시 
    		if(includeArchitects.contains(new Archi(x-1, y, 1)) && includeArchitects.contains(new Archi(x+1, y, 1))) return false;
    	}
    	
    	return true;
    }
    
    public static void main(String[] args) {
		/*
		 * 5, [[1, 0, 0, 1], [1, 1, 1, 1], [2, 1, 0, 1], [2, 2, 1, 1], 
		 * [5, 0, 0, 1], [5, 1, 0, 1], [4, 2, 1, 1], [3, 2, 1, 1]]
		 * 
		 * 
		 * [[0, 0, 0, 1], [2, 0, 0, 1], [4, 0, 0, 1], [0, 1, 1, 1], [1, 1, 1, 1], [2, 1, 1, 1],
		 *  [3, 1, 1, 1], [2, 0, 0, 0], [1, 1, 1, 0], [2, 2, 0, 1]]
		 */
    	int n = 5;
    	int[][] build_frame2 = {{1,0,0,1},{1,1,1,1},{2,1,0,1},{2,2,1,1},{5,0,0,1},{5,1,0,1},{4,2,1,1},{3,2,1,1}};
    	int[][] build_frame = {{0,0,0,1},{2,0,0,1},{4,0,0,1},{0,1,1,1},{1,1,1,1},{2,1,1,1},{3,1,1,1},{2,0,0,0},{1,1,1,0},{2,2,0,1}};
    	
    	int[][] answer = solution(n, build_frame);
    	for(int[] a:answer) {
    		System.out.println(Arrays.toString(a));
    	}
	}
}
