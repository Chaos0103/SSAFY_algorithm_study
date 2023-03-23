package com.yoojin.boj.g2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class BJ_4195 {
	static int T;
	static int F;
	static Map<String,Integer> map;
	static int[] parent;
	static int[] level;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for(int testNum=1;testNum<=T;testNum++) {
			F = Integer.parseInt(br.readLine());
			map = new HashMap<>();
			parent = new int[F*2];
			level = new int[F*2];
			
			for(int i=0;i<F*2;i++) {
				parent[i] = i;
				level[i] = 1;
			}
			
			int idx = 0;
			for(int i =0;i<F;i++) {
				String[] freind = br.readLine().split(" ");
				
				if(!map.containsKey(freind[0])) {
					map.put(freind[0], idx++);
				}
				
				if(!map.containsKey(freind[1])) {
					map.put(freind[1], idx++);
				}
				
				System.out.println(union(map.get(freind[0]),map.get(freind[1])));
			}

		}
	}
	
	static int find(int x) {
		if(parent[x] == x) {
			return x;
		}
		
		return parent[x] = find(parent[x]);
	}
	
	static int union(int x,int y) {
		x = find(x);
		y = find(y);
		
		if(x != y) {
			parent[y] = x;
			level[x] += level[y];
			
			level[y] = 1;
		}
		
		return level[x];
	}
	

}
