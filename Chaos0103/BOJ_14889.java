import java.util.*;

class Combination {
    private int n;
    private int r;
    private int[] now; // 현재 조합
    private ArrayList<ArrayList<Integer>> result; // 모든 조합

    public ArrayList<ArrayList<Integer>> getResult() {
        return result;
    }

    public Combination(int n, int r) { //전체 갯수, 뽑을 갯수
        this.n = n;
        this.r = r;
        this.now = new int[r];
        this.result = new ArrayList<ArrayList<Integer>>();
    }

    public void combination(ArrayList<Integer> arr, int depth, int index, int target) {
        if (depth == r) {
            ArrayList<Integer> temp = new ArrayList<>();
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
public class BOJ_14889 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int[][] data = new int[n][n];
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				data[i][j] = sc.nextInt();
			}
		}
		
		ArrayList<Integer> numbers = new ArrayList<>();
		for(int i=0;i<n;i++) {
			numbers.add(i);
		}
		Combination com = new Combination(n, n/2);
		com.combination(numbers, 0, 0, 0);
		ArrayList<ArrayList<Integer>> combinationResult = com.getResult();
		
		int result = Integer.MAX_VALUE;
		for(ArrayList<Integer> res : combinationResult) {
			int startTeam = 0;
			int linkTeam = 0;
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					if (res.contains(i) && res.contains(j)) {
						startTeam += data[i][j];
					} else if (!res.contains(i) && !res.contains(j)){
						linkTeam += data[i][j];
					}
				}
			}
			result = Math.min(result, Math.abs(startTeam - linkTeam));
		}
		System.out.println(result);
	}
}
