import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] people;

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		int sum = 0;

		people = new int[N+1];
		for (int i = 1; i <= N; i++) {
			people[i] = sc.nextInt();
		}
		
	
		Arrays.sort(people);
		
		for (int i = 1; i <= N; i++) {
			 people[i] = people[i] + people[i-1];
			 sum += people[i];
		}
		
		System.out.println(sum);
	}
}