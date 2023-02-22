import java.util.*;

class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int n = sc.nextInt();
            int k = sc.nextInt();
            int size = n >> 2;

            char[] hex = new char[n + size];
            String str = sc.next();
            for (int i = 0; i < n; i++) {
                hex[i] = str.charAt(i);
            }
            for (int i = 0; i < size; i++) {
                hex[i + n] = str.charAt(i);
            }

            Set<String> set = new HashSet<>();
            for (int a = 0; a < size; a++) {
                for (int i = 0; i < 4; i++) {
                    StringBuilder sb = new StringBuilder();
                    for (int j = 0; j < size; j++) {
                        sb.append(hex[a + i * size + j]);
                    }
                    set.add(String.valueOf(sb));
                }
            }
            ArrayList<Integer> list = new ArrayList<>();
            for (String h : set) {
                int num = Integer.parseInt(h, 16);
                list.add(num);
            }

            list.sort(Collections.reverseOrder());

            System.out.printf("#%d %d\n", test_case, list.get(k - 1));
        }
    }
}
