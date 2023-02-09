import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        FastReader fr = new FastReader();
        
        int n = fr.nextInt();
        int d = fr.nextInt();
        int k = fr.nextInt();
        int c = fr.nextInt();
        
        int[] arr = new int[n];
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0; i<n; i++) {
        	arr[i] = fr.nextInt();
        }
        
        for(int i=0;i<n+k;i++) {
        	list.add(arr[i % n]);
        }
        
        Set<Integer> s = new HashSet<>();
        ArrayList<Integer> l = new ArrayList<>();
        
        int left = 0;
        int right = k - 1;
        for(int i = 0; i <= right; i++) {
        	s.add(list.get(i));
        	l.add(list.get(i));
        }
        
        int result = 0;
        while (right != list.size() - 1) {
        	if (s.contains(c)) {
        		result = Math.max(result, s.size());
        	} else {
        		result = Math.max(result, s.size() + 1);
        	}
        	
        	left++;
        	right++;
        	l.add(list.get(right));
        	l.remove(0);
        	s = new HashSet<>(l);
        }
        //리스트1
        //k개 담는 리스트
        //중복 set
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

