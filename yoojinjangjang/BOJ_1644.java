package com.yoojin.twopointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_1644 {
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		
		boolean[] notPrime = new boolean[N+1];
		notPrime[0] = true;
		notPrime[1] = true; // 0과 1은 소수가 아님 
		for(int i = 2;i*i<=N;i++) { //2부터 소수 검사 
			if(!notPrime[i]) {
				for(int j = i*i; j<=N;j+=i) { // 배수들을 불린 true 처리(소수가아님)
					notPrime[j] = true;
				}
			}
		}
		
		// 소수들의 배열 생성
		List<Integer> primes = new ArrayList<>();
		for(int i = 2;i<=N;i++) {
			if(!notPrime[i]) { // 소수이면 값 넣기
				primes.add(i);

			}
		}
		
		
		// 구한 소스들을 two pointer로 합계 구하기 
		int cnt = 0; // 경우의 수 (만약 해당 합계가 N과 같다면 증가시킴)
		int s = 0;
		int e = 0; 
		int sum =0;
		while(true) {
			
			if(sum>=N) {
				sum -= primes.get(s);
				s++;
			}else if (e == primes.size()) {
				break;
			}
			else {
				sum += primes.get(e);
				e++;
			}
			
			if(sum == N) cnt++;
		}
		
		System.out.println(cnt);
	}
}
