import java.io.*;
import java.util.*;

class Fireball {
    int r;
    int c;
    int m;
    int s;
    int d;

    public Fireball(int r, int c, int m, int s, int d) {
        this.r = r;
        this.c = c;
        this.m = m;
        this.s = s;
        this.d = d;
    }
}
public class Main {
    private static int n, m, k;
    private static ArrayList<Fireball>[][] map;
    private static ArrayList<Fireball> list = new ArrayList<>();
    private static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    private static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new ArrayList[n +1][n +1];
        for(int i = 0; i< n +1; i++) {
            for(int j = 0; j< n +1; j++) {
                map[i][j] = new ArrayList<>();
            }
        }
        for(int i = 0; i< m; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            map[r][c].add(new Fireball(r,c,m,s,d));
            list.add(new Fireball(r,c,m,s,d));
        }

        while(k-- > 0) {
            move();
            for(int i = 1; i< n +1; i++) {
                for(int j = 1; j< n +1; j++) {
                    if(map[i][j].size() >=2 ) {
                        merge(i,j);
                    }
                }
            }
            makeList();
        }

        int ans = 0;
        for(int i = 1; i< n +1; i++) {
            for(int j = 1; j< n +1; j++) {
                if(map[i][j].size() > 0) {
                    for(Fireball cur : map[i][j]) {
                        ans += cur.m;

                    }
                }
            }
        }

        System.out.println(ans);
    }

    public static void makeList(){
        list = new ArrayList<>();
        for(int i = 1; i< n +1; i++) {
            for(int j = 1; j< n +1; j++) {
                if(map[i][j].size() > 0){
                    for(Fireball cur : map[i][j]) {
                        list.add(cur);
                    }
                }
            }
        }
    }

    public static void move() {
        for(int i = 1; i< n +1; i++) {
            for(int j = 1; j< n +1; j++) {
                map[i][j] = new ArrayList<>();
            }
        }

        for(Fireball cur : list) {
            cur.r = cur.r+dx[cur.d]*(cur.s% n);
            cur.c = cur.c+dy[cur.d]*(cur.s% n);

            if(cur.r > n) cur.r %= n;
            if(cur.c > n) cur.c %= n;
            if(cur.r <= 0 ) cur.r = n -Math.abs(cur.r);
            if(cur.c <= 0) cur.c = n -Math.abs(cur.c);

            map[cur.r][cur.c].add(cur);
        }

    }

    public static void merge(int x, int y) {
        int sumM=0, sumS=0, sumCnt=0;
        boolean isEven = true;
        boolean isOdd = true;
        for(Fireball cur : map[x][y]) {
            sumM += cur.m;
            sumS += cur.s;
            sumCnt++;
            if(cur.d %2 ==0) {
                isOdd = false;
            }else {
                isEven = false;
            }
        }

        int M = sumM/5;
        int S = sumS/sumCnt;

        map[x][y] = new ArrayList<>();
        if(M <= 0) {
            return;
        }

        if(isEven || isOdd) {
            for(int i=0; i<4; i++) {
                map[x][y].add(new Fireball(x, y, M, S, i * 2));
            }
        }else {
            for(int i=0; i<4; i++) {
                map[x][y].add(new Fireball(x, y, M, S, i * 2 + 1));
            }
        }
    }
}
