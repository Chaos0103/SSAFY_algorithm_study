package algorithm_study.day32;

import java.util.ArrayList;
import java.util.Collections;

public class PGS_17677 {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution("E=M*C^2", "e=m*c^2"));
    }
}

class Solution {
    ArrayList<String> A, B;
    ArrayList<String> intersection;
    ArrayList<String> union;

    public int solution(String str1, String str2) {
        int answer = 0;
        init();
        makeSet(str1, A);
        makeSet(str2, B);
        process();
        return getAnswer(union.size(), intersection.size());
    }

    private void init() {
        A = new ArrayList<>();
        B = new ArrayList<>();
        intersection = new ArrayList<>();
        union = new ArrayList<>();
    }

    private void process() {
        Collections.sort(A);
        Collections.sort(B);
        for (String s : A) {
            if (B.remove(s)) {
                intersection.add(s);
            }
            union.add(s);
        }
        union.addAll(B);
    }

    private void makeSet(String str, ArrayList<String> list) {
        String current = "";
        str = str.toLowerCase();
        int len = str.length() - 1;
        for (int i = 0; i < len; i++) {
            current = str.substring(i, i + 2);
            // 모두 영문자로 이루어져있는것만 추가
            if (isMatches(current)) {
                list.add(current);
            }
        }
    }

    private static boolean isMatches(String current) {
        return current.matches("^[a-zA-Z]*$");
    }

    private int getAnswer(double unionSize, double intersectionSize) {
        if (union.size() == 0) {
            return 65536;
        } else {
            return (int) ((intersectionSize / unionSize) * 65536);
        }
    }
}
