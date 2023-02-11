import java.io.*;
import java.util.*;

class Node implements Comparable<Node>{

	private int start;
	private int end;
	
	public Node(int start, int end) {
		this.start = start;
		this.end = end;
	}

	public int getStart() {
		return start;
	}

	public int getEnd() {
		return end;
	}

	@Override
	public int compareTo(Node other) {
		if (this.start < other.start) {
			return -1;
		} else if (this.start == other.start) {
			if (this.end < other.end) {
				return -1;
			}
		}
		return 1;
	}
}
public class Main {
	
    public static void main(String[] args) {
        FastReader fr = new FastReader();
        
        int n = fr.nextInt();
        
        List<Node> list = new ArrayList<>();
        for(int i=0; i<n; i++) {
        	int s = fr.nextInt();
        	int e = fr.nextInt();
        	list.add(new Node(s, e));
        }
        
        Collections.sort(list);
        
        int result = 1;
        Node node = list.get(0);
        for(int i=1; i<n; i++) {
        	Node now = list.get(i);
        	if (node.getEnd() <= now.getStart()) {
        		node = now;
        		result++;
        		continue;
        	}
        	if (node.getEnd() > now.getEnd()) {
        		node = now;
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

