import java.io.*;
import java.util.*;

public class Main {

    private static int n, m;
    private static int[] parent = new int[51];
    private static ArrayList<Integer> know = new ArrayList<>();
    private static ArrayList<ArrayList<Integer>> party = new ArrayList<>();

    private static int findParent(int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = findParent(parent[x]);
    }

    private static void unionParent(int a, int b) {
        a = findParent(a);
        b = findParent(b);
        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }

    public static void main(String[] args) {
        FastReader fr = new FastReader();

        n = fr.nextInt();
        m = fr.nextInt();

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        int knowCount = fr.nextInt();
        for (int i = 0; i < knowCount; i++) {
            int num = fr.nextInt();
            know.add(num);
        }

        for (int i = 0; i < m; i++) {
            int cnt = fr.nextInt();

            ArrayList<Integer> temp = new ArrayList<>();
            for (int j = 0; j < cnt; j++) {
                int num = fr.nextInt();
                temp.add(num);
            }
            party.add(temp);

            for (int x = 0; x < cnt; x++) {
                for (int y = x + 1; y < cnt; y++) {
                    if (findParent(temp.get(x)) != findParent(temp.get(y))) {
                        unionParent(temp.get(x), temp.get(y));
                    }
                }
            }
        }

        int result = m;
        Set<Integer> set = new HashSet<>();
        for (int num : know) {
            set.add(findParent(num));
        }

        for (int i = 0; i < m; i++) {
            for (int num : party.get(i)) {
                if (set.contains(findParent(num))) {
                    result--;
                    break;
                }
            }
        }

        System.out.println(result);
    }

    public static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String s) throws FileNotFoundException {
            br = new BufferedReader(new FileReader(new File(s)));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
