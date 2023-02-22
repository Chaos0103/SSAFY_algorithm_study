package BJ;

import java.util.Scanner;

public class BOJ_1976 {
	static int[] parent;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		parent = new int[n + 1];
		for(int i = 1; i <= n; i++) {
			parent[i] = i;
		}
		
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				int con = sc.nextInt();
				if(con == 1) {
					unionparent(i, j);
				}
			}
		}
		
		int start = findparent(sc.nextInt());
		boolean flag = true;
		for(int i = 1; i < m; i++) {
			int trip = sc.nextInt();
			
			if(start != findparent(trip)) {
				flag = false;
				break;
			}
		}
		if(flag) {
			System.out.println("YES");
		}else {
			System.out.println("NO");
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
