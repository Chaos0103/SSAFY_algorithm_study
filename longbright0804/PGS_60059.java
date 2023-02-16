/**
 * SSAFY 알고리즘 스터디 16일차 - 자물쇠와 열쇠
 * 미해결
 * @author YoungHwan
 *
 */
public class PGS_60059 {
	
	public static void main(String[] args) {
		// 접근이 안됨 -> 내일 다시 도전
	}
	
    private static boolean solution(int[][] key, int[][] lock) {
    	boolean answer = true;
    	
    	// 다 확인했음에도 열지 못한 경우
        return answer;
	}

	// 2차원 리스트 90도 회전하기
    public static int[][] rotateMatrixBy90Degree(int[][] a) {
        int n = a.length;
        int m = a[0].length;
        int[][] result = new int[n][m]; // 결과 리스트
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result[j][n - i - 1] = a[i][j];
            }
        }
        return result;
    }
}
