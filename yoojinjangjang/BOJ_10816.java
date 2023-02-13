package com.yoojin.binarysearch;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class BOJ_10816 {
	//static int[] cards;
	static Map<Integer,Integer> cards;
	static Integer[] cardsList;
	static int[] finds;
	static int cnt = 0;
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		StringTokenizer st = new StringTokenizer(in.readLine());
		cards = new TreeMap<>();
		for(int i = 0;i<N;i++) {
			int card = Integer.parseInt(st.nextToken());
			cards.put(card, cards.getOrDefault(card, 0)+1);
			
		}
		
		int M = Integer.parseInt(in.readLine());
		st = new StringTokenizer(in.readLine());
		finds = new int[M];
		for(int i = 0;i<M;i++) {
			finds[i] = Integer.parseInt(st.nextToken());
		}

		//Collections.sort(cards,); // 정렬
		
		
		
		StringBuilder sb = new StringBuilder();
		cardsList = cards.keySet().toArray(new Integer[0]);
		// 이진 탐색 
		for(int i = 0;i<M;i++) {
			cnt = 0;
			binarySearch(finds[i], 0, cards.size()-1);
			sb.append(cnt+" ");
		}
		
		System.out.println(sb.toString());
	}
	
	
	private static void binarySearch(int key, int low, int high) {
		
		
		// low 가 high보다 작거나 같은 경우
		int mid;
		if(low <= high) {
			mid = (low+high)/2;
			if(key == cardsList[mid]) {
				
				cnt = cards.get(cardsList[mid]);
				return;
				
				
			}
			
			if(key < cardsList[mid]) {
				binarySearch(key, low, mid-1);
			} 
			
			if(key > cardsList[mid]) {
				binarySearch(key, mid +1 , high);
			}
			
		}
		
	}
}