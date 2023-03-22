package Bj;

import org.openjsse.sun.net.util.IPAddressUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3055 {
    static int R,C;
    static char[][] map;
    static boolean[][] visited;
    static int[][] waterMap;
    static int[][] gosumMap;
    static ArrayList<Water> flood;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[R][C];
        waterMap = new int[R][C];
        gosumMap = new int[R][C];
        flood = new ArrayList<>();
        int startX = 0;
        int startY = 0;
        int waterX = 0;
        int waterY = 0;
        for(int i = 0; i < R; i++){
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            for(int j = 0; j < C; j++){
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'S'){
                    startX = i;
                    startY = j;
                }
                if(map[i][j] == '*'){
//                    System.out.println(i + " " + j);
                    flood.add(new Water(i,j,0));
                }
            }
        }
        bfsFlood();
//        printwater();
        visited = new boolean[R][C];
        int result = bfsGosum(startX, startY);
//        System.out.println("_---------");
//        printgosum();
        if(result == 0){
            System.out.println("KAKTUS");
        }else{
            System.out.println(result);
        }



    }

    static void printwater(){
        for(int[] arr : waterMap){
            for(int x : arr){
                System.out.print(x + " ");
            }
            System.out.println();
        }
    }

    static void printgosum(){
        for(int[] arr : gosumMap){
            for(int x : arr){
                System.out.print(x + " ");
            }
            System.out.println();
        }
    }
    static void bfsFlood(){
        Queue<Water> q = new ArrayDeque<>();
        for(int i = 0; i < flood.size(); i++){
            Water now = flood.get(i);
            q.offer(flood.get(i));
            visited[now.x][now.y] = true;
        }
        while(!q.isEmpty()){
            Water cur = q.poll();
            for(int i = 0; i < 4; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if(nx < 0 || nx >= R || ny < 0 || ny >= C){
                    continue;
                }
                if(visited[nx][ny]) {
                    continue;
                }
                if(map[nx][ny] == '.' || map[nx][ny] == 'S'){
                    waterMap[nx][ny] = cur.time + 1;
                    visited[nx][ny] = true;
                    q.offer(new Water(nx, ny, cur.time+1));
                }
            }
        }
    }

    static int bfsGosum(int x, int y){
        Queue<Gosum> q = new ArrayDeque<>();
        q.offer(new Gosum(x, y, 0));
        visited[x][y] = true;
        while(!q.isEmpty()){
            Gosum cur = q.poll();
            if(map[cur.x][cur.y] == 'D'){
                return cur.time;
            }
            for(int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 0 || nx >= R || ny < 0 || ny >= C) {
                    continue;
                }
                if (visited[nx][ny]) {
                    continue;
                }
                if(map[nx][ny] == 'X'){
                    continue;
                }
//                if(waterMap[nx][ny] <= cur.time + 1){
//                    continue;
//                }
                if(waterMap[nx][ny] > cur.time + 1 || map[nx][ny] == 'D' || waterMap[nx][ny] == 0){
                    gosumMap[nx][ny] = cur.time + 1;
                    visited[nx][ny] = true;
                    q.offer(new Gosum(nx, ny, cur.time+1));
                }


            }
        }
        return 0;
    }

    static class Water{
        int x, y, time;
        public Water(int x, int y, int time){
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    static class Gosum{
        int x, y, time;
        public Gosum(int x, int y, int time){
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
}
