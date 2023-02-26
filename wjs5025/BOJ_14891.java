import java.io.*;
import java.util.*;

//회전한 톱니 기준으로 앞, 뒤 나눠서 로직짜기
// 1. 회전 톱니인덱스 -1이 0보다 클때 앞에꺼 회전하기
// 2. 회전 톱니인덱스 +1 이 4보다 작을때 뒤에꺼 회전하기

public class BOJ_14891 {
    static int K;
    static int[][] command;
    static int[][] wheels = new int[4][8];
    static boolean[] visited = new boolean[4];

    // 시계방향 회전
    public static void turnRight(int[] wheel, int wheelIdx) {
        visited[wheelIdx] = true;

        // System.out.println((wheelIdx + 1) + "번 바퀴 Right 회전");

        if (wheelIdx - 1 >= 0 && !visited[wheelIdx - 1]) {
            // 극이다르면 회전해야하므로,
            if (wheel[6] != wheels[wheelIdx - 1][2]) {
                turnLeft(wheels[wheelIdx - 1], wheelIdx - 1);
            }
        }
        if (wheelIdx + 1 < 4 && !visited[wheelIdx + 1]) {
            if (wheel[2] != wheels[wheelIdx + 1][6]) {
                turnLeft(wheels[wheelIdx + 1], wheelIdx + 1);
            }
        }

        int tmp = wheel[7];
        for (int i = 6; i >= 0; i--) {
            wheel[i + 1] = wheel[i];
        }
        wheel[0] = tmp;

        // print();

        visited[wheelIdx] = false;
    }

    // 반시계방향 회전
    public static void turnLeft(int[] wheel, int wheelIdx) {
        visited[wheelIdx] = true;
        // System.out.println((wheelIdx + 1) + "번 바퀴 Left 회전");

        if (wheelIdx + 1 < 4 && !visited[wheelIdx + 1]) {
            if (wheel[2] != wheels[wheelIdx + 1][6]) {
                turnRight(wheels[wheelIdx + 1], wheelIdx + 1);
            }
        }
        if (wheelIdx - 1 >= 0 && !visited[wheelIdx - 1]) {
            // 극이다르면 회전해야하므로,
            if (wheel[6] != wheels[wheelIdx - 1][2]) {
                turnRight(wheels[wheelIdx - 1], wheelIdx - 1);
            }
        }

        int tmp = wheel[0];
        for (int i = 0; i < 7; i++) {
            wheel[i] = wheel[i + 1];
        }
        wheel[7] = tmp;

        // print();

        visited[wheelIdx] = false;
    }

    // 메인
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력받기
        String[] tmp;
        for (int i = 0; i < 4; i++) {
            tmp = br.readLine().split("");
            for (int j = 0; j < 8; j++) {
                wheels[i][j] = Integer.parseInt(tmp[j]);
            }
        }
        K = Integer.parseInt(br.readLine());

        // 최초 톱니바퀴
        // print();
        command = new int[K][2];
        for (int i = 0; i < K; i++) {
            tmp = br.readLine().split(" ");
            command[i][0] = Integer.parseInt(tmp[0]);
            command[i][1] = Integer.parseInt(tmp[1]);
        }
        // 입력받기 끝.

        // 로직 짜기.

        for (int[] c : command) {
            if (c[1] == 1) {
                turnRight(wheels[c[0] - 1], c[0] - 1);
            } else {
                turnLeft(wheels[c[0] - 1], c[0] - 1);
            }
        }

        int sum = 0;
        for (int i = 0; i < 4; i++) {
            if (i == 0 && wheels[i][0] == 1) {
                sum += 1;
            } else if (i == 1 && wheels[i][0] == 1) {
                sum += 2;
            } else if (i == 2 && wheels[i][0] == 1) {
                sum += 4;
            } else {
                if (wheels[i][0] == 1) {
                    sum += 8;
                }
            }
        }
        System.out.println(sum);
    }

    static void print() {
        System.out.println();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(wheels[i][j] + " ");
            }
            System.out.println();
        }
    }
}
