import java.io.*;
import java.util.*;

public class Main {
	
    public static void main(String[] args) {
        FastReader fr = new FastReader();
        
        int n = fr.nextInt();
        int[] arr = new int[n];
        for(int i=0; i<n; i++) {
        	arr[i] = fr.nextInt();
        }
        
        Arrays.sort(arr);
        
        int result = 0;
        for(int i=0; i<n; i++) {
        	for(int j=0; j<=i; j++) {
        		result += arr[j];
        	}
        }
        
        System.out.println(result);
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

