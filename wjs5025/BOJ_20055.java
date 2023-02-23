import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 다신 배열 한칸씩 미는걸
 * 귀찮아하지 않겠습ㄴ디ㅏ.. 
 * 
 */
public class Main {
	static int N, K;
//	static LinkedList<Integer> durability = new LinkedList<>();
//	static LinkedList<Integer> belt = new LinkedList<>();
	static int[] durability;
	static int[] belt;
	static int cnt = 0;
	static boolean done;
	static int zeroCnt = 0;
	static int beltSize;

	// 4. 내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료한다. 그렇지 않다면 1번으로 돌아간다.
	public static void step4() {
		if (!checkDurability()) {
			done = true;
		}
	}

	// 3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
	public static void step3() {
		if (durability[0] > 0) {
			belt[0] = 1;
			durability[0] = durability[0] - 1;
		}
	}

	// 2. 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다. 만약 이동할 수 없다면 가만히
	// 있는다.
	public static void step2() {
		for (int i = beltSize - 1; i >= 0; i--) {
			// 다음칸이 비어있고, 다음칸의 내구도가 1이상일때, 한칸이동해야해.
			int nextIdx = (i + 1) % (beltSize); // 끝엣놈을

			if (belt[i] == 1 && (belt[nextIdx] == 0) && durability[nextIdx] >= 1 && durability[nextIdx] - 1 >= 0) {
				// 만약, 내리는 위치에 도착해버리면
				if (nextIdx == N - 1) {
					belt[nextIdx] = 0;
				} else {
					belt[nextIdx] = belt[i];
				}
				belt[i] = 0;
				durability[nextIdx] = durability[nextIdx] - 1;
				i++;
			}
		}
	}

	// 1. 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
	public static void step1() {
		int tmp = durability[beltSize - 1];
		for (int i = beltSize - 2; i >= 0; i--) {
			durability[i + 1] = durability[i];
		}
		durability[0] = tmp;

		tmp = belt[beltSize - 1];
		for (int i = beltSize - 2; i >= 0; i--) {
			belt[i + 1] = belt[i];
		}
		belt[0] = tmp;

		belt[N - 1] = 0;

	}

	public static boolean checkDurability() {
		// 내구도 0인게 K개 이상이면 false
		// 아니면 true 리턴
		int cnt = 0;
		for (int i = 0; i < beltSize; i++) {
			if (durability[i] == 0)
				cnt++;
		}
		if (cnt >= K)
			return false;
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String tmp = br.readLine();
		StringTokenizer st = new StringTokenizer(tmp, " ");

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		beltSize = 2 * N;

		durability = new int[beltSize];
		belt = new int[beltSize];

		tmp = br.readLine();
		st = new StringTokenizer(tmp, " ");

		for (int i = 0; i < beltSize; i++) {
			durability[i] = Integer.parseInt(st.nextToken());
			belt[i] = 0;
		}

		while (!done) {
			cnt++;
			step1();
			step2();
			step3();
			step4();
		}

		bw.write(Integer.toString(cnt));
		bw.flush();
	}
}