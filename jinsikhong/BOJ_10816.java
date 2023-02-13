package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;


/*
이분탐색으로 풀려고 했지만 자꾸 시간초과 발생...
HASH MAP 이용하여 풀이
-> M데이터 받고, 또 한번 M 만큼 LOOP를 돌며 답을 출력하면 시간초과 발생
-> M 데이터 받음과 동시에 HASHMAP에 GET 하여 정답 출력 하는 방식으로 변경
*/



public class BOJ_10816 {
	static ArrayList<Integer> arr_n;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine()); //상근이 카드
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		HashMap<Integer, Integer> hm = new HashMap<>();
		
//		int[] arr_n = new int[n];
//		arr_n = new ArrayList<>();
		for(int i = 0; i < n; i++) {
			int temp = Integer.parseInt(st.nextToken());
			hm.put(temp, hm.getOrDefault(temp, 0) + 1);
		}
		
		int m = Integer.parseInt(in.readLine()); //상근이 카드
		st = new StringTokenizer(in.readLine(), " ");
//		int[] arr_m = new int[m];
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < m; i++) {
			int temp = Integer.parseInt(st.nextToken());
			sb.append(hm.getOrDefault(temp, 0)).append(" ");
		}
		System.out.println(sb);
//		Collections.sort(arr_n);
		
//		Arrays.sort(arr_n);
//		int start = 0;
//		int end = arr_n.size();
//		for(int i = 0; i < arr_m.length; i++) {
//			System.out.print(hm.getOrDefault(arr_m[i], 0) + " ");
//			}
//			int cnt = 0;
//			int cardidx = arr_n.indexOf(arr_m[i]);
//			if(cardidx == -1) {
//				System.out.print(cnt+ " ");
//			}else {
//				while(true) {
//					if(arr_n.get(cardidx) == arr_m[i]) {
//						cnt++;
//						arr_n.remove(cardidx);
//						if(cardidx == arr_n.size()) {
//							break;
//						}
//					}else {
//						break;
//					}
//				}
//				System.out.print(cnt + " ");
			}
//			while(true) {
//				if(arr_n.indexOf(arr_m[i]) == -1) {
//					System.out.print(cnt+" ");
//					break;
//				}else {
//					arr_n.remove(arr_n.indexOf(arr_m[i]));
//					cnt++;
//				}
//			}
//			int end = arr_n.size()-1;
//			int result = bs(arr_m[i],start,end);
//			System.out.print(result + " ");
//		}
		
	

	
	static int bs(int key, int start, int end) {
		int mid = 0;
		int cnt = 0;
		while(start <= end){
			mid = (start+end)/2;
			if (arr_n.get(mid) == key) {
				cnt++;
//				arr_n.remove(mid);
				int temp = mid;
				int overcnt = 0;
				while(true) {
					if(mid + 1 >= arr_n.size()) break;
					if(arr_n.get(++mid) == key) {
//						arr_n.remove(mid);
						cnt++;
						overcnt++; //종복 카운트 세기
					}else {
						break;
					}
				}
				mid = temp;
				while(true) {
					if((mid - 1) < 0) break;
					if(arr_n.get(--mid) == key) {
//						arr_n.remove(mid);
						cnt++;
						overcnt++;
					}else {
						break;
					}
				}
				for(int i = 0; i < overcnt; i++) {
					arr_n.remove(mid);
				}
				return cnt;
			} else if(key < arr_n.get(mid)) {
				end = mid -1;
			}else {
				start = mid + 1;
			}
		}
		return cnt;
	}
}
