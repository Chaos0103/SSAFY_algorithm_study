import java.util.*;

class Combination {
    private int n;
    private int r;
    private int[] now; // 현재 조합
    private ArrayList<ArrayList<Character>> result; // 모든 조합

    public ArrayList<ArrayList<Character>> getResult() {
        return result;
    }

    public Combination(int n, int r) { //전체 갯수, 뽑을 갯수
        this.n = n;
        this.r = r;
        this.now = new int[r];
        this.result = new ArrayList<ArrayList<Character>>();
    }

    public void combination(ArrayList<Character> arr, int depth, int index, int target) {
        if (depth == r) {
            ArrayList<Character> temp = new ArrayList<>();
            for (int i = 0; i < now.length; i++) {
                temp.add(arr.get(now[i]));
            }
            result.add(temp);
            return;
        }
        if (target == n) return;
        now[index] = target;
        combination(arr, depth + 1, index + 1, target + 1);
        combination(arr, depth, index, target + 1);
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        char[] vowels = {'a', 'e', 'i', 'o', 'u'};

        int l = sc.nextInt();
        int c = sc.nextInt();
        ArrayList<Character> data = new ArrayList<>();
        for (int i = 0; i < c; i++) {
            char ch = sc.next().charAt(0);
            data.add(ch);
        }

        Collections.sort(data);

        Combination combination = new Combination(c, l);
        combination.combination(data, 0, 0, 0);
        for (ArrayList<Character> password : combination.getResult()) {
            int count = 0;
            for (char ch : password) {
                for (char vowel : vowels) {
                    if (ch == vowel) {
                        count++;
                    }
                }
            }
            if (count >= 1 && count <= l - 2) {
                for (Character ch : password) {
                    System.out.print(ch);
                }
                System.out.println();
            }
        }
    }
}
