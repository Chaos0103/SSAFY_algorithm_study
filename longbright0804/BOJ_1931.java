import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1931 {
	static int n;
	static int result = 1;
	static List<Meeting> list = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 회의 개수 입력
		n = Integer.parseInt(br.readLine());
		// 회의 리스트 입력
		StringTokenizer st = null;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			list.add(new Meeting(start, end));
		}
		// 종료시간 기준으로 회의 리스트 정렬
		Collections.sort(list, new Comparator<Meeting>() {

			@Override
			public int compare(Meeting o1, Meeting o2) {
				if (Integer.compare(o1.end, o2.end) == 0) {
					return Integer.compare(o1.start, o2.start);
				}
				return Integer.compare(o1.end, o2.end);
			}
			
		});
		Meeting prev = list.get(0);
		Meeting now = null;
		for (int index = 1; index < n; index++) {
			now = list.get(index);
			// 진행 불가능 할 경우 포함 X
			if (prev.end > now.start) {
				continue;
			}
			// 진행 가능 할 경우 포함
			prev = now;
			result++;
		}
		System.out.println(result);
	}
}

class Meeting {
	int start;
	int end;

	public Meeting(int start, int end) {
		this.start = start;
		this.end = end;
	}

	/**
	 * @return the start
	 */
	public int getStart() {
		return start;
	}

	/**
	 * @return the end
	 */
	public int getEnd() {
		return end;
	}
}
