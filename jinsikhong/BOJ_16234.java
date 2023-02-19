package Bj;

import java.util.*;

public class BOJ_16234 {

    static int n;
    static int l;
    static int r;
    static int[][] map;
    static boolean[][] visited;
    static int[][] map_open;
    static int move_cnt;

    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        l = sc.nextInt();
        r = sc.nextInt();
        map = new int[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        int result = 0;
        while(true){
            if(all_bfs() == 0){
                break;
            }else{
                result++;
            }
        }
        System.out.println(result);
    }

    public static int check() {
        int result = 0;
        while(true){
            boolean open_flag = false;
            map_open = new int[n][n];
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    for(int k = 0; k < 2; k++){
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if(nx >= 0 && nx < n && ny >= 0 && ny < n){
                            int gap = Math.abs(map[i][j] - map[nx][ny]);
                            if(gap >= l && gap <= r){
                                map_open[i][j] = 1;
                                map_open[nx][ny] = 1;
                                open_flag = true;
                            }
                        }
                    }
                }
            }
            if(open_flag){
                all_bfs();
                result++;
            }else{
                return result;
            }
        }
    }

    public static int all_bfs(){
        visited = new boolean[n][n];
        move_cnt = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(!visited[i][j]){
                    bfs(i, j);

                }
            }
        }
        for(int k = 0; k < n; k++){
            Arrays.fill(visited[k] ,false);
        }
        return move_cnt;
    }
    public static void bfs(int x, int y){
        //2중 방문 방문 했으면 bfs(x)
        visited[x][y] = true;
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x, y));
        ArrayList<Node> change = new ArrayList<>();
        change.add(new Node(x,y));
        int sum = map[x][y];
        while (!q.isEmpty()){
            Node no = q.poll();

            for(int i = 0; i < 4; i++){
                int nx = no.x + dx[i];
                int ny = no.y + dy[i];
                if(nx >= 0 && ny >= 0 && nx < n && ny < n){
//                    if(map_open[nx][ny] == 1){ //문이 열려있으면 -> 시간 복잡도 증가. 그냥 bfs 내에서 검사
                    if(!visited[nx][ny]){
                        int gap = Math.abs(map[no.x][no.y] - map[nx][ny]);
                        if(gap >= l && gap <= r){
                            q.add(new Node(nx ,ny));
                            sum+= map[nx][ny];
                            visited[nx][ny] = true;
                            change.add(new Node(nx, ny));
                            move_cnt++;
                        }
                    }
                }
            }
        }
        if(move_cnt > 0){
            int temp = sum / change.size();
//            System.out.println("sum :  "+  temp);
            for(int i = 0; i < change.size(); i++){
                int change_x = change.get(i).x;
                int change_y = change.get(i).y;
                map[change_x][change_y] = temp;
//                System.out.println(change_x + "   " + change_y);
            }
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
