class Solution {
    public boolean solution(int[][] key, int[][] lock) {

        int[][] newLock = createNewLock(lock);

        for (int t = 0; t < 4; t++) {
            key = rotateAMatrixBy90Degree(key);
            for (int i = 0; i < newLock.length - key.length; i++) {
                for (int j = 0; j < newLock.length - key.length; j++) {
                    newLock = insertKey(newLock, key, i, j);

                    if (isUnLock(newLock)) {
                        return true;
                    }

                    newLock = removeKey(newLock, key, i, j);
                }
            }
        }
        return false;
    }

    private int[][] createNewLock(int[][] lock) {
        int[][] res = new int[lock.length * 3][lock[0].length * 3];
        for (int i = 0; i < lock.length; i++) {
            for (int j = 0; j < lock.length; j++) {
                res[lock.length + i][lock.length + j] = lock[i][j];
            }
        }
        return res;
    }

    public int[][] rotateAMatrixBy90Degree(int[][] arr) {
        int rowLength = arr.length;
        int columnLength = arr[0].length;

        int[][] res = new int[columnLength][rowLength];
        for (int r = 0; r < rowLength; r++) {
            for (int c = 0; c < columnLength; c++) {
                res[c][rowLength - 1 - r] = arr[r][c];
            }
        }
        return res;
    }

    private int[][] insertKey(int[][] lock, int[][] key, int x, int y) {
        for (int i = 0; i < key.length; i++) {
            for (int j = 0; j < key.length; j++) {
                lock[x + i][y + j] += key[i][j];
            }
        }
        return lock;
    }

    private int[][] removeKey(int[][] lock, int[][] key, int x, int y) {
        for (int i = 0; i < key.length; i++) {
            for (int j = 0; j < key.length; j++) {
                lock[x + i][y + j] -= key[i][j];
            }
        }
        return lock;
    }

    private boolean isUnLock(int[][] lock) {
        int x = lock.length / 3;
        for (int i = x; i < x * 2; i++) {
            for (int j = x; j < x * 2; j++) {
                if (lock[i][j] != 1) {
                    return false;
                }
            }    
        }
        return true;
    }
}
