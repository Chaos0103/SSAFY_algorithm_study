package PGS;

import java.util.Scanner;
import java.util.Stack;

public class PGS_60058 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Solution s = new Solution();
        String p = sc.next();
        p = s.solution(p);
        System.out.println(p);
    }
}




class Solution {
    public String solution(String p) {
        if(p.isEmpty()){
            return p;
        }
        StringBuilder sb_u = new StringBuilder();
        StringBuilder sb_v = new StringBuilder();
        int cnt = 0;
        for(int i = 0; i < p.length(); i++){
            if(p.charAt(i) == '('){
                cnt++;
                sb_u.append(p.charAt(i));
            }else{
                sb_u.append(p.charAt(i));
                cnt--;
            }
            if(cnt == 0){
                break;
            }
        }
        sb_v.append(p.substring(sb_u.length()));

        if(corretchk(sb_u.toString())){
            sb_u.append(solution(sb_v.toString()));
            return sb_u.toString();
        }else{
            StringBuilder sb_new = new StringBuilder();
            sb_new.append('(');
            sb_new.append(solution(sb_v.toString()));
            sb_new.append(')');
            sb_u.deleteCharAt(0);
            sb_u.deleteCharAt(sb_u.length()-1);
            for(int i = 0; i < sb_u.length(); i++){
                if(sb_u.charAt(i) == '('){
                    sb_new.append(')');
                }else{
                    sb_new.append('(');
                }
            }
            return sb_new.toString();
        }

    }



    public static boolean corretchk(String p){
        Stack<Character> stack = new Stack<>();
        char[] char_arr = p.toCharArray();

        for(int i = 0; i <char_arr.length; i++){
            if(char_arr[i] == '('){
                stack.push(char_arr[i]);
            }else{
                if(!stack.isEmpty()){
                    stack.pop();
                }else{
                    return false;
                }
            }
        }
        if(stack.isEmpty()){
            return true;
        }else{
            return false;
        }
    }
}

