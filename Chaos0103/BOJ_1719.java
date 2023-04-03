import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] matrix = new int[n+1][n+1];
        int[][] path = new int[n+1][n+1];
        for (int i = 1; i < n+1; i++) {
            Arrays.fill(matrix[i], 10001);
            matrix[i][i]=0;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());

            matrix[to][from] = val;
            matrix[from][to] = val;

            path[from][to] = from;
            path[to][from] = to;
        }

        for (int k = 1; k < n+1; k++) {         
            for (int i = 1; i < n+1; i++) {     
                for (int j = 1; j < n+1; j++) { 
                    if(matrix[i][j]>matrix[i][k]+matrix[k][j]) {
                        matrix[i][j] = matrix[i][k] + matrix[k][j];
                        path[i][j] = path[k][j];
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < n+1; j++) {
                if(i==j) sb.append("- ");
                else sb.append(path[j][i]+" ");
            }sb.append("\n");
        }

        System.out.println(sb.toString());
    }

}
