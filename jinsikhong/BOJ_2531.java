package bj;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * 문제 : 회전 초밥
 * 풀이시간 : 30분
 * 풀이 방법 : map을 이용한 슬라이딩 윈도우 
 * 
 */


public class BOJ_2531 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(); //회전초밥 접시 수 
		int d = sc.nextInt(); //초밥 가짓 수 
		int k = sc.nextInt(); //연속해서 먹는 초밥 접시 수 
		int c = sc.nextInt(); //쿠폰 번호
		
		int[] arr = new int[n]; //초밥을 배열에다가.
		for(int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		Map<Integer, Integer> sushi = new HashMap<>();
		
		for(int i = 0; i < k; i ++) { //초기값 설정
			sushi.put(arr[i], sushi.getOrDefault(arr[i], 0) + 1);
		} 
		int idx1 = 0; //슬라이딩 윈도우 초기 idx
		int idx2 = k;
		
		//초기값 설정
		sushi.put(c, sushi.getOrDefault(c, 0) + 1); //추가 초밥은 map에 넣고 while 해도 무방
		int max = sushi.size(); // 초기 max 값 설정

		while(idx1 < n) {
			//슬라이딩 윈도우 move
			//첫 초밥 삭제
			sushi.put(arr[idx1], sushi.get(arr[idx1])-1);
			if(sushi.get(arr[idx1]) == 0) { //삭제 이후 연속된 범위 안에 초밥이 없으면 map에서 삭제
				sushi.remove(arr[idx1]);
			}
			//마지막 초밥 추가
			sushi.put(arr[idx2], sushi.getOrDefault(arr[idx2], 0) + 1);
			
			
			// size 검사.
			int size = sushi.size();
			if(max < size) {
				max = size;
			}
			//윈도우 idx 증가
			idx1++;
			idx2++;
			if(idx2 == n) { //회전 초밥은 연결된 점을 생각해서 윈도우 마지막 idex가 초밥 size를 벗어 났을때 idx 0으로 변경 
				idx2 = 0;
			}
			
		}
		System.out.println(max);
	
	
	}
}
