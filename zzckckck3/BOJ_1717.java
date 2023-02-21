package BOJ__17;

import java.io.*;
import java.util.*;
 
public class BOJ_1717 {
    public static int [] parent;
    
    public static int find(int x) {
        if(x == parent[x]) 
            return x;
        return parent[x] = find(parent[x]);
    }
    
    public static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if(x != y) {
            parent[y] = x;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        parent = new int[n + 1];
        for(int i = 0; i <= n; i++) {
            parent[i] = i; //parent 초기화
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(reader.readLine());
            int o = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            if(o == 0) {
                union(x,y);
            } else {
                if(find(x) == find(y)) {
                    sb.append("YES\n");
                } else {
                    sb.append("NO\n");
                }
            }
        }
        System.out.print(sb.toString());
    }
}