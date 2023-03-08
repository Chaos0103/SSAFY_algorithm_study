package algorithm_study.day29;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <pre>
 * SSAFY 알고리즘 스터디 29일차 - 블록 이동하기
 * 솔루션
 * </pre>
 * @author YoungHwan
 *
 */
class Solution {
    static int dx[] = {-1,1,0,0};
    static int dy[] = {0,0,-1,1};
    static int rx[][] = {{-1,0,-1,0}, {0,0,1,1} }; 
    static int ry[][] = {{0,0,1,1}, {-1,0,-1,0}};
    
    public int solution(int[][] board) {
        int answer = Integer.MAX_VALUE;
        int len = board.length;
        Queue<Robot> q = new LinkedList<>();
        boolean visited[][][] = new boolean[2][101][101];
                
        q.add(new Robot(0,0,0,0));
        visited[0][0][0] = true;
        
        while(!q.isEmpty()){
            Robot cur = q.poll();
            if(cur.dir == 0 && cur.x == len-1 && cur.y == len-2 ){
                answer = Math.min(answer, cur.cnt);
                continue;
            }else if(cur.dir == 1 && cur.x==len-2 && cur.y == len-1){
                answer = Math.min(answer, cur.cnt);
                continue;
            }
            
            // 상하좌우 이동
            for(int i=0; i<4; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                /* 움직일 수 있는지 체크 */
                if(!canMove(nx,ny, cur.dir, board)) continue;
                
                if(!visited[cur.dir][nx][ny]){
                    q.add(new Robot(nx,ny,cur.dir, cur.cnt+1));
                    visited[cur.dir][nx][ny] = true;
                }
            }
            
            // 회전
            for(int i=0; i<4; i++){
                int nx = cur.x + rx[cur.dir][i];
                int ny = cur.y + ry[cur.dir][i];
                
                /* 가로 방향은 상하로 움직일 수 있으면 회전 가능 */
                int cx = cur.x + dx[i%2];
                int cy = cur.y + dy[i%2];
                
                if(cur.dir == 1){   // 세로 방향은 좌우로 움직일 수 있으면 회전 가능
                    cx = cur.x + dx[i<2?i+2:i];
                    cy = cur.y + dy[i<2?i+2:i];
                }
                
                int ndir = cur.dir^1;   // 방향 XOR 연산으로 계산
                
                /* 회전 할 수 있는지 체크 */
                if(!canMove(nx,ny, ndir, board)|| !canMove(cx, cy, cur.dir, board) ) continue;
                
                if(!visited[ndir][nx][ny]){
                    q.add(new Robot(nx,ny,ndir,cur.cnt+1));
                    visited[ndir][nx][ny] = true;
                }
            }
        }
        
        return answer;
    }
    
    /* 움직일 수 있는지 체크 */
    public static boolean canMove(int nx, int ny, int dir, int[][] board){
        int len = board.length;
        if(dir == 0){
            if(nx<0 || ny <0 || nx >= len || ny >= len || 
               ny+1 >= len ||board[nx][ny] != 0 || board[nx][ny+1] != 0 ){
                return false;
            }
            return true;
        }else{
            if(nx<0 || ny<0 || nx >= len || nx+1>=len || ny>=len ||
              board[nx][ny] != 0 || board[nx+1][ny] != 0){
                return false;
            }
            return true;
        }
    }
        
    
    static class Robot{
        int x;
        int y;
        int dir;    // 0:가로 1:세로
        int cnt;    // 걸린 시간
        Robot(int x, int y, int dir, int cnt){
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cnt = cnt;
        }
    }
}