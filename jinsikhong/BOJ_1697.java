package BJ;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * 풀이시간 1시간
 * 45번줄 메모리초과 오류 발생
 */
public class BOJ_1697 {
	static int cnt = 0;
	static int[] arr = new int[100001];
//	static ArrayList<Integer> arr2 = new ArrayList<Integer>();
	static int[] move = {1,-1,2};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		bfs(n,k);
	}
	
	
	static void bfs(int s, int k) {
		arr[s] = 1; 
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(s);
		
		while(!q.isEmpty()) {
			int x = q.poll();
			if(x == k) {
//				for(int i = 0; i < 20; i++) {
//					System.out.println(arr[i]);
//				}
				System.out.println(arr[k]-1);
				return;
			}
			if(arr[x] != 0) {
				for(int i = 0; i < 3; i++) {
					int y = x + move[i];
					if(i == 2) {
						y = x * move[i];
					}
					if(y >= 0 && y <= 100000 && arr[y] == 0) {
						arr[y] = arr[x]+1;
						q.offer(y);
					}
				}
			}
		}
	}
}
