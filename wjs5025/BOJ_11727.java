import java.util.Scanner;

// 이론 학습 중에 예제로 보았던 문제라서
// 솔루션을 참고했다고 보면 될 것 같습니다
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int input = sc.nextInt();
		int[] memo = new int[input + 1];

		if (input == 1) {
			System.out.println(1);
		} else if (input == 2) {
			System.out.println(3);
		} else {
			memo[1] = 1;
			memo[2] = 3;
			for (int i = 3; i <= input; i++) {
				memo[i] = (memo[i - 1] + memo[i - 2] * 2) % 10007;
			}
			System.out.println(memo[input]);
		}
	}
}