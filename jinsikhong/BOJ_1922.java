package BJ;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BOJ_1922 {
	static int[] parent;
	static int m;
	static ArrayList<Node> arr = new ArrayList<>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		parent = new int[n+1];
		for(int i = 1; i <= n; i++) {
			parent[i] = i;
		}
		
		
		for(int i = 0; i < m; i++) {
			int a = sc.nextInt();
	        int b = sc.nextInt();
	        int d = sc.nextInt();
	        arr.add(new Node(a,b,d));
		}
		
		Collections.sort(arr);
        int result = mst();
        System.out.println(result);
		
	}
	public static int mst(){
        int sum = 0;
        for(int i = 0; i < arr.size(); i++){
            Node no = arr.get(i);
            int a = no.a;
            int b = no.b;
            int distance = no.distance;
            if(findparent(a) != findparent(b)){
                unionparent(a, b);
                sum += distance;

            }
        }
        return sum;
    }
	
	
    public static int findparent(int x){
        if(x != parent[x])
            parent[x] = findparent(parent[x]);
        return parent[x];
    }
    
    public static void unionparent(int a, int b){
        a = parent[a];
        b = parent[b];
        if(a>b){
            parent[a] = b;
        }else{
            parent[b] = a;
        }
    }
}

class Node implements Comparable<Node>{
    int a;
    int b;
    int distance;

    public Node(int a, int b, int distance){
        this.a = a;
        this.b = b;
        this.distance = distance;

    }

    @Override
    public int compareTo(Node o) {
        return this.distance - o.distance;
    }
}
