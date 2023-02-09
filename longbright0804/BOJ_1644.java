import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * SSAFY 알고리즘 스터디 10일차 - 소수의 연속합
 * 접근
 * 1. 소수 배열을 만든다(크기를 얼마로 해야하는가, 어떻게 만드는가)
 * 2. 소수 배열을 투포인터 알고리즘을 통해 접근한다
 * 3. 소수 배열을 투포인터를 통해 탐색하며 n 을 만드는 부분합을 찾는다
 * @author SSAFY
 *
 */
public class BOJ_1644 {
	static int n;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
	}
}
