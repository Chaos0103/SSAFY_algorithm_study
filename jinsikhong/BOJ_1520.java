package Bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1520 {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1,0,-1}; // 아래 우 위 좌
    static int M;
    static int N;
    static int map[][];
    static int dp[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        dp = new int[M][N];
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j <N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }
        System.out.println(dfs(new Node(0,0)));
//4방향 탐색인걸 생각 못하고 짠 내 dp ..

//        for(int i = 0; i < M; i++){
//            for(int j = 0; j < N; j++){
//                for(int k = 0; k < 3; k++){
//                    int nx = i + dx[k];
//                    int ny = j + dy[k];
//                    if(nx < 0 || nx >= M || ny < 0 || ny >= N){
//                        continue;
//                    }
//                    if(k == 2){ // 왼쪽에서 오는거면
//                        if(map[nx][ny] < map[i][j]){
//                            dp[nx][ny] += dp[i][j];
//                        }
//                    }
//                    if(map[nx][ny] > map[i][j]){ // 더 낮은 경우
//                        dp[i][j] += dp[nx][ny];
//                    }
//
//                }
//            }
//        }
    }

    static class Node{
        int x, y;
        public Node(int x, int y){
            this.x =x;
            this.y =y;
        }
    }
    static int dfs(Node node){
        if(node.x == M-1 && node.y == N-1) // 끝 점 도달
            return 1;
        if (dp[node.x][node.y] != -1) {
            return dp[node.x][node.y]; // 이미 구해진 경로라면 return
        }
        dp[node.x][node.y] = 0; // 현위치에서 끝점까지 도달하는 경로 개수 0개로 초기화 ?
        for(int i = 0; i < 4; i++){
            int nx = node.x + dx[i];
            int ny = node.y + dy[i];
            if(nx < 0 || nx >= M || ny < 0 || ny >= N){
                continue;
            }
            if(map[nx][ny] < map[node.x][node.y]){
                dp[node.x][node.y] += dfs(new Node(nx, ny));
            }
        }
        return dp[node.x][node.y];

    }
}
