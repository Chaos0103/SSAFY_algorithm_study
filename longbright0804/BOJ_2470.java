import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * SSAFY 알고리즘 8일차 - 두 용액
 * 접근을 못함
 * @author YoungHwan
 *
 */
public class BOJ_2470 {
	static int n;
	static int[] arr;
	
	public static void main(String[] args) throws Exception {
		// 접근을 못함
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i< n; i++) {
			arr[i] = parseInt(st.nextToken());
		}
		Arrays.sort(arr);
	}
}
