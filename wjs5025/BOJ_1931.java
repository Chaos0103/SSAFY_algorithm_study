package BOJ_1931;

import java.util.*;

/*
 3트 : 성공 (약간의 솔루션 참고)
정렬 시, compare function이 문제였다;
끝나는 시간 기준 오름차순 정렬을 했으면 
끝나는 시간이 같을때 시작시간도 오름차순 정렬해줬어야 했습니다;;;;;
진짜 재밌다 알고리즘 짱......
 * */

public class Main {
	static int N;
	static int[][] info;
	static int max = Integer.MIN_VALUE;

	public static int search() {
		int cnt = 0;
		int endTime = 0;

		for (int i = 0; i < N; i++) {
			if (endTime <= info[i][0]) {
				endTime = info[i][1];
				cnt++;
			}
		}

		return cnt;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		info = new int[N][2];

		for (int i = 0; i < N; i++) {
			info[i][0] = sc.nextInt();
			info[i][1] = sc.nextInt();
		}

		// 정렬
		Arrays.sort(info, (o1, o2) -> ((o1[1] == o2[1]) ? o1[0] - o2[0] : o1[1] - o2[1]));
		System.out.println(search());
	}
}
/* 2트 
 * 계속 78~85퍼에서 틀렸습니다가 뜸..
 * 이유를 모르겠습니다..
 */
/*
import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static ArrayList<int[]> info = new ArrayList<>();
	static int max = Integer.MIN_VALUE;

	public static int search() {
		int cnt = 1;
		int endTime = info.get(0)[1];
		
		for (int i =1; i <N; i++) {
			if (info.get(i)[0] >= endTime) {
				cnt++;
				endTime = info.get(i)[1];
			}
		}
		
		return cnt;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();

		for (int i = 0; i < N; i++) {
			int[] tmp = new int[2];
			for (int j = 0; j < 2; j++) {
				tmp[j] = sc.nextInt();
			}
			info.add(tmp);
		}

		// 정렬
		Collections.sort(info, (o1, o2) -> {
			return o1[1] - o2[1]; // 첫번째 숫자 기준 오름차순 {1,30}{2,10}{3,50}{4,20}{5,40}
		});
		
		System.out.println(search());
	}
}
*/


/*
 * 1트 : 시작시간 기준 정렬 하고 하나씩 돌면서 좌우 갯수 탐색하기(사실상 의미가 없었음)
 */

//public class Main {
//	static int N;
//	static int[][] info;
//	static int max = Integer.MIN_VALUE;
//
//	public static int searchLeft(int left, int idx) {
//		int cnt = 0;
//		
//		while (idx != 0) {
//			if (left >= info[idx][1]) {
//				cnt++;
//				left = info[idx][0];
//			}
//			idx--;
//		}
//
//		return cnt;
//	}
//
//	public static int searchRight(int right, int idx) {
//		int cnt = 0;
//
//		while (idx != N-1) {
//			if (right <= info[idx][0]) {
//				cnt++;
//				right = info[idx][1];
//			}
//			idx++;
//		}
//
//		return cnt;
//	}
//
//	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//
//		N = sc.nextInt();
//
//		info = new int[N][2];
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < 2; j++) {
//				info[i][j] = sc.nextInt();
//			}
//		}
//
//		// 정렬
//		Arrays.sort(info, (o1, o2) -> {
//			return o1[0] - o2[0]; // 첫번째 숫자 기준 오름차순 {1,30}{2,10}{3,50}{4,20}{5,40}
//		});
//
//		// 탐색
//		for (int i = 0; i < N; i++) {
//			int tmp = searchLeft(info[i][0], i) + searchRight(info[i][1], i);
//			max = Math.max(tmp+1, max);
//		}
//
//		System.out.println(max);
//	}
//}