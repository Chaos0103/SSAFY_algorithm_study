package Algorithm_230319;

import java.io.*;
import java.util.*;

public class BOJ_11000 {
	private static class Point{
		int start, end;

		public Point(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		Point lesson[] = new Point[N];
		StringTokenizer st = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			lesson[i]=new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(lesson, (l1, l2) -> l1.start == l2.start ? l1.end - l2.end : l1.start - l2.start);
		
        PriorityQueue<Integer> pQueue = new PriorityQueue<>();
        pQueue.offer(lesson[0].end);
        
        for (int i = 1; i < N; i++) {
            if (pQueue.peek() <= lesson[i].start) {
                pQueue.poll();
            }
            pQueue.offer(lesson[i].end);
        }
        System.out.println(pQueue.size());
	}
}