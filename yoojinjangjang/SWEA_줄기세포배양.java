package com.yoojin.swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA_줄기세포배양 {
	static class Node {
		int x;
		int y;
		int power;
		int time;
		public Node(int x, int y, int power, int time) {
			super();
			this.x = x;
			this.y = y;
			this.power = power;
			this.time = time;
		}
		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + ", power=" + power + ", time=" + time + "]";
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}
		
	}
	
	static int N,M,K;
	static ArrayList<Node> nonActive;
	static ArrayList<Node> active;
	static ArrayList<Node> die;
	static ArrayList<Node> temp;
	static int[][] locs = {{1,0},{-1,0},{0,1},{0,-1}};
	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("swea_줄기세포.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		for(int testNum = 1;testNum<=T;testNum++) {
			nonActive = new ArrayList<>();
			active = new ArrayList<>();
			die = new ArrayList<>();
			StringTokenizer st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			
			// 1. 입력 받기 -> 첫입력시 모든 줄기세포들은 비활성화 상태이다. 
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(in.readLine());
				for(int j = 0;j<M;j++) {
					int input = Integer.parseInt(st.nextToken());
					if(input != 0) {
						nonActive.add(new Node(i,j,input,0)); // 모든 줄기세포들을 비활성화 배열에 저장한다. 
					}
				}
			}
			
			// 2. 시간초까지 시간을 증가시키면서 진행한다. 
			for(int k = 1;k<=K;k++) {
				temp  = new ArrayList<>(); // 해당 초에 새로 생긴 노드들을 담을 임시 배열이다. 
				
				
				
				// 3. 활성상태에서 죽은 상태로 변경되는 노드 확인
				for(int i =0;i<active.size();i++) {
					Node current = active.get(i);
					if(k-current.time == 1) {
						// 2-2. 활성상태 진입 후 1시간 지남 주변 4탐을 하며, 새로운 줄기세포를 배양할 수 있는지를 확인한다. 
						for(int[] loc : locs) {
							int nx = current.x + loc[0];
							int ny = current.y + loc[1];
							Node newNode = new Node(nx,ny,current.power,k);
							if(nonActive.contains(newNode) || active.contains(newNode) || die.contains(newNode)) continue; // 다른 배열에 이미 있는 경우 해당 자리는 이미 차지되었다고 본다.
							// 차지가 되어있지 않다면 생성을 위한 작업을 진행한다.
							if(temp.contains(newNode)) {
								if(temp.get(temp.indexOf(newNode)).power < newNode.power) {
									temp.remove(temp.indexOf(newNode));
									temp.add(newNode);
								}
							} else {
								temp.add(newNode);
							}
						}
					}
					
					if(k-current.time == current.power) {
						active.remove(i);
						i--;
						die.add(current);
					}
				}
				
				
				
				// 2-1. 비활성화 배열들을 확인하며 활성으로 넘어갈 노드들이 있는지 확인한다. 
				for(int i = 0;i<nonActive.size();i++) {
					Node current = nonActive.get(i);
					if(k-current.time == current.power) { // 현재 시간 - 만들어진 시간 == 생명력 수치 (비활성상태에서 X시간이 지나는 순간 활성 상태가 된다.)
						// 활성으로 변경 (비활성 배열에서 제거하고, 활성 배열에 추가한다. 이때, time을 갱신해준다.)
						nonActive.remove(i);
						i--;
						current.time = k;
						active.add(current);
					}
				}
							
				nonActive.addAll(temp);
				

			}
			
			System.out.println(nonActive.size()+active.size());
			
			
			
		}
	}

}
