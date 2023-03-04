package BOJ_21609;

import java.io.*;
import java.util.*;

class Node implements Comparable<Node> {
    int r;
    int c;
    int v;
    boolean isGrouped;

    @Override
    public String toString() {
        return Integer.toString(v) + "  ";
        // return "Node [r=" + r + ", c=" + c + ", v=" + v + "]";
    }

    public Node(int r, int c, int v) {
        super();
        this.r = r;
        this.c = c;
        this.v = v;
    }

    @Override
    public int compareTo(Node o) {
        if (Integer.compare(this.r, o.r) == 0) {
            return Integer.compare(this.c, o.c);
        }
        return Integer.compare(this.r, o.r);
    }
}

public class Main {
    static int N, M;
    static Node[][] map;
    static HashSet<Node> groupSet;
    static ArrayList<Node> group;
    static ArrayList<ArrayList<Node>> groups = new ArrayList<>();
    static int score = 0;
    static int[] dx = { -1, 0, 1, 0 }; // 상우하좌
    static int[] dy = { 0, 1, 0, -1 }; // 상우하좌

    // 새로운 값(newValue) 가진 노드가 그룹에 추가될 수 있는지 검사하는 함수.
    public static boolean canAdd(int newValue) {
        // 스태틱 변수 group의 요소들에 새로운 값 newValue가 들어왔을때
        // 포함될 수 있으면 true
        // 포할될 수 없으면 false 리턴

        // 검정블록이면 무조건 못넣어.
        if (newValue == -1)
            return false;

        // 그룹이 비었을 때, 새로운 값이 무지개색이면 포함할 수 없으므로 못넣어.
        if (groupSet.size() == 0) {
            if (newValue == 0)
                return false;
        } else {
            // 그룹이 안비었을때, 무지개 블록이면 환영
            if (newValue == 0) {
                return true;
            }
        }
        // 일반 블록 숫자와 일치하지 않으면 못넣어.
        for (Node n : groupSet) {
            if (n.v != 0) {
                if (newValue != n.v)
                    return false;
            }
        }

        // 다 충족하면 true;
        return true;
    }

    // bfs를 돌면서 검사할거야.
    public static void bfs(int r, int c) {
        // 이번 노드에서의 그룹을 담을 group 어레이 리스트
        groupSet = new HashSet<>();

        // 큐 생성
        Deque<Node> q = new ArrayDeque<Node>();
        // 첫번째 요소 넣고 방문처리
        q.offer(map[r][c]);
        if (canAdd(map[r][c].v)) { // 추가가능하면
            groupSet.add(map[r][c]);
            map[r][c].isGrouped = true;
        }

        while (!q.isEmpty()) {
            Node current = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = current.r + dx[i];
                int ny = current.c + dy[i];

                // 범위 안에 속하고
                if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
                    if (!map[nx][ny].isGrouped && canAdd(map[nx][ny].v)) {
                        map[nx][ny].isGrouped = true;
                        q.add(map[nx][ny]);
                        groupSet.add(map[nx][ny]);
                    } else {
                        if (map[nx][ny].v == 0 && map[nx][ny].isGrouped) {
                            groupSet.add(map[nx][ny]);
                        }
                    }
                }
            }
        }

        group = new ArrayList<>(groupSet);
        Collections.sort(group);

        if (group.size() >= 2)
            groups.add(group);
    }

    // 중력
    static void gravity() {
        for (int i = 0; i < N; ++i) {
            for (int j = N - 1; j >= 0; --j) {
                if (map[j][i].v == -2 || map[j][i].v == -1)
                    continue;
                int dest = j + 1;
                while (true) {
                    if (dest == N)
                        break;
                    if (map[dest][i].v == -2)
                        dest++;
                    else
                        break;
                }
                if (dest == j + 1)
                    continue;
                map[dest - 1][i].v = map[j][i].v;
                map[j][i].v = -2;
            }
        }
    }

    // 반시계로 회전
    static void rotateLeft() {
        Deque<Node> q = new ArrayDeque<Node>();
        Node[][] newMap = new Node[N][N];
        // 큐에 순차적으로 집어넣기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                q.offer(map[i][j]);
            }
        }
        for (int j = 0; j < N; j++) {
            for (int i = N - 1; i >= 0; i--) {
                newMap[i][j] = q.poll();
            }
        }

        map = newMap;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        map = new Node[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int value = sc.nextInt();
                map[i][j] = new Node(i, j, value);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!map[i][j].isGrouped) {
                    if (map[i][j].v > 0)
                        bfs(i, j);
                }
            }
        }

        // 그룹 사이즈 내림차순 정렬
        Collections.sort(groups, (o1, o2) -> o2.size() - o1.size());
        printGroups();

        while (!groups.isEmpty()) {
            int groupRealSize = 0;
            System.out.println("0번그룹" + groups.get(0));

            for (Node n : groups.get(0)) {
                if (map[n.r][n.c].v != -2) {
                    groupRealSize++;
                }
            }

            for (Node n : groups.get(0)) {
                map[n.r][n.c].v = -2;
            }

            score += Math.pow(groupRealSize, 2);
            groups.remove(0);
            System.out.println("그룹 제거후");
            print();
            System.out.println("그래비티 후");
            gravity();
            System.out.println("반시계 회전 후");
            rotateLeft();
            System.out.println("또 다시 그래비티 후");
            gravity();

            print();

            groups.clear();

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map[i][j].isGrouped = false;
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!map[i][j].isGrouped) {
                        if (map[i][j].v > 0)
                            bfs(i, j);
                    }
                }
            }
        }

        // 최종 점수 출력
        System.out.println("score" + score);
    }

    static void print() {
        System.out.println("--------map---------");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void printGroups() {
        System.out.println("--------그룹찍기---------");
        for (ArrayList<Node> g : groups) {
            System.out.println(g.clone());
        }
    }
}
