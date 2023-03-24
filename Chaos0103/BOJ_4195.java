import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class Main {

    private static int[] parent;
    private static int[] count;
    private static HashMap<String, Integer> map;

    private static int findParent(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = findParent(parent[x]);
    }

    private static void unionParent(int a, int b) {
        a = findParent(a);
        b = findParent(b);
        if (a == b) {
            return;
        }
        parent[b] = a;
        count[a] += count[b];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int f = Integer.parseInt(br.readLine());
            parent = new int[f * 2];
            count = new int[f * 2];
            Arrays.fill(count, 1);

            map = new HashMap<>();
            int index = 0;
            for (int i = 0; i < f; i++) {
                String[] str = br.readLine().split(" ");
                if (!map.containsKey(str[0])) {
                    parent[index] = index;
                    map.put(str[0], index++);
                }
                if (!map.containsKey(str[1])) {
                    parent[index] = index;
                    map.put(str[1], index++);
                }

                unionParent(map.get(str[0]), map.get(str[1]));
                System.out.println(count[findParent(map.get(str[1]))]);
            }

        }
    }
}
