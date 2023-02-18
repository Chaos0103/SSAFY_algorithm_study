package BOJ__17;

import java.io.*;
import java.util.*;

public class PGS_60058 {
    public static int pos;

    public static String solution(String p) {
        if(p.isEmpty()){
            return p;
        }

        boolean isBalance = isBalance(p);
        String u = p.substring(0, pos);
        String v = p.substring(pos, p.length());

        if(isBalance){
            return u + solution(v);
        } else {
            String ans = "(";

            ans += solution(v);

            ans += ')';

            for (int i = 1; i < u.length() - 1; i++) {
                if (u.charAt(i) == '(') {
                    ans += ")";
                } else {
                    ans += "(";
                }
            }

            return ans;
        }
    }

    public static boolean isBalance(String s) {
        Stack<Character> stack = new Stack<>();
        boolean check = true;
        int left = 0;
        int right = 0;

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '(') {
                left++;
                stack.push('(');
            } else {
                right++;
                if (stack.isEmpty()) {
                    check = false;
                } else {
                    stack.pop();
                }
            }

            if (left == right) {
                pos = i + 1;
                return check;
            }
        }
        return true;
    }
    
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		
		System.out.println(solution(str));
	}
}
