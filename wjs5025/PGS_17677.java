package PGS_17677;

import java.util.*;
import java.io.*;

class Solution {
	static ArrayList<String> strOneSet;
	static ArrayList<String> strTwoSet;

	// str이 모두 영어인지 검사
	static boolean checkStr(String str) {
		String strSet = "abcdefghijklmnopqrstuvwxyz";

		if (strSet.contains(Character.toString(str.charAt(0))) && strSet.contains(Character.toString(str.charAt(1)))) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	static double getJacad() {
		ArrayList<String> union = new ArrayList<>();
		ArrayList<String> inter = new ArrayList<>();


		for (String s : strOneSet) {// 교집합 구하기
			if (strTwoSet.remove(s))
				inter.add(s);
		}
		union.addAll(strOneSet);
		union.addAll(strTwoSet);
		if (union.size() == 0 && inter.size() == 0) {
			return 1;
		}

		System.out.println(union);
		System.out.println(inter);
		return (double) inter.size() / union.size();
	}

	public int solution(String str1, String str2) {
		int answer = 0;

		str1 = str1.toLowerCase();
		str2 = str2.toLowerCase();

		strOneSet = new ArrayList<>();
		strTwoSet = new ArrayList<>();

		for (int i = 0; i < str1.length() - 1; i++) {
			String tmp = str1.substring(i, i + 2);
			if (checkStr(tmp)) {
				strOneSet.add(tmp);
			}
		}

		for (int i = 0; i < str2.length() - 1; i++) {
			String tmp = str2.substring(i, i + 2);
			if (checkStr(tmp)) {
				strTwoSet.add(tmp);
			}
		}

		answer = (int) (getJacad() * 65536);

		return answer;
	}
}

public class Main {
	public static void main(String[] args) {
		Solution solution = new Solution();
		String[][] input = { { "FRANCE", "french" }, { "handshake", "shake hands" }, { "aa1+aa2", "AAAA12" },
				{ "E=M*C^2", "e=m*c^2" } };

		for (int i = 0; i < 4; i++) {
			System.out.println(solution.solution(input[i][0], input[i][1]));
		}
	}
}