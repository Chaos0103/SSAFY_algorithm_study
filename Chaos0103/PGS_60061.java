import java.util.*;

class Node implements Comparable<Node> {

    private int x;
    private int y;
    private int type;

    public Node(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getType() {
        return type;
    }

    @Override
    public int compareTo(Node other) {
        if (this.x == other.x && this.y == other.y) {
            return Integer.compare(this.type, other.type);
        } else if (this.x == other.x) {
            return Integer.compare(this.y, other.y);
        }
        return Integer.compare(this.x, other.x);
    }
}
class Solution {
    public int[][] solution(int n, int[][] build_frame) {
        ArrayList<Node> build = new ArrayList<>();

        for (int[] data : build_frame) {
            int x = data[0];
            int y = data[1];
            int a = data[2];
            if (data[3] == 0) {
                for (int i = 0; i < build.size(); i++) {
                    if (build.get(i).getX() == x && build.get(i).getY() == y && build.get(i).getType() == a) {
                        build.remove(i);
                        break;
                    }
                }
                if (!check(build)) {
                    build.add(new Node(x, y, a));
                }
            } else {
                build.add(new Node(x, y, a));
                if (!check(build)) {
                    build.remove(build.size() - 1);
                }
            }
        }

        Collections.sort(build);
        int[][] answer = new int[build.size()][3];
        for (int i = 0; i < build.size(); i++) {
            answer[i][0] = build.get(i).getX();
            answer[i][1] = build.get(i).getY();
            answer[i][2] = build.get(i).getType();
        }

        return answer;
    }

    private boolean check(ArrayList<Node> build) {
        for (Node node : build) {
            int x = node.getX();
            int y = node.getY();
            if (node.getType() == 0) {
                boolean result = false;
                if (y == 0) {
                    result = true;
                }
                for (Node other : build) {
                    if (other.getX() == x && other.getY() == y - 1 && other.getType() == 0) {
                        result = true;
                    }
                    if (other.getX() == x - 1 && other.getY() == y && other.getType() == 1) {
                        result = true;
                    }
                    if (other.getX() == x && other.getY() == y && other.getType() == 1) {
                        result = true;
                    }
                }
                if (!result) {
                    return false;
                }
            } else {
                boolean result = false;
                boolean left = false;
                boolean right = false;
                for (Node other : build) {
                    if (other.getX() == x && other.getY() == y - 1 && other.getType() == 0) {
                        result = true;
                    }
                    if (other.getX() == x + 1 && other.getY() == y - 1 && other.getType() == 0) {
                        result = true;
                    }
                    if (other.getX() == x - 1 && other.getY() == y && other.getType() == 1) {
                        left = true;
                    }
                    if (other.getX() == x + 1 && other.getY() == y && other.getType() == 1) {
                        right = true;
                    }
                }
                if (left && right) {
                    result = true;
                }
                if (!result) {
                    return false;
                }
            }
        }
        return true;
    }
}
