import java.util.*;

class Solution{
	static int N;
	static HashMap<String, String> map = new HashMap<>();
	static StringBuilder sb =new StringBuilder();
	
	public String[] solution(String[] input) {
        int idx = 0;
        
		N = input.length;
		int resultCnt= 0;
		// 맵 처리
		for (int i =0; i <N; i++) {
			String[] info = input[i].split(" ");

			if (info[0].equals("Enter")) {
				map.put(info[1], info[2]);
                resultCnt++;
			} else if (info[0].equals("Leave")) {
                resultCnt++;
			} else {
				map.put(info[1], info[2]);
			}
		}
		
        String[] result = new String[resultCnt];
		for (int i =0; i <N; i++) {
			String[] info = input[i].split(" ");
			
			if (info[0].equals("Enter")) {
				result[idx++] = map.get(info[1])+"님이 들어왔습니다.";
			} else if (info[0].equals("Leave")) {
                result[idx++] = map.get(info[1])+"님이 나갔습니다.";
			}
		}
		return result;
    }
}	