package BOJ__16;

import java.util.*;
import java.io.*;

public class PGS_60061 {
	public boolean pillar;
	public boolean bar;
	
    public int[][] solution(int n, int[][] build_frame) {
        pillar = new boolean[n + 1][n + 1];
        bar = new boolean[n + 1][n + 1];
        
        int count = 0;
        for(int i = 0; i < build_frame.length; i++) {
            int x = build_frame[i][0];
            int y = build_frame[i][1];
            int type = build_frame[i][2];
            int action = build_frame[i][3];
            
            if(type == 0) { //기둥을
                if(action == 1) { //설치한다
                    if(checkPillar(x, y)) {
                        pillar[x][y] = true;
                        count++;
                    }  
                } else { //삭제한다
                    pillar[x][y] = false;
                    if(canDelete(n) == false) pillar[x][y] = true;
                    else count--;
                }
            } else { //보를
                if(action == 1) {
                    if(checkBar(x, y)) { //설치한다
                        bar[x][y] = true;
                        count++;
                    } 
                } else { //삭제한다
                    bar[x][y] = false;
                    if(canDelete(n) == false) bar[x][y] = true;
                    else count--;
                }
            }
        }
    	
        int[][] answer = {};
        return answer;
    }
}
