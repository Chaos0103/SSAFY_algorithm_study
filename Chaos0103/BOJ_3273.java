import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        FastReader fr = new FastReader();
        
        int n = fr.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;i++) {
        	arr[i] = fr.nextInt();
        }
        int target = fr.nextInt();
        
        Arrays.sort(arr);
        
        int left = 0;
        int right = n - 1;
        int count = 0;
        while (left < right) {
        	int sum = arr[left] + arr[right];
        	if (sum == target) {
        		count++;
        		left++;
        	} else if (sum > target) {
        		right--;
        	} else {
        		left++;
        	}
        }
        
        System.out.println(count);
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
