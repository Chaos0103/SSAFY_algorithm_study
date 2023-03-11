import java.util.*;

public class None {

    static ArrayList<Integer>[] list;
    static int[] indegree;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();
        int m = scan.nextInt();

        list = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        indegree = new int[n + 1];
        for(int i = 0; i < m; i++) {
            int count = scan.nextInt();
            if(count == 0) continue;
            int s = scan.nextInt();
            for(int j = 0; j < count - 1; j++) {
                int e = scan.nextInt();
                indegree[e]++;
                list[s].add(e);
                s = e;
            }
        }

        ArrayList<Integer> result = topologySort(n);
        if(result.size() == n)  {
            for(int i = 0; i < result.size(); i++) {
                System.out.println(result.get(i));
            }
        } else {
            System.out.println("0");
        }
    }

    public static ArrayList<Integer> topologySort(int n) {
        Queue<Integer> q = new LinkedList<>();
        for(int i = 1; i <= n; i++) {
            if(indegree[i] == 0) {
                q.offer(i);
            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        while(!q.isEmpty()) {
            int current = q.poll();
            result.add(current);

            for(int i = 0; i < list[current].size(); i++) {
                int next = list[current].get(i);
                indegree[next]--;
                if(indegree[next] == 0) q.offer(next);
            }
        }
        return result;
    }
}
