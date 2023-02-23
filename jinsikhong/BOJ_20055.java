package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 
벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다. 만약 이동할 수 없다면 가만히 있는다.
로봇이 이동하기 위해서는 이동하려는 칸에 로봇이 없으며, 그 칸의 내구도가 1 이상 남아 있어야 한다.
올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료한다. 그렇지 않다면 1번으로 돌아간다.
 */

public class BOJ_20055 {
	static int n;
	static int k;
	static int upidx;
	static int downidx;
	static int belt[];
	static boolean robot[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		belt = new int[2 * n];
		for(int i = 0; i < 2 * n; i++) {
			belt[i] = Integer.parseInt(st.nextToken());
		}
		
		robot = new boolean[n];
		upidx = 0;
		downidx = n;
		int cnt = 0;
		while(k > 0) {
			cnt++;
			//벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
			// -> 벨트의 회전을 올리는 위치, 내리는 위치의 변화로 표현
			upidx--;
			if(upidx == -1) {  //범위 벗어나면 스위치
				upidx = 2*n-1;
			}
			downidx--;
			if(downidx == -1) { //범위 벗어나면 스위치 
				downidx = 2*n-1;
			}
			
			// solution code
			for(int i = n-2; i >= 0; i--) { //로봇도 한칸씩 옮겨준다.
				if(robot[i]) { // 로봇이 없으면 그냥 지나침.
	                robot[i] = false;
	                if(i+1 < n-1) // 마지막꺼 제외하고 로봇이 있다면 한칸씩 옮기는 효과  				 
	                    robot[i+1] = true;	
	            }
			}
			
			for(int i = n-2; i >= 0; i--) {
				if(robot[i]) { // 로봇이 있으면
					int nx = upidx + i + 1; // upidx를 더해줘야함(upidx로 벨트가 움직이도록 하니깐)
					if(nx >= 2*n) {
						nx -= 2*n; //범위 벗어나면 그만큼 빼주기
					}
					if(robot[i+1] == false && belt[nx] >= 1) { //다음위치에 로봇이 없고, 내구도가 남아있다면
						robot[i] = false; //현재위치 로봇을 옮기기
						if(i+1 < n-1) {
							robot[i+1] = true; // 다음 위치로 옮기기(로봇 범위 내 있다면!(로봇 배열의 마지막이 아니라면))
						}
						belt[nx]--; // 내구도 감소
						if(belt[nx] == 0) { //내구도가 0보다 작아지면 k 감소 -> 기저조건
							k--;
						}
					}
				}
			}
			if(belt[upidx] != 0 && belt[upidx] > 0) { // 올리는곳에 로봇이 없고, 내구도가 있으면 로봇 올리기
				robot[0] = true;
				belt[upidx]--;
				if(belt[upidx] == 0) {
					k--; // 내구도 다 떨어지면 k감소
				}
			}
			
		}
		System.out.println(cnt);
	}
}
