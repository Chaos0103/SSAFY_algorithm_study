package Bj;

import java.util.Scanner;

public class BOJ_1717 {

    static int[] parent;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        parent = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }
        for(int i = 0; i < m; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();

            if(a == 0){
                unionparent(b, c);
            }else{
                if(findparent(b) == findparent(c)){
                    System.out.println("YES");
                }else{
                    System.out.println("NO");
                }
            }
        }


    }

    public static int findparent(int x){
        if(x != parent[x])
            parent[x] = findparent(parent[x]);
        return parent[x];
    }

    public static void unionparent(int a, int b){
        a = findparent(a);
        b = findparent(b);

        if(a < b){
            parent[b] = a;
        }else{
            parent[a] = b;
        }
    }


}
