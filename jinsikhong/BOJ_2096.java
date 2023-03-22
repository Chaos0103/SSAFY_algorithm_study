package Bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2096 {
    static int n;
    static int[] dx = {-1, 1};
    static int[][] map;
    static int[][] dpMax;
    static int[][] dpMin;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][3];
        dpMax = new int[n][3];
        dpMin = new int[n][3];
        StringTokenizer st;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i = 0; i < 3; i++){
            dpMax[0][i] = map[0][i];
            dpMin[0][i] = map[0][i];
        }

        for(int i = 1; i < n; i++){
            for(int j = 0; j < 3; j++){
                dpMax[i][j] = dpMax[i-1][j] + map[i][j];
                dpMin[i][j] = dpMin[i-1][j] + map[i][j];

                for(int k = 0; k < 2; k++){
                    int nx = j + dx[k];
                    if(nx < 0 || nx >= 3){
                        continue;
                    }
                    dpMin[i][j] = Math.min(dpMin[i][j], dpMin[i-1][nx] + map[i][j]);
                    dpMax[i][j] = Math.max(dpMax[i][j], dpMax[i-1][nx] + map[i][j]);
                }
            }
        }
        int maxresult = Integer.MIN_VALUE;
        int minresult = Integer.MAX_VALUE;
        for(int i = 0; i < 3; i++){
            maxresult = Math.max(maxresult, dpMax[n-1][i]);
            minresult = Math.min(minresult, dpMin[n-1][i]);
        }


        System.out.print(maxresult + " ");
        System.out.println(minresult);

    }

}
