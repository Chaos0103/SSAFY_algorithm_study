package com.yoojin.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_20056 {
	static ArrayList<FireBall> balls = new ArrayList<>();
	static ArrayList<FireBall> temps = new ArrayList<>();
	static int N,M,K;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int cnt = 1;
			boolean isAllSame = true;
			balls.add(new FireBall(r, c, m, s, d, cnt, isAllSame));
		}
		
		for(int i =0;i<K;i++) {
			// 1. 모든 파이어볼을 이동시키기
			temps = new ArrayList<>();
			moveAllFireBalls();
			
			// 2. 이동시킨 파이어볼들 중 2개이상인 파이어볼에 대한 처리
			divideFireBall();
		}
		
		// 3. 남아있는 파이어볼 질량의 합 구하기
		getAllM();
		
		System.out.println(sumOfM);
	}
	private static void getAllM() {
		for(int i = 0;i<balls.size();i++) {
			sumOfM += balls.get(i).m;
		}
		
	}
	static int sumOfM;
	static int[] allSame = {0,2,4,6};
	static int[] notAllSame = {1,3,5,7};
	private static void divideFireBall() {
		for(int i = 0;i<temps.size();i++) {
			FireBall fireBall = temps.get(i);
			if(fireBall.cnt == 1) {
				balls.add(fireBall);
				continue;
			}
			
			int newM = fireBall.m / 5;
			if(newM<=0) continue;
			int newS = fireBall.s / fireBall.cnt;
			int[] newD = fireBall.isAllSame?allSame:notAllSame;
			for(int j =0;j<4;j++) {
				balls.add(new FireBall(fireBall.r, fireBall.c, newM, newS, newD[j], 1, true));
			}
		}
		
	}

	static int[][] locs = {{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};
	private static void moveAllFireBalls() {
		for(int i =0;i<balls.size();i++) {
			// 한개씩 돌면서 이동시키기
			FireBall fireBall = balls.get(i);
			balls.remove(i--);
			fireBall.r += (fireBall.s * locs[fireBall.d][0]);
			fireBall.c += (fireBall.s * locs[fireBall.d][1]); // 이동시키기
			while(fireBall.r< 0) {
				fireBall.r = (N+fireBall.r);
			}
			while(fireBall.c < 0) {
				fireBall.c = (N+fireBall.c);
			}
			fireBall.r %= N;
			fireBall.c %= N;
			
			
			if(temps.contains(fireBall)) {
				FireBall temp = temps.get(temps.indexOf(fireBall));
				temp.m += fireBall.m;
				temp.s += fireBall.s;
				temp.cnt++;
				if(fireBall.d%2!=temp.d%2) {
					temp.isAllSame = false;
				}
			} else {
				temps.add(fireBall);
			}
		}
	}

	static class FireBall {
		int r,c; // 행, 열
		int m,s,d; // 질량, 속도, 방향
		int cnt; // 수
		boolean isAllSame; // 모두 같은 짝수,홀수 인지 여부
		public FireBall(int r, int c, int m, int s, int d, int cnt, boolean isAllSame) {
			super();
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
			this.cnt = cnt;
			this.isAllSame = isAllSame;
		}
		@Override
		public String toString() {
			return "FireBall [r=" + r + ", c=" + c + ", m=" + m + ", s=" + s + ", d=" + d + ", cnt=" + cnt
					+ ", isAllSame=" + isAllSame + "]";
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + c;
			result = prime * result + r;
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
			FireBall other = (FireBall) obj;
			if (c != other.c)
				return false;
			if (r != other.r)
				return false;
			return true;
		}
		
		
		
	}
}
