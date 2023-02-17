package bj;

public class PGS_60061 {
	public static void main(String[] args) {
		int[][] frame = {{1, 0, 0, 1}, {1, 1, 1, 1}, {2, 1, 0, 1}, {2, 2, 1, 1}, {5, 0, 0, 1}, {5, 1, 0, 1}, {4, 2, 1, 1}, {3, 2, 1, 1}};
		int n = 5;
		
		int[][] result;
		Solution s = new Solution();
		result = s.solution(n, frame);
		for(int[] arr : result) {
			for(int x : arr) {
				System.out.print(x + " ");
			}
			System.out.println();
		}
	}
	
}
	
	
class Solution {
		boolean[][] pillar;
		boolean[][] bar;
		
		// 정답에서 1 은 기둥  / 2는 보 -> 출력할때 형식 change
		public int[][] solution(int n, int[][] build_frame) {
	        pillar = new boolean[n + 1][n + 1];
	        bar = new boolean[n + 1][n + 1];
	        int cnt = 0;
	        for(int i = 0; i < build_frame.length; i++) {
	        	int x = build_frame[i][0]; // x 좌표 ㅡ 
	        	int y = build_frame[i][1]; // y 좌표 |
	        	int a = build_frame[i][2]; //0은 기둥 1 은 보
	        	int b = build_frame[i][3]; // 0은 삭제, 1은 설치
	        	
	        	if(a == 0) { // 기둥 일떄
	        		if(b == 0) { // 기둥 삭제
	        			pillar[x][y] = false;
	        			if(delete(n)) { // 삭제 성공
	        				cnt--;
	        			}else {
	        				pillar[x][y] = true;
	        			}
	        		}else { // 기둥 설치
	        			if(checkpillar(x, y) == true) {
	        				pillar[x][y] = true;
	        				cnt++;
	        			}
	    			}
	
	        	}else { // 보 일때
	        		if(b == 0) { // 보 삭제
	        			bar[x][y] = false;
	        			if(delete(n)) { // 삭제 성공
	        				cnt--;
	        			}else {
	        				bar[x][y] = true;
	        			}
	        		}else { // 보 설치
	        			if(checkbar(x, y)== true) {
	        				bar[x][y] = true;
	        				cnt++;
	        			}
	        		}
	        	}
	        }
	        int[][] answer = new int[cnt][3];
	        int idx = 0;
	        for(int i = 0; i <= n; i++) {
	        	for(int j = 0; j <= n; j++) {
	        		if(pillar[i][j] == true) {
	        			answer[idx][0] = i;
	        			answer[idx][1] = j;
	        			answer[idx][2] = 0;
	        			idx++;
	        		}if(bar[i][j] == true) {
	        			answer[idx][0] = i;
	        			answer[idx][1] = j;
	        			answer[idx][2] = 1;
	        			idx++;
	        		}
	        	}
	        }
	        return answer;
	    }
		
		public boolean delete(int n) {
			for(int i = 0; i <= n; i++) {
				for(int j = 0; j <= n; j++) {
					if(pillar[i][j] && !checkpillar(i, j)) {
						return false;
					}
					if(bar[i][j] && !checkbar(i, j)) {
						return false;
					}
				}
			}
			return true;
		}
		
		public boolean checkpillar(int x, int y) {
			if(y == 0) {
				return true;
			}else if(y > 0 && pillar[x][y-1]) { // 아래칸에 기둥이 있으면
				return true;
			}else if(x > 0 && bar[x-1][y] || bar[x][y]) { // 왼쪽 칸에 보가 있으면 ㅢ(형태로 연결)
				return true;
			}
			return false;
		}
		
		
		public boolean checkbar(int x, int y) {
			if(y > 0 && pillar[x][y-1] || pillar[x+1][y-1]) { // 한쪽에라도 기둥이 있고 0보다 크면 / 0일경우 index out
				return true;									
			}else if(x > 0 && bar[x-1][y] && bar[x+1][y]) { // 양쪽에 다 보가 있어야함 // x의 최대 범위는 검사 x -> 문제 조건 자체가 범위에 벗어나도록 입력이 안주어지기때문(bar는 무조건 오른쪽 방향으로 설치 : 최대 범위가 -> n-1)
				return true;
			}else {
				return false;
			}
			
				
			
		}
}
	
	

