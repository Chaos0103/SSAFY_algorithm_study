package Bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14500 {
    static int[][] map;
    static boolean[][] visited;
    static int m;
    static int n;

    static int max = Integer.MIN_VALUE;

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j< m; j++){
                map[i][j]= Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                visited[i][j] = true;
                dfs(i,j,map[i][j],1);
                visited[i][j] = false;
            }
        }
        System.out.println(max);

    }

    static void dfs(int x, int y, int sum, int cnt){
        if(cnt == 4){
            max = Math.max(max, sum);
            return;
        }

        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx >= 0 && nx < n && ny >= 0 && ny < m){
                if(!visited[nx][ny]){

                    //solution code
                    // 보라색 ㅗ 테트리스를 만들기 위해 2번째 칸에서 탐색
                    if(cnt == 2){
                        visited[nx][ny] = true;
                        dfs(x,y, sum+map[nx][ny], cnt + 1);
                        visited[nx][ny] = false;
                    }
                    visited[nx][ny] = true;
                    dfs(nx,ny, sum+map[nx][ny], cnt + 1);
                    visited[nx][ny] = false;
                }
            }

        }
    }
}
