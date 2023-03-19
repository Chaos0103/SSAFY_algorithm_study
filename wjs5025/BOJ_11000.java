package BOJ_11000;

import java.io.*;
import java.util.*;

/**
 * https://steady-coding.tistory.com/253
 * 솔루션 참고
 * 
 * 
 * @author jeon
 *
 */
// 강의시간을 저장하는 ㅗ드
class Time implements Comparable<Time>{
	int start;
	int end;

	
	public Time(int start, int end) {
		super();
		this.start = start;
		this.end = end;
	}


	@Override
	public int compareTo(Time o) {
		if (this.start - o.start == 0) {
			return this.end - o.end;
		}
		return this.start - o.start;
	}
}

public class Main {
	static Time[] times;
	public static void main(String[] args) throws IOException{
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		times = new Time[N];
		
		for (int i =0; i< N;i++) {
			times[i] = new Time(sc.nextInt(),sc.nextInt());
		}
		
		// 강의시간 정렬 (시작시간 빠른순 + 같으면 종료시간 빠른순
		Arrays.sort(times);
		
		// pq에 첫 강의 종료시간 넣기
		pq.offer(times[0].end);
		
		// 다음 강의의 시작시간이 
		// 현재 진행할 강의의 종료시간보다 느리다면
		// 이어서 진행할 수 있는 강의이므로 poll().
		// 불가능하다면 따로 진행해야하는 강의이므로 남겨놓는다.
		for (int i = 1; i <N; i++) {
			if (pq.peek() <= times[i].start) {
				pq.poll();
			}
			pq.offer(times[i].end);
		}
		
		// 남아있는 강의들의 수가 필요한 강의실 개수.
		System.out.println(pq.size());

	}
}
