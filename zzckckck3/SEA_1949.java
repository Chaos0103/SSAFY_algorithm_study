package Algorithm_230304;

import java.io.*;
import java.util.*;

public class SEA_1949 {
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int N, K;
	static int MAX = Integer.MIN_VALUE;
	
	static class Point {
        int row, col;
 
        public Point(int row, int col) {
            super();
            this.row = row;
            this.col = col;
        }
    }
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            MAX = Integer.MIN_VALUE;
            StringTokenizer token = new StringTokenizer(br.readLine());
            N = Integer.parseInt(token.nextToken());
            K = Integer.parseInt(token.nextToken());
            int[][] map = new int[N][N];
            int max = 0;
            for (int r = 0; r < N; r++) {
                token = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    map[r][c] = Integer.parseInt(token.nextToken());
                    if (max < map[r][c]) {
                        max = map[r][c];
                    }
                }
            }
            List<Point> list = new ArrayList<>();
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (max == map[r][c]) {
                        list.add(new Point(r, c));
                    }
                }
            }
 
            for (int k = 0; k <= K; k++) {
                for (int r = 0; r < N; r++) {
                    for (int c = 0; c < N; c++) {
                        if (map[r][c] - k < 0) {
                            continue;
                        } else {
                            map[r][c] -= k;
                        }
                        for (int i = 0; i < list.size(); i++) {
                            dfs(list.get(i).row, list.get(i).col, 1, map);
                        }
                        map[r][c] += k;
                    }
                }
            }
            System.out.println("#" + testCase + " " + MAX);
        }
    }
 
    static void dfs(int row, int col, int cnt, int[][] newMap) {
        for (int i = 0; i < 4; i++) {
            int nr = row + dr[i];
            int nc = col + dc[i];
            if (canConnect(nr, nc) && newMap[row][col] > newMap[nr][nc]) {
                dfs(nr, nc, cnt + 1, newMap);
            }
        }
        if (MAX < cnt) {
            MAX = cnt;
        }
    }
 
    static boolean canConnect(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }
 
    
}
