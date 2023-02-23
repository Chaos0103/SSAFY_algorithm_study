package BOJ_21608;

import java.io.*;
import java.util.*;

/**
 * 캐슬을 너무 열심히 지킨 나머지
 * 지쳐버리고 시간도 많이 늦어서
 * 제대로 시도하지 못했습니다.
 * 죄송합니당
 */
public class Main {
    public static int N, W, H;
    public static int[][] map;
    public static int[][] tmpMap;

    public static void bfs(int r, int c, int range) {
        if (range <= 1) {
            tmpMap[r][c] = 0;
            return;
        }

        System.out.println("r" + r);
        System.out.println("c" + c);
        System.out.println("range" + range);
    }

    public static void shoot(int startC) {
        for (int i = 0; i < H; i++) {
            if (tmpMap[i][startC] != 0) {
                bfs(i, startC, tmpMap[i][startC]);
                print(startC);
                return;
            }
        }

    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int tc = sc.nextInt();

        for (int t = 1; t <= tc; t++) {
            N = sc.nextInt();
            W = sc.nextInt();
            H = sc.nextInt();

            map = new int[H][W];

            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    map[i][j] = sc.nextInt();
                }
            }

            tmpMap = new int[H][W];
            for (int i = 0; i < W; i++) {
                for (int j = 0; j < W; j++) {
                    tmpMap[i] = map[i].clone();
                }
            }
            for (int i = 0; i < W; i++) {
                shoot(i);
            }
        }
    }

    static void print(int c) {
        System.out.println("컬럼 : " + c + "일 떄");
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                System.out.print(tmpMap[i][j] + " ");
            }
            System.out.println();
        }
    }
}
