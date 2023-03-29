package Algorithm_230329;

import java.io.*;
import java.util.*;

public class BOJ_5052 {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int testCase = 1; testCase <= T; testCase++) {
            int n = Integer.parseInt(br.readLine());
            
            String[] arr = new String[n];
            HashSet<String> set = new HashSet<>();
            boolean flag = true;

            for (int i=0; i<n; i++) {
                String str = br.readLine();
                arr[i] = str;
                set.add(str);
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < arr[i].length(); j++) {
                    if (set.contains(arr[i].substring(0, j))) {
                        flag = false;
                        break;
                    }
                }
            }

            if (flag == true) {
            	System.out.println("YES");
            }
            else System.out.println("NO");
        }
	}
}