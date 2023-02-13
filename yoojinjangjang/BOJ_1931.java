package com.yoojin.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_1931 {
	
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		Room[] rooms = new Room[N];
		for(int i = 0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			rooms[i] = new Room(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())); // 각 요소들을 받아서 배열로 저장
		}
		
		// 1. endTime으로 정렬한다. 
		Arrays.sort(rooms);
		
		// 2. 첫번째 회의실부터 시작하여  회의실 세기
		int cnt = 1;
		Room curRoom = rooms[0];
		int endTime = curRoom.getEndTime();
		for(int i=1;i<N;i++) {
			Room nextRoom = rooms[i];
			if(nextRoom.getStartTime() >= endTime) {
				endTime = nextRoom.getEndTime();
				cnt++;
			}
		}
		
		System.out.println(cnt);
		
	}
	
}

class Room implements Comparable<Room>{
	private int startTime;
	private int endTime;
	public Room(int startTime, int endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
	}
	public int getStartTime() {
		return startTime;
	}
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
	public int getEndTime() {
		return endTime;
	}
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}
	@Override
	public int compareTo(Room o) {
		if(this.endTime != o.endTime) {
			return Integer.compare(this.endTime, o.endTime);
		}
		return Integer.compare(this.startTime, o.startTime);
	}
	
	@Override
	public String toString() {
		return startTime + " " + endTime;
	}
	
}
