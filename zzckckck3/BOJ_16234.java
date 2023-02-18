package BOJ__17;

import java.util.*;
import java.io.*;

public class BOJ_16234 {
	public static class Country{
         int row; 
         int col;

         public Country(int row, int col) {
             this.row = row;
             this.col = col;
         }
         
     }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int L = Integer.parseInt(str[1]);
        int R = Integer.parseInt(str[2]);


        int[][] grid = new int[N][N];

        for (int i = 0; i < N; i++) {
            String[] temp = br.readLine().split(" ");
            for (int j = 0; j < temp.length; j++) {
                grid[i][j] = Integer.parseInt(temp[j]);
            }
        }

        Queue<Country> queue = new LinkedList<>();
        int[] moveRow = {-1, 1, 0, 0};
        int[] moveCol = {0, 0, -1, 1};
        int result = 0;


        while (true) {
            int[][] chk = new int[N][N];
            boolean flag = false;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    ArrayList<Country> list = new ArrayList<>();
                    int sum = 0;
                    if (chk[i][j] == 0) {
                        queue.offer(new Country(i, j));
                        list.add(new Country(i, j));
                        sum += grid[i][j];
                        chk[i][j] = 1;
                    } else {
                        continue;
                    }

                    while (!queue.isEmpty()) {
                        Country country = queue.poll();

                        for (int k = 0; k < 4; k++) {
                            int nextRow = country.row + moveRow[k];
                            int nextCol = country.col + moveCol[k];

                            if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N || chk[nextRow][nextCol] == 1) {
                                continue;
                            }

                            int diff = Math.abs(grid[country.row][country.col] - grid[nextRow][nextCol]);
                            if( diff < L || diff > R){
                                continue;
                            }

                            queue.offer(new Country(nextRow, nextCol));
                            list.add(new Country(nextRow, nextCol));
                            sum += grid[nextRow][nextCol];
                            chk[nextRow][nextCol] = 1;
                        }
                    }

                    if (list.size() >= 2) {
                        int population = sum / list.size();
                        for (Country country : list) {
                            grid[country.row][country.col] = population;
                        }
                        flag = true;
                    }
                }
            }

            if (flag) {
                result++;
            } else {
                System.out.println(result);
                return;
            }
        }
    }


}