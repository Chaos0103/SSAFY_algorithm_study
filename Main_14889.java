import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_14889{
	private static int N;
	private static int [][] arr = new int[21][21];
	private static boolean [] selected = new boolean[21];
	private static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
	
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0,0);
		
		System.out.println(min);
	}
	
	public static void dfs(int depth, int a) {
		
		if(depth == N/2) {
			check_diff();
			return;
		}
		
		for(int i = a ; i < N ; i++) {
			selected[i]=true;
			dfs(depth+1, i+1);
			selected[i]=false;
		}
	
	}
	
	public static void check_diff() {
		int start = 0;
		int link = 0;
		for(int i = 0 ; i < N-1 ; i++) {
			for(int j = i+1 ; j < N ; j++) {
				if(selected[i]==true && selected[j]==true) {
					start += arr[i][j];
					start += arr[j][i];
				}
				else if(selected[i]==false && selected[j]==false) {
					link += arr[i][j];
					link += arr[j][i];
				}
				
			}
		}
		
		int val = Math.abs(start - link);
		
		if(val == 0) {
			System.out.println(val);
			System.exit(0);
		}
		
		min=Math.min(min,val);
	}

}