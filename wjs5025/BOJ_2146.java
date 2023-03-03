import java.io.*;
import java.util.*;

/*
 * 계속 메모리초과가 떠서 고민했는데,
 * 질문게시판 글을 보고, bfs시 방문체크 위치가 잘못됐단 걸 알았습니다.
 * "bfs에서는 큐에서 뺀 다음이 아니라, 큐에 넣을 때 방문체크를 해야한다" ****
 *  * https://www.acmicpc.net/board/view/23029
 * 
 * 

1. 섬구분하기 (BFS?) 1번 2번 3번..
2. 섬각각의 위치에서 다른섬까지의 최솟값 갱신하기

첫번째 1만나면 bfs 탐색 시작.
방문체크하고, 다음으로.

종료하면 섬넘버 ++
*/

class Node {
   int r;
   int c;

   @Override
   public String toString() {
      return "Node [r=" + r + ", c=" + c + "]";
   }

   public Node(int r, int c) {
      super();
      this.r = r;
      this.c = c;
   }

}

public class Main {
   static int N;
   static int[][] map;
   static boolean[][] visited;
   static int min = Integer.MAX_VALUE;

   static int[] dx = { -1, 0, 1, 0 }; // 상우하좌
   static int[] dy = { 0, 1, 0, -1 };// 상우하좌

   static int islandNum = 2; // 섬번호는 2부터 시작

   public static void bfs(int r, int c) {
      Queue<Node> q = new ArrayDeque<>();
      q.offer(new Node(r, c));

      while (!q.isEmpty()) {
         Node current = q.poll();
         map[current.r][current.c] = islandNum;

         // 상우하좌 탐색하면서 추가하기
         for (int i = 0; i < 4; i++) {
            // 범위 탐색
            if (current.r + dx[i] >= 0 && current.c + dy[i] >= 0 && current.r + dx[i] < N && current.c + dy[i] < N
                  && map[current.r + dx[i]][current.c + dy[i]] == 1
                  && !visited[current.r + dx[i]][current.c + dy[i]]) {
               q.offer(new Node(current.r + dx[i], current.c + dy[i]));
               visited[current.r + dx[i]][current.c + dy[i]] = true;
            }
         }
      }

   }

   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      N = Integer.parseInt(br.readLine());
      map = new int[N][N];
      visited = new boolean[N][N];

      for (int i = 0; i < N; i++) {
         String[] tmp = br.readLine().split(" ");
         for (int j = 0; j < N; j++) {
            map[i][j] = Integer.parseInt(tmp[j]);
         }
      }

      for (int i = 0; i < N; i++) {
         for (int j = 0; j < N; j++) {
            if (map[i][j] >= 1 && !visited[i][j]) {
               bfs(i, j);
               islandNum++;
            }
         }
      }
//      print();

      // 배열만들어서,
      // 0과 붙어있는 놈을 찾고,
      // ArrayList<ArrayList<Node>> 만들어서
      // 각 어레이 리스트 노드 <-> 어레이리스트 노드 해서 최소값 갱신

      ArrayList<ArrayList<int[]>> islands = new ArrayList<>();

      for (int i = 0; i < islandNum; i++) {
         islands.add(new ArrayList<int[]>());
      }
      boolean[][] visiting = new boolean[N][N];

      for (int i = 2; i < islandNum; i++) {
         for (int j = 0; j < N; j++) {
            for (int k = 0; k < N; k++) {
               if (map[j][k] == i && isInBound(j, k) && !visiting[j][k]) {
                  int[] tmp = { j, k };
                  islands.get(i).add(tmp);
               }
            }
         }
      }

      for (int i = 2; i < islandNum - 1; i++) {
         for (int j = i + 1; j < islandNum; j++) {
            // 각 섬들에 있는 바운더리 끼리 비교
            for (int[] a : islands.get(i)) {
               for (int[] b : islands.get(j)) {
                  min = Math.min(min, Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]));
               }
            }

         }
      }

      System.out.println(min - 1);
   }

   static boolean isInBound(int r, int c) {
      for (int i = 0; i < 4; i++) {
         if (r + dx[i] >= 0 && r + dx[i] < N && c + dy[i] >= 0 && c + dy[i] < N) {
            if (map[r + dx[i]][c + dy[i]] == 0)
               return true;
         }
      }
      return false;
   }

   static void print() {
      System.out.println();
      for (int i = 0; i < N; i++) {
         for (int j = 0; j < N; j++) {
            System.out.print(map[i][j] + " ");
         }
         System.out.println();
      }
   }
}