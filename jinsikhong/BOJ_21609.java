package Bj;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_21609 {
    static int N;
    static int M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {0, 0, -1 ,1};
    static int[] dy= {1, -1, 0, 0};

    static int maxGroupCnt = Integer.MIN_VALUE;
    static ArrayList<Node> maxGroup = new ArrayList<>();

    static Node maxNode = null;
    static int maxRainbowCnt;
    static int score;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        } // 데이터 받기


        while(true){
            if(findGroup()){
                getScore();
//                System.out.println();
//                for(int[] arr : map) {
//                    System.out.println(Arrays.toString(arr));
//                }
//                System.out.println("scroe : " +  score);
                gravity();
//                System.out.println("after first graviry");
//                for(int[] arr : map) {
//                    System.out.println(Arrays.toString(arr));
//                }
                rotation();
//                System.out.println("after rotation");
//                for(int[] arr : map) {
//                    System.out.println(Arrays.toString(arr));
//                }
                gravity();
//                System.out.println("after second gravity");
//                for(int[] arr : map) {
//                    System.out.println(Arrays.toString(arr));
//                }
            }else{
                break;
            }

        }
        System.out.println(score);
//        findGroup();
//        getScore();
//        for(int[] arr : map){
//            System.out.println(Arrays.toString(arr));
//        }
//        gravity();
//        System.out.println();
//        for(int[] arr : map){
//            System.out.println(Arrays.toString(arr));
//        }
//        rotation();
//        System.out.println();
//        for(int[] arr : map){
//            System.out.println(Arrays.toString(arr));
//        }
//        gravity();
//        System.out.println();
//        for(int[] arr : map){
//            System.out.println(Arrays.toString(arr));
//        }
//        for(int i = 0; i < maxGroup.size(); i++){
//            System.out.print("[" + maxGroup.get(i).x + " , " + maxGroup.get(i).y + "]" + " , ");
//        }
    }
    // 크기가 가장 큰 블록 그룹을 찾는다. 그러한 블록 그룹이 여러 개라면 포함된 무지개 블록의 수가 가장 많은 블록 그룹,
    // 그러한 블록도 여러개라면 기준 블록의 행이 가장 큰 것을, 그 것도 여러개이면 열이 가장 큰 것을 찾는다.
    static boolean findGroup(){
        visited = new boolean[N][N];
        maxGroup = new ArrayList<>();
        maxGroupCnt = Integer.MIN_VALUE;
        maxNode = null;
        maxRainbowCnt = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
//                visited[i][j] = true;
                    if(!visited[i][j]){
                        bfs(new Node(i, j));
                    }
                    for(int k = 0; k < N; k++){
                        for(int z = 0; z < N; z++){
                            if(map[k][z] == 0){
                                visited[k][z] = false;
                            }
                        }
                    }
            }
        }
        if(maxGroupCnt == Integer.MIN_VALUE){
            return false;
        }
        return true;

    }

    static void bfs(Node node){
        Queue<Node> q = new ArrayDeque<>();
        q.offer(node);
        visited[node.x][node.y] = true;
        int color = map[node.x][node.y];
        if(color <= -1){ // 검은색 블록이면 리턴
            return;
        }
        ArrayList<Node> Group = new ArrayList<>(); // 그룹에 속하는 블록을 담을 리스트
        Group.add(node);
        int cnt = 1; // 그룹의 개수
        int rainbowCnt;// 레인보우 블록의 개수
        Node leaderNode;// 그룹 대표 idx(무지개 블록이 아닌 블록 중에서 행의 번호가 가장 작거나 열의 번호가 가장 작은)
        if(map[node.x][node.y] == 0){ // 시작 노드가 무지개색 노드면
            rainbowCnt = 1;
            leaderNode = null;
        }else{
            rainbowCnt = 0;
            leaderNode = node;
        }
        while(!q.isEmpty()){
            Node cur = q.poll();
            for(int i = 0; i < 4; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if(nx < 0 || nx >= N || ny < 0 || ny >= N){
                    continue;
                }
                if(visited[nx][ny]){
                    continue;
                }
                if(map[nx][ny] <= -1){
                    continue;
                }
                if(map[nx][ny] == color || map[nx][ny] == 0){
                    if(map[nx][ny] == 0){ // 무지개색 블록이면
                        rainbowCnt++;
                    }else{ // 무지개색이 아니면
                        if(leaderNode == null){ // 리더 노드가 null이면(첫 시작 노드가 무자개색 노드면)
                            leaderNode = new Node(nx, ny);
                        }else{// 리더 노드가 존재하면
                            if(leaderNode.x > nx){ // 행이 더 작으면 새로운 리더 갱신
                                leaderNode = new Node(nx, ny);
                            }else if(leaderNode.x == nx){ // 둘의 행이 같으면
                                if(leaderNode.y > ny){ // 열이 더 작으면 새로운 리더 갱신
                                    leaderNode = new Node(nx, ny);
                                }
                            }
                        }
                    }
                    Group.add(new Node(nx,ny));
                    q.offer(new Node(nx, ny));
                    visited[nx][ny] = true;
                    cnt++;
                }
            }
        }
        if(cnt < 2){
            return;
        }
        if(cnt - rainbowCnt < 1){
            return;
        }
        if(maxGroupCnt < cnt){
            maxGroup = Group;
            maxGroupCnt = cnt;
            maxRainbowCnt = rainbowCnt;
            maxNode = leaderNode;
        }else if(maxGroupCnt == cnt){ // 같을 경우
            boolean flag = false;
            if(maxRainbowCnt < rainbowCnt){ // 새로운 그룹의 무지개 블록이 더 많은 경우
                flag = true;
            }else if(maxRainbowCnt == rainbowCnt){ // 무지개 개수가 같을 경우
                if(leaderNode != null && maxNode != null){
                    if(maxNode.x < leaderNode.x){ // 행이 더 큰 경우
                        flag = true;
                    }else if(maxNode.x == leaderNode.x){ // 행 까지 같은 경우
                        if(maxNode.y < leaderNode.y){
                            flag = true;
                        }
                    }
                }
            }
            if(flag){
                maxGroup = Group;
                maxGroupCnt = cnt;
                maxRainbowCnt = rainbowCnt;
                maxNode = leaderNode;
            }
        }
        return;
    }

    static void getScore(){
        score +=  Math.pow(maxGroup.size(), 2);
        for(int i = 0; i < maxGroup.size(); i++){
            int x = maxGroup.get(i).x;
            int y = maxGroup.get(i).y;
            map[x][y] = -2;
        }
    }

    static void gravity(){
        for(int i = 0; i < N; ++i)
        {
            for(int j = N-1; j >= 0; --j)
            {
                if(map[j][i] == -2 || map[j][i] == -1) continue;
                int dest = j+1;
                while(true)
                {
                    if(dest == N) break;
                    if(map[dest][i] == -2) dest++;
                    else break;
                }
                if(dest == j+1) continue;
                map[dest-1][i] = map[j][i];
                map[j][i] = -2;
            }
        }


//        for(int i = 0; i < N; i++){
//            Stack<Integer> s = new Stack<>();
//            boolean meetEnd = false;
//            for(int j = 0; j < N; j++){
//                if(map[j][i] == -1 && !meetEnd) { // 경계가 아직 시작 안했는데 -1을 만나면 -> stack 비우기
//                    int idx = j-1;
//                    while(!s.isEmpty()) {
//                        map[idx--][i] = s.pop();
//                    }
//                }
//                else if(map[j][i] >= 0 && !meetEnd) { //경계가 시작 전 이면 넣기.
//                    s.push(map[j][i]);
//                    map[j][i] = -2;
//                }else if(map[j][i] == -2 && !meetEnd){ // 빈칸이 시작 되는 지점
//                    meetEnd = true;
//                }else if(map[j][i] > -2 && meetEnd){ //빈칸이 종료 된다면
//                    int idx = j-1;
//                    if(s.isEmpty()){
//                        if(map[j][i] != -1){
//                            s.push(map[j][i]);
//                            map[j][i] = -2;
//                        }
//                    }else{
//                        while(!s.isEmpty()) {
//                            map[idx--][i] = s.pop();
//                        }
//                    }
//                    meetEnd = false; // 다시 경계 시작 전으로 바꿔주기
//                    }
//                }
//            int idx = N -1;
//            while(!s.isEmpty()){
//                map[idx--][i] = s.pop();
//            }
//        }
    }
    static void rotation(){
        Queue<Integer> q = new ArrayDeque<>();
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                q.offer(map[i][j]);
            }
        }
        for(int j = 0; j < N; j++){
            for(int i = N-1; i >= 0; i--){
                map[i][j] = q.poll();
            }
        }
    }



    static class Node{
        int x, y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

}
