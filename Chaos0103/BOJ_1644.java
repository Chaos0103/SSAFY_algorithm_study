import java.io.*;
import java.util.*;

public class Main {
	
	private static boolean[] arr;
	
	public static void main(String args[]) {
		FastReader fr = new FastReader();

		int n = fr.nextInt();
		arr = new boolean[n + 1];
		prime(n);
		List<Integer> primeNumber = new ArrayList<>();
		for(int i = 2; i <= n; i++) {
			if (arr[i]) {
				primeNumber.add(i);
			}
		}
		
		int end = 0;
		int intervalSum = 0;
		int result = 0;
		for(int start = 0; start < primeNumber.size(); start++) {
			while (intervalSum < n && end < primeNumber.size()) {
				intervalSum += primeNumber.get(end);
				end++;
			}
			if (intervalSum == n) {
				result++;
			}
			intervalSum -= primeNumber.get(start);
		}
		
		System.out.println(result);
	}
	
	private static void prime(int n) {
		Arrays.fill(arr, true);
		for(int i = 2; i <= Math.sqrt(n); i++) {
			if (arr[i]) {
				int j = 2;
				while (i * j <= n) {
					arr[i*j] = false;
					j++;
				}
			}
		}
	}
	
    public static class FastReader {
        BufferedReader br;
        StringTokenizer st;
        public FastReader() { br = new BufferedReader(new InputStreamReader(System.in)); }
        public FastReader(String s) throws FileNotFoundException { br = new BufferedReader(new FileReader(new File(s))); }
        String next() {
            while (st == null || !st.hasMoreElements()) {
                try { st = new StringTokenizer(br.readLine()); }
                catch (IOException e) { e.printStackTrace(); }
            }
            return st.nextToken();
        }
        int nextInt() { return Integer.parseInt(next()); }
        long nextLong() { return Long.parseLong(next()); }
        double nextDouble() { return Double.parseDouble(next()); }
        String nextLine() {
            String str = "";
            try { str = br.readLine(); }
            catch (IOException e) { e.printStackTrace(); }
            return str;
        }
    }
}
