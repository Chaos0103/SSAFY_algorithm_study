package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 
9 4 
	0 1 2 3 4 5 6 7 8
0 : 4 4 3 3 3 3 2 2 2 
1 : 4 4 3 3 1 1 2 2 3 
2 : 3 3 2 2 1 1 1 1 2 
3 : 1 1 1 1 1 1 1 1 1 
4 : 1 1 1 1 1 1 1 1 1 
5 : 2 2 1 1 1 1 1 1 1 
6 : 2 2 1 1 1 1 1 1 1 
7 : 2 2 2 2 2 2 1 1 1 
8 : 3 3 3 3 2 2 2 2 1 
 */
//종료 시간 : 2 : 59
public class SEA_활주로건설 {
	static int[][] map;
	static int n;
	static int x;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= 10; tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			map = new int[n][n];
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int cnt = 0; // 카운팅
			
			// <--> check
			for(int i = 0; i < n; i++) {
				int downCnt = x; // 경사로 길이 카운팅
				boolean slopeFlag = false; // 경사로인지 flag값
				int compare = map[i][0];
				boolean canload = true;
				boolean[] slopeArr = new boolean[n];
				for(int j = 1; j < n; j++) {
					if(compare == map[i][j] && !slopeFlag) { // 비교해야할 것  과 같으면, 경사로가 아니라면
						continue;
					}else if(compare == map[i][j] && slopeFlag) {// 경사로랑 같은지 확인, 경사로가 진행 중인 경우
						downCnt--;
						
						slopeArr[j] = true;
						
						if(downCnt == 0) { // 경사로가 끝
							slopeFlag = false;
						}
					}else if(compare != map[i][j]) { //비교해야할것과 같지 않다면
						if(Math.abs(compare - map[i][j]) > 1){ //둘의 차이가 1보다 크면 -> 경사로 생성 x break
							canload = false; //활주로 건설 불가
							break;
						}else if(compare > map[i][j]) {//더 작아진 경우  이렇게 경사로 설치
							if(slopeFlag) { // 경사로가 안끝남 지점에서 경사로가 또 시작된다면 -> 활주로 생성 x break
								canload = false;
								break;
							}
							slopeFlag = true;
							compare = map[i][j];
							
							slopeArr[j] = true;
							
							downCnt = x - 1;
						}else if(compare < map[i][j]) {//더 커진 경우 이렇게 경사로 설치
							if(slopeFlag) { // 경사로가 안끝남 지점에서 경사로가 또 시작된다면 -> 활주로 생성 x break
								canload = false;
								break;
							}
							for(int k = 1; k <= x; k++) { // 이 전까지 경사로가 형성 될 수 있는지 확인
								int ny = j - k;
								if(ny < 0) { // 범위에 벗어나면 -> 경사로 생성 x break
									canload = false;
									break;
								}
								if(map[i][ny] != map[i][j] - 1 || slopeArr[ny]) { // 이전에 x개만큼 비교 했을때 경사로 생성 불가하다면 break
									canload = false;
									break;
								}
							}
							if(!canload) {
								break;
							}
							compare = map[i][j];
						}
					}
				}
			
//				System.out.print("i : " + i+ ", canload : " + canload + " , slopeFlag : " + slopeFlag);
				if(canload && !slopeFlag) {
//					System.out.println("i : " + i+ ", canload : " + canload + "slopeFlag : " + slopeFlag);
//					System.out.println("   경사로 : ok" );
					cnt++;
				}else {
//					System.out.println();
				}
			}
			
			for(int i = 0; i < n; i++) {
				int downCnt = x; // 경사로 길이 카운팅
				boolean slopeFlag = false; // 경사로인지 flag값
				int compare = map[0][i];
				boolean canload = true;
				boolean[] slopeArr = new boolean[n];
				for(int j = 1; j < n; j++) {
					if(compare == map[j][i] && !slopeFlag) { // 비교해야할 것  과 같으면, 경사로가 아니라면
						continue;
					}else if(compare == map[j][i] && slopeFlag) {// 경사로랑 같은지 확인, 경사로가 진행 중인 경우
						downCnt--;
						slopeArr[j] = true;
						if(downCnt == 0) { // 경사로가 끝
							slopeFlag = false;
						}
					}else if(compare != map[j][i]) { //비교해야할것과 같지 않다면
						if(Math.abs(compare - map[j][i]) > 1){ //둘의 차이가 1보다 크면 -> 경사로 생성 x break
							canload = false; //활주로 건설 불가
							break;
						}else if(compare > map[j][i]) {//더 작아진 경우 이렇게 경사로 설치
							if(slopeFlag) { // 경사로가 안끝남 지점에서 경사로가 또 시작된다면 -> 활주로 생성 x break
								canload = false;
								break;
							}
							slopeArr[j] = true;
							slopeFlag = true;
							compare = map[j][i];
							downCnt = x - 1;
						}else if(compare < map[j][i]) {//더 커진 경우 이렇게 경사로 설치
							if(slopeFlag) { // 경사로가 안끝남 지점에서 경사로가 또 시작된다면 -> 활주로 생성 x break
								canload = false;
								break;
							}
							for(int k = 1; k <= x; k++) { // 이 전까지 경사로가 형성 될 수 있는지 확인
								int nx = j - k;
								if(nx < 0) { // 범위에 벗어나면 -> 경사로 생성 x break
									canload = false;
									break;
								}
								if(map[nx][i] != map[j][i] - 1 || slopeArr[nx]) { // 이전에 x개만큼 비교 했을때 경사로 생성 불가하다면 break
									canload = false;
									break;
								}
							}
							if(!canload) {
								break;
							}
							compare = map[j][i];
						}
					}
				}
//				System.out.print("i : " + i+ ", canload : " + canload + " , slopeFlag : " + slopeFlag );
				if(canload && !slopeFlag) {
//					System.out.println("   경사로 : ok" );
//					System.out.println("i : " + i+ ", canload : " + canload + "slopeFlag : " + slopeFlag);
					cnt++;
				}else {
//					System.out.println();
				}
			}
			
			
			
			
			System.out.println("#" + tc + " " + cnt);
			
		}
	}
}
