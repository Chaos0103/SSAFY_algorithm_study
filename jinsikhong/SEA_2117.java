import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int K;
    static int[][] map;
    static int N;
    static int M;
    static int result;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            result = Integer.MIN_VALUE;
            int houseCnt = 0;
            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j] == 1){
                        houseCnt++;
                    }
                }
            }
            int houseMaxExpense = houseCnt * M;
            K = 1;
            int cost = K * K + (K - 1) * (K - 1);
            while(houseMaxExpense > cost){
//                System.out.println(houseMaxExpense + " " + cost);
                Solve();
                K++;
                cost = K * K + (K - 1) * (K - 1);

            }
            System.out.println("#" + tc + " " + result);
        }
    }
    static void Solve(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                getHouse(i , j);
            }
        }
    }

    static int getHouse(int x, int y){
        int expense = K * K + (K - 1) * (K - 1);
        int cnt = 0;

        for(int i = x - K - 1; i <= x + K - 1; i++){
            if(i < 0 || i >= N){
                continue;
            }
            for(int j = y - K - 1; j <= y + K - 1; j++){
                if(j < 0 || j >= N){
                    continue; // 범위가 벗어나면 continue
                }
                if(Math.abs(x - i) + Math.abs(y - j) >= K){
                    continue;
                }
                if(map[i][j] == 1){
                    cnt++;
                }
            }
        }
        int profit = cnt * M - expense;
        if(profit >= 0){
            result = Math.max(result, cnt);
        }
        return 0;
    }
}
