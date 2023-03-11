import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_2696 {
	static int M;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc = 0; tc < T; tc++) {
			M = Integer.parseInt(br.readLine());
			ArrayList<Integer> arr = new ArrayList<>();
			StringBuilder sb = new StringBuilder();
			int cnt = 0;
			int result = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());

			for(int i = 1; i <= M; i++) {
				
				
				arr.add(Integer.parseInt(st.nextToken()));
//				System.out.print(arr.get(i-1) + " ");
				Collections.sort(arr);
				if(i % 2 == 1) { // 홀수면
					sb.append(arr.get((i+1)/2-1) + " ");
					cnt++;
					result++;
				}
				if(cnt == 10) {
					sb.append("\n");
					cnt = 0;
				}
				if(i % 10 == 0) {
					st = new StringTokenizer(br.readLine());
				}
			}
			System.out.println(result);
			System.out.println(sb);	
		}
	}
}
