import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    
    private static int[] dx = { 0, 1, 0, -1 };
    private static int[] dy = { -1, 0, 1, 0 };
    private static boolean[][] map = new boolean[101][101];
    static LinkedList<Integer>[] dragonCurves;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        dragonCurves = new LinkedList[11];

        dragonCurves[0] = new LinkedList<>();
        dragonCurves[0].offer(0);
        for(int i = 1; i < 11; i++) {
            dragonCurves[i] = new LinkedList<>();

            for(int index = dragonCurves[i - 1].size() - 1; index >= 0; index--) {
                int nowDirection = dragonCurves[i - 1].get(index);
                dragonCurves[i].offerFirst(nowDirection);

                if(nowDirection <= 0) {
                    nowDirection = 4;
                }
                dragonCurves[i].offerLast((nowDirection - 1) % 4);
            }
        }

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int direction = getSavedDragonCurvesDirection(Integer.parseInt(st.nextToken()));
            int g = Integer.parseInt(st.nextToken());

            map[y][x] = true;

            for(int j = 0; j < dragonCurves[g].size(); j++) {
                int nowDirection = dragonCurves[g].get(j);
                x += dx[(nowDirection + direction) % 4];
                y += dy[(nowDirection + direction) % 4];
                map[y][x] = true;
            }
        }

        int count = 0;
        for(int i = 0; i < 100; i++) {
            for(int j = 0; j < 100; j++) {
                if(map[i][j]) {
                    if(checkSquared(j, i)) {
                        count++;
                    }
                }

            }
        }

        System.out.println(count);
    }

    static int getSavedDragonCurvesDirection(int inputDirection) {
        switch (inputDirection) {
            case 0:
                return 1;
            case 1:
                return 0;
            case 2:
                return 3;
            case 3:
                return 2;
            default:
                return -1;
        }
    }

    static boolean checkSquared(int x, int y) {
        for(int i = 1; i < 4; i++) {
            x += dx[i];
            y += dy[i];
            if(!map[y][x]) {
                return false;
            }
        }
        return true;
    }
}
