import java.util.Arrays;
import java.util.Scanner;

/**
 * SSAFY 알고리즘 스터디 2일차 - 암호 만들기
 * 23.02.01 00:50 시작 - 30분소요
 * 
 * 조합 사용
 * 문자배열 사전 정렬로 오름차순 점검
 * 각 조합 별로 자음 모음 개수 확인
 * @author YoungHwan
 *
 */
public class BOJ_1759 {
	static int l;
	static int c;
	static char[] arr;
	static char[] password;
	static boolean[] visited;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		l = sc.nextInt();
		c = sc.nextInt();
		arr = new char[c];
		password = new char[l];
		visited = new boolean[c];
		for (int i = 0; i < c; i++) {
			arr[i] = sc.next().charAt(0);
		}
		// 오름차순 정렬 여부 검사를 피하기 위함
		Arrays.sort(arr);
		combination(0, 0);
	}
	
	static void combination(int count, int start) {
		if (count == l) {
			if (isValid()) {
				System.out.println(String.valueOf(password));
			}
			return;
		}
		for (int i = start; i < c; i++) {
			password[count] = arr[i];
			combination(count + 1, i + 1);
		}
	}
	
	static boolean isValid() {
		int vowels = 0;		// 모음의 개수
		int consonants = 0;	// 자음의 개수
		
		// 자음과 모음의 개수 확인
		for (int i = 0; i < l; i++) {
			if (password[i] == 'a' || password[i] == 'e' || password[i] == 'i' || password[i] == 'o' || password[i] == 'u') {
				vowels++;
			} else {
				consonants++;
			}
		}
		// 모음이 1개 미만이고 자음이 2개 미만일 경우 조건 불충족
		if (vowels < 1 || consonants < 2) {
			return false;
		}
		return true;
	}
}
