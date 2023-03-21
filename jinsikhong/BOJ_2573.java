package Bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2573 {
    static int N, M;
    static int[][] map;
    static int[][] melting;
    static boolean[][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,1,-1};
    static int iceCnt = 0;
    static class Node{
        int x, y, h, idx;

        public Node(int x, int y, int h, int idx){
            this.x =x;
            this.y =y;
            this.h =h;
            this.idx = idx;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        melting = new int[N][M];
        visited = new boolean[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] > 0){
                    iceCnt++; // 얼음 개수 증가
                }
            }
        }
        int result = 0;
        while(true){
            if(iceCnt == 0){
                result = 0;
                break;
            }
//            System.out.println("23321");
            int startX = 0;
            int startY = 0;
            for(int i = 0; i < N; i++){
                for(int j = 0; j < M; j++){
                    if(map[i][j] > 0){
                        startX = i;
                        startY = j;
                        break;
                    }
                }
            }
            int ice = bfs(new Node(startX, startY, 0, 0));
//            System.out.println("????/");
            visited = new boolean[N][M];

            if(ice != iceCnt){  // 얼음 총 개수와 같지 않으면 return
                break;
            }
            melting = new int[N][M];
            melt(); // 녹이기
//            print();
//            System.out.println("------");
            result++;
        }
        System.out.println(result);


    }
    static void melt(){
//        ArrayList<Node> meltice = new ArrayList<>(); // h는 깎이는 높이
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(map[i][j] > 0){
                    int waterCnt = 0;
                    for(int z = 0; z < 4; z++) {
                        int nx = i + dx[z];
                        int ny = j + dy[z];
                        if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                            continue;
                        }
                        if(map[nx][ny] == 0) {
                            waterCnt++;
                        }
                    }
                    melting[i][j] = waterCnt;
                }
            }
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(melting[i][j] == 0){
                    continue;
                }
                map[i][j] = Math.max(0, map[i][j] - melting[i][j]);
                if(map[i][j] == 0){
                    iceCnt--;
                }
            }
        }
    }

    static int bfs(Node node){
        Queue<Node> q = new ArrayDeque<>();
        q.offer(node);
        visited[node.x][node.y] = true;
        int cnt = 1;
        while(!q.isEmpty()){

            Node cur = q.poll();
//            System.out.println(cur.x + " " + cur.y);
            for(int i = 0; i < 4; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if(nx < 0 || nx >= N || ny < 0 || ny >= M){
                    continue;
                }
                if(visited[nx][ny]){
                    continue;
                }
                visited[nx][ny] = true;
                if(map[nx][ny] > 0){
                    q.offer(new Node(nx, ny, cur.h, 0));
                    cnt++;
                }
            }
        }
        return cnt;
    }

    static void print(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j <M; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }


}
