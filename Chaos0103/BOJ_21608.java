

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class Student {
    private int x;
    private int y;
    private int[] flist;

    public Student(int x, int y, int[] flist) {
        this.x = x;
        this.y = y;
        this.flist = flist;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int[] getFlist() {
        return flist;
    }
}

public class Main {

    private static int[][] classroom, nearEmptySeatCnt;
    private static int N;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    private static Map<Integer, Student> list = new HashMap<>();

    public static void main(String[] args) throws NumberFormatException, IOException {
        FastReader fr = new FastReader();

        N = fr.nextInt();
        int N2 = N * N;
        int result = 0;
        classroom = new int[N][N];
        fillNearEmptySeat();

        for (int i = 0; i < N2; i++) {
            int num = fr.nextInt();
            int s1 = fr.nextInt();
            int s2 = fr.nextInt();
            int s3 = fr.nextInt();
            int s4 = fr.nextInt();

            findSeat(num, new int[]{s1, s2, s3, s4});
        }

        for (int i = 1; i <= N2; i++) {
            Student student = list.get(i);
            int count = 0;
            for (int friend : student.getFlist()) {
                if (Math.abs(list.get(friend).getX() - student.getX()) + Math.abs(list.get(friend).getY() - student.getY()) == 1) {
                    count++;
                }
            }

            if (count == 1) {
                result += 1;
            } else if (count == 2) {
                result += 10;
            } else if (count == 3){
                result += 100;
            } else if (count == 4) {
                result += 1000;
            }
        }

        System.out.println(result);

    }

    private static void findSeat(int num, int[] friends) {
        int[][] nearScore = new int[N][N]; //주변에 친한 친구가 많은 위치를 찾기 위한 배열
        for (int friend : friends) {
            if (list.containsKey(friend)) {
                Student student = list.get(friend);
                int x = student.getX();
                int y = student.getY();

                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if (nx >= 0 && nx < N && ny >= 0 && ny < N && classroom[nx][ny] == 0) {
                        nearScore[nx][ny]++;
                    }
                }
            }
        }

        int emptyCntMax = -1;
        int nearScoreMax = -1;
        int choiceX = -1;
        int choiceY = -1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (classroom[i][j] != 0) continue;
                if (nearScoreMax < nearScore[i][j]) {
                    choiceX = i;
                    choiceY = j;
                    nearScoreMax = nearScore[i][j];
                    emptyCntMax = nearEmptySeatCnt[i][j];
                } else if (nearScoreMax == nearScore[i][j] && emptyCntMax < nearEmptySeatCnt[i][j]) {
                    emptyCntMax = nearEmptySeatCnt[i][j];
                    choiceX = i;
                    choiceY = j;
                }
            }
        }

        classroom[choiceX][choiceY] = num;
        list.put(num, new Student(choiceX, choiceY, friends));

        for (int i = 0; i < 4; i++) {
            int nx = choiceX + dx[i];
            int ny = choiceY + dy[i];
            if (nx >= 0 && nx < N && ny >= 0 && ny < N && classroom[nx][ny] == 0) {
                nearEmptySeatCnt[nx][ny]--;
            }
        }
    }

    private static void fillNearEmptySeat() {
        nearEmptySeatCnt = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int cnt = 4;
                if (i == 0 || i == N - 1) {
                    cnt--;
                }
                if (j == 0 || j == N - 1) {
                    cnt--;
                }
                nearEmptySeatCnt[i][j] = cnt;
            }
        }
    }

    public static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String s) throws FileNotFoundException {
            br = new BufferedReader(new FileReader(new File(s)));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
