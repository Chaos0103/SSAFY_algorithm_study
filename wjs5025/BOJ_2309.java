import java.util.*;
import java.io.*;

public class Main {
	static int[] talls = new int[9];
	static int[] 난쟁이 = new int[7];
	static boolean[] visited = new boolean[9];
	static StringBuilder sb = new StringBuilder();
	static int[] answer= new int[7];
	
	static int ArraySum (int[] arr) {
		int sum = 0;
		for(int i : arr) {
			sum += i;
		}
		return sum;
	}
	
	static void getPermutation(int idx) {
		if (idx == 7) {
			if(ArraySum(난쟁이) == 100) {
				for(int i = 0;  i < 7; i++) {
					answer[i] = 난쟁이[i];
				}
			}
			return;
		}

		for (int i = 0; i < 9; i++) {
			if (visited[i])
				continue;
			visited[i] = true;
			난쟁이[idx] = talls[i];
			getPermutation(idx + 1);
			visited[i] = false;
		}
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		for (int i = 0; i < 9; i++) {
			talls[i] = sc.nextInt();
		}

		getPermutation(0);
		
		Arrays.sort(answer);
		for(int i = 0; i<7; i++) {
			sb.append(answer[i]).append("\n");
		}
		System.out.println(sb);
	}
}