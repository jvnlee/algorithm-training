package programmers;

public class PRG60059 {
    private int M, N;

    public boolean solution(int[][] key, int[][] lock) {
        M = key.length;
        N = lock.length;

        int[][] extendedLock = new int[N + 2 * (M - 1)][N + 2 * (M - 1)];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                extendedLock[i + M - 1][j + M - 1] = lock[i][j];
            }
        }

        for (int i = 0; i < 4; i++) {
            if (move(key, extendedLock)) {
                return true;
            }

            key = rotate(key);
        }

        return false;
    }

    private boolean move(int[][] key, int[][] extendedLock) {
        int len = extendedLock.length;

        for (int x = 0; x <= len - M; x++) {
            for (int y = 0; y <= len - M; y++) {
                if (canUnlock(key, extendedLock, x, y)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean canUnlock(int[][] key, int[][] extendedLock, int x, int y) {
        int[][] tempLock = copyArray(extendedLock);

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                tempLock[x + i][y + j] += key[i][j];
            }
        }

        for (int i = M - 1; i < M - 1 + N; i++) {
            for (int j = M - 1; j < M - 1 + N; j++) {
                if (tempLock[i][j] != 1) {
                    return false;
                }
            }
        }

        return true;
    }

    private int[][] rotate(int[][] key) {
        int[][] rotatedKey = new int[M][M];

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                rotatedKey[j][M - 1 - i] = key[i][j];
            }
        }

        return rotatedKey;
    }

    private int[][] copyArray(int[][] arr) {
        int[][] copy = new int[arr.length][arr[0].length];

        for (int i = 0; i < arr.length; i++) {
            copy[i] = arr[i].clone();
        }

        return copy;
    }
}
