package com.yoojin.boj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_1963 {
	static boolean[] prime = new boolean[10000];
	static boolean[] visited;
	static ArrayList<Integer> primeList = new ArrayList<>();
	static int to,from;
	static int result;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		makePrime();
				
		int T = Integer.parseInt(br.readLine());
		for(int testNum = 1;testNum<=T;testNum++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			result = Integer.MAX_VALUE;
			
			visited = new boolean[10001];
			visited[from] = true;
			findTo(from, 0);
			if(result == Integer.MAX_VALUE) {
				System.out.println("Impossible");
			}
			System.out.println(result);
		}
	}

	private static void findTo(int search, int cnt) {
		
		
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {search,0});
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			if(cur[0] == to) {
				result = Math.min(result, cur[1]);
			}
			
			for(int j = 0;j<10;j++) {
				for(int i =3;i>=0;i--) {
					int num = (int) (cur[0] - ((cur[0]+"").charAt(3-i)-'0') * Math.pow(10,i));
					num += Math.pow(10,i)*j;
					if(num < 1000 || cur[0] == num ) continue;
					if(!prime[num]) continue;
					if(visited[num]) continue;
					visited[num] = true;
					queue.offer(new int[] {num,cur[1]+1});
				}
			}
			
		}
		
	}

	private static void makePrime() {
		Arrays.fill(prime, true);
		for(int i = 2;i<10000/2;i++) {
			int mul = i*i;
			while(mul < 10000) {
				prime[mul] = false;
				mul += i;
			}
		}
		
		for(int i = 1000;i<10000;i++) {
			if(prime[i]) {
				primeList.add(i);
			}
		}
	}
}
