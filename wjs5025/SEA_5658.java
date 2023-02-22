
import java.io.*;
import java.util.*;

public class SEA_5658 {
	static StringBuilder sb = new StringBuilder();
	static String[] inputArr;
	static HashSet<Integer> set;
	static int N, K;

	static void rotateArr() {
		String tmp = inputArr[N - 1];
		for (int i = N - 2; i >= 0; i--) {
			inputArr[i + 1] = inputArr[i];
		}
		inputArr[0] = tmp;
	}

	// tc별 로직수행
	/**
	 * 핵심 아이디어
	 * 1. 변 크기만큼 쪼개서 16진수로 바꾼값을 Set에 넣음.
	 * 2. 한 바퀴 돌아야하니까 N-1번만큼 회전시키고, 회전시킬 때마다 나오는 숫자들을 Set에 넣어줌.
	 * 3. Set를 Integer 배열로 바꾼 후, 내림차순 정렬해서 K-1 번째 값을 뽑아냄.
	 */
	static int getResult(String input) {
		inputArr = input.split("");
		set = new HashSet<>();
		int byunLength = N / 4;

		for (int a = 0; a < N; a++) {
			if (a >= 1) {
				rotateArr();
			}
			for (int i = 0; i < N; i += byunLength) {
				String str = "";
				for (int j = i; j < i + byunLength; j++) {
					str += inputArr[j];
				}
				set.add(Integer.decode("0x" + str));
			}
		}

		
		// Set to Integer Array
		int[] nums = new int[set.size()];
		Object[] setArr = set.toArray();
		
		for (int i = 0; i < set.size(); i++) {
			nums[i] = (int) setArr[i];
		}

		// 배열 내림차순 정렬
		Integer[] numInt = Arrays.stream(nums).boxed().toArray(Integer[]::new);
		Arrays.sort(numInt, Collections.reverseOrder());
		
		// 결과반환
		return numInt[K-1];
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		int tc = sc.nextInt();

		for (int t = 1; t <= tc; t++) {
			N = sc.nextInt();
			K = sc.nextInt();

			sb.append(String.format("#%d %d%n", t, getResult(sc.next())));
		}
		System.out.println(sb);

	}
}
