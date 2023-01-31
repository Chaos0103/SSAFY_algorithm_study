import java.io.*;
import java.util.*;

public class Main {
	static int[][] teamCombi;
	static int N;
	static int[] start; // start 팀 구성원
	static int[] link; // link 팀 구성원
	static int min = Integer.MAX_VALUE;
	static boolean[] used;
	static boolean[] usedForTeam;
	static int[] 팀순열;
	static int sum = 0;
	
	// start 또는 link 각 팀에서 나올 수 있는 능력치의 합
	static int getSum(int idx, int[] team) {
		int sum = 0;
		if (idx == 2) {
			sum += teamCombi[팀순열[0]][팀순열[1]];
			return sum;
		}
		
		for (int i = 0; i < N/2; i++) {
			if(usedForTeam[i]) continue;
			팀순열[idx] = team[i];
			usedForTeam[i] = true;
			sum += getSum(idx+1, team);
			usedForTeam[i] = false;			
		}
		return sum;
	}
	
	// 링크팀 구해서 멤버에 집어넣기 (스타트팀이 아닌 인덱스들)
	static void getLinkTeam() {
		link = new int[N/2];
		int size = 0;
		
		for (int i = 0; i < N; i++) {
			if (!used[i]) {
				link[size++] = i;
			}
		}
	}
	
	static void getTeam(int idx) {
		// 순열조합이 만들어지면, 차를 구해서 최소값인지 비교
		if(idx == N/2) {
			getLinkTeam();
			
			usedForTeam = new boolean[N/2];
			팀순열 = new int[2];
			
			int cha = Math.abs(getSum(0,start) - getSum(0,link));
			if (min > cha) {
				min = cha;
			}
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (used[i]) continue;
			start[idx] = i;
			used[i] = true;
			getTeam(idx+1);
			used[i] = false;
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		teamCombi = new int[N][N];
		
		start = new int[N/2];
		used = new boolean[N];
		
		// 능력치 조합표 input 받기 
		for (int i = 0; i < N ; i ++) {
			String[] line = br.readLine().split(" ");
			for (int j = 0; j <N; j++) {
				teamCombi[i][j] = Integer.parseInt(line[j]);
			}
		}
		getTeam(0);
		System.out.println(min);
	}
}