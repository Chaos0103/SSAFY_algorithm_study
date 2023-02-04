import java.util.*;
import java.io.*;

// 오.. 이 문제는 솔루션 안보고 1트에 성공한 나.. 
// 조금 대견할지도;

public class Main {
	static int[][] house;
	static int[][] cost;

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int nowIdx = 0;
		
		house = new int[N+1][3];
		cost = new int[N+1][3];
		
		// 입력받기
		for (int i = 0; i < N; i++) {
			for (int j=0; j <3; j++) {
				house[i][j] = sc.nextInt();
				if (i == 0) {
					cost[i][j] = house[i][j];
				}
			}
		}
		
		// 칠하기
		for(int i = 1; i < N; i++) {
			for (int j =0; j < 3; j ++) {
					for (int k =0; k<3; k++) {
						if (k != j) {
							int sum = house[i][j] + cost[i-1][k];
							if (cost[i][j] != 0 && sum < cost[i][j]) {
								cost[i][j] = sum;
							}
							if(cost[i][j] == 0) {
								cost[i][j] = sum;
							}
						}
				}
			}
		}
		
		
		// 출력하기
		int min = Integer.MAX_VALUE;
		for(int i =0; i<3; i++) {
			if (min > cost[N-1][i])
				min = cost[N-1][i];
		}
		
		System.out.println(min);
	}
}