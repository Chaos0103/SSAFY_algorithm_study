package Bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16938 {
    static int N, L, R, X;
    static boolean[] visited;
    static int[] problem;
    static int result;
//    static int visited_cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

//        visited_cnt = 0;
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        visited = new boolean[N];
        problem = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            problem[i] = Integer.parseInt(st.nextToken());
        }
        subset(0,0);
        System.out.println(result);
    }

    static void subset(int cnt, int visited_cnt){
        if(cnt == N){
            if(visited_cnt < 2){ // 부분집합의 개수가 1 이하이면 return
                return;
            }
            solve();
            return;
        }
        visited[cnt] = true;
        subset(cnt + 1, visited_cnt + 1);
        visited[cnt] = false;
        subset(cnt+1, visited_cnt);
    }

    static void solve(){
        // 최대, 최소
        int sum = 0;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < N; i++){
            if(visited[i] == true){
                sum += problem[i];
                max = Math.max(max, problem[i]);
                min = Math.min(min, problem[i]);
            }
        }
        if((sum >= L && sum <= R) && (max - min >= X)){
            result++;
        }

    }
}
