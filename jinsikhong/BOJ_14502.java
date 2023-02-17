package 최단경로;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_14502 {
    static int map[][];
    static int n;
    static int m;
    static ArrayList<Node> virus;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int max = Integer.MIN_VALUE;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        map = new int[n][m];
        virus = new ArrayList<>();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                map[i][j] = sc.nextInt();
                if(map[i][j] == 2){
                    virus.add(new Node(i, j));
                }
            }
        } //데이터 받기
        perm(0);
        System.out.println(max);

    }

    public static void perm(int cnt){
        if(cnt == 3){
            bfs();
            return;
        }
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(map[i][j] == 0){
                    map[i][j] = 1; //벽을 세우기(순열을 그대로 맵에다 적용)
                    perm(cnt+1);
                    map[i][j] = 0;
                }
            }
        }
    }

    public static void bfs(){
        Queue<Node> q = new LinkedList<>();
        int copymap[][] = new int[n][m];
        for(int j = 0; j < n; j++){
            for(int k = 0; k < m; k++){
                copymap[j][k] = map[j][k];
            }
        }
        for(int i = 0; i < virus.size(); i++){
            q.add(virus.get(i));

            while(!q.isEmpty()){
                Node no = q.poll();
                int x = no.x;
                int y = no.y;

                for(int z = 0; z < 4; z++){
                    int nx = x + dx[z];
                    int ny = y + dy[z];
                    if(0 <= nx && nx < n && 0 <= ny && ny < m){
                        if(copymap[nx][ny] == 0){
                            q.add(new Node(nx, ny));
                            copymap[nx][ny] = 2;
                        }
                    }
                }
            }
        }
        solve(copymap);
    }
    public static void solve(int[][] copy){
        int sum = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(copy[i][j] == 0){
                    sum++;
                }
            }
        }
        if(max < sum){
            max = sum;
        }
    }

}
class Node{
    int x;
    int y;

    public Node(int x, int y){
        this.x = x;
        this.y = y;
    }
}