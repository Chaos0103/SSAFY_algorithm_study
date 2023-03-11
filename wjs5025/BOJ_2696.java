import java.io.*;
import java.util.*;

public class BOJ_2696 {
	static int M;
	static ArrayList<Integer> arr;
	
	public static void main(String[] args) throws IOException{
		Scanner sc =new Scanner(System.in);
		StringBuilder sb =new StringBuilder();
		
		
		int tc = sc.nextInt();
		
		for (int t= 0; t<tc; t++) {
			arr = new ArrayList<>();
			
			M = sc.nextInt();
			int cnt = 0;
			ArrayList<Integer> answers = new ArrayList<>();
			for (int i =0; i <M; i++) {
				arr.add(sc.nextInt());
				if (arr.size()%2 == 1) {
					cnt++;
					Collections.sort(arr);
					answers.add(arr.get(arr.size()/2));
				}
			}
			sb.append(String.format("%d%n",cnt));
			int lastIDX = 0;
			for (int i = 0; i <cnt; i++) {
				sb.append(answers.get(i)).append(" ");
				if (i == 9) {
					lastIDX = i;
					break;
				}
			}
			if (cnt > 10) {
				sb.append("\n");
				for (int i =lastIDX+1; i<cnt; i++) {
					sb.append(answers.get(i)).append(" ");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
		
	}
}
 BOJ_2696 {
    
}
