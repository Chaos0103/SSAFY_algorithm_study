package com.yoojin.programmers;

public class PGS_60059 {
    public boolean solution(int[][] key, int[][] lock) {
         int keyLen = key.length;
        int lockLen = lock.length;
        int[][] board = new int[2 * (keyLen - 1) + lockLen][2 * (keyLen - 1) + lockLen];
        int boardLen = board.length;

        for (int i = keyLen - 1; i <= boardLen - keyLen; i++) {
            for (int j = keyLen - 1; j <= boardLen - keyLen; j++) {
                board[i][j] = lock[i - keyLen + 1][j - keyLen + 1];
            }
        }

        for (int d = 0; d < 4; d++) {
            key = rotate(key, keyLen);

            for (int i = 0; i <= boardLen - keyLen; i++) {
                for (int j = 0; j <= boardLen - keyLen; j++) {
                    int[][] tmp = match(i, j, board, boardLen, key, keyLen);

                    if (unlock(tmp, boardLen, keyLen, lockLen)) return true;
                }
            }
        }

        return false;
    }
    
    static int[][] rotate(int[][] key, int keyLen) {
        int[][] tmp = new int[keyLen][keyLen];

        for (int i = 0; i < keyLen; i++) {
            for (int j = 0; j < keyLen; j++) {
                tmp[j][keyLen - 1 - i] = key[i][j];
            }
        }

        return tmp;
    }
    
     static int[][] match(int x, int y, int[][] board, int boardLen, int[][] key, int keyLen) {
        int[][] tmp = new int[boardLen][boardLen];

        for (int idx = 0; idx < boardLen; idx++)
            System.arraycopy(board[idx], 0, tmp[idx], 0, board[idx].length);

        for (int i = x; i < x + keyLen; i++) {
            for (int j = y; j < y + keyLen; j++) {
                tmp[i][j] += key[i - x][j - y];
                
            }
            
        }
        

        return tmp;
    }
    
    static boolean unlock(int[][] board, int boardLen, int keyLen, int lockLen) {
        int cnt = 0;

        for (int i = keyLen - 1; i <= boardLen - keyLen; i++) {
            for (int j = keyLen - 1; j <= boardLen - keyLen; j++) {
                if (board[i][j] == 1) cnt++;
            }
        }

        return cnt == lockLen * lockLen ? true : false;
    }

}