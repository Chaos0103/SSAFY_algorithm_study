import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

class Robot {
    public int x1, y1, x2, y2;
    public int diff;

    public Robot(int x1, int y1, int x2, int y2, int diff) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.diff = diff;
    }

    @Override
    public boolean equals(Object o) {
        Robot r = (Robot) o;
        return ((x1 == r.x1 && y1 == r.y1) && (x2 == r.x2 && y2 == r.y2)) ||
                ((x1 == r.x2 && y1 == r.y2) && (x2 == r.x1 && y2 == r.y1));
    }

    @Override
    public int hashCode() {
        return Objects.hash(x1, y1) + Objects.hash(x2, y2);
    }
}


class Solution {
    public int[] dx = {0, 0, -1, 1};
    public int[] dy = {-1, 1, 0, 0};
    public HashSet<Robot> set = new HashSet<>();
    public int minDiff = (int) Math.pow(10, 10);

    public boolean inRange(int x, int y, int xMax, int yMax) {
        return (0 <= x && x < xMax) && (0 <= y && y < yMax);
    }

    public boolean isMoveOk(int[][] board, Robot robot, int d) {
        int newX1 = robot.x1 + dx[d];
        int newX2 = robot.x2 + dx[d];
        int newY1 = robot.y1 + dy[d];
        int newY2 = robot.y2 + dy[d];
        int xMax = board[0].length;
        int yMax = board.length;

        return (inRange(newX1, newY1, xMax, yMax) && board[newY1][newX1] == 0) &&
                (inRange(newX2, newY2, xMax, yMax) && board[newY2][newX2] == 0);
    }

    public boolean isFinish(int[][] board, Robot robot) {
        int fx = board[0].length - 1;
        int fy = board.length - 1;

        return (robot.x1 == fx && robot.y1 == fy) || (robot.x2 == fx && robot.y2 == fy);
    }

    public void bfs(int[][] board, Robot start) {
        Queue<Robot> robotQ = new LinkedList<>();
        robotQ.add(start);
        set.add(start);

        while (robotQ.size() > 0) {
            Robot now = robotQ.poll();
            if (isFinish(board, now)) {
                minDiff = now.diff;
                return;
            }

            for (int i = 0; i < 4; i++) {
                if (isMoveOk(board, now, i)) {
                    int x1 = now.x1 + dx[i];
                    int x2 = now.x2 + dx[i];
                    int y1 = now.y1 + dy[i];
                    int y2 = now.y2 + dy[i];

                    Robot r = new Robot(x1, y1, x2, y2, now.diff + 1);
                    if (!set.contains(r)) {
                        robotQ.add(r);
                        set.add(r);
                    }

                    Robot r2 = new Robot(now.x1, now.y1, x1, y1, now.diff + 1);
                    Robot r3 = new Robot(now.x2, now.y2, x2, y2, now.diff + 1);

                    if (!set.contains(r2)) {
                        robotQ.add(r2);
                        set.add(r2);
                    }
                    if (!set.contains(r3)) {
                        robotQ.add(r3);
                        set.add(r3);
                    }
                }
            }
        }
    }

    public int solution(int[][] board) {
        Robot start = new Robot(0, 0, 1, 0, 0);
        bfs(board, start);
        return minDiff;
    }
}
