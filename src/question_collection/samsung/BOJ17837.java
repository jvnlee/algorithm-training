package question_collection.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ17837 {
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][N];
        List<Integer>[][] board = new ArrayList[N][N];
        List<Piece> pieces = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                board[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1;

            pieces.add(new Piece(x, y, d));
            board[x][y].add(i);
        }

        int[] dx = {0, 0, -1, 1};
        int[] dy = {1, -1, 0, 0};

        for (int turn = 1; turn <= 1000; turn++) {
            for (int i = 0; i < K; i++) {
                Piece piece = pieces.get(i);
                int x = piece.x;
                int y = piece.y;
                int d = piece.d;

                int idx = board[x][y].indexOf(i);
                List<Integer> moving = new ArrayList<>(board[x][y].subList(idx, board[x][y].size()));
                board[x][y].subList(idx, board[x][y].size()).clear();

                int nx = x + dx[d];
                int ny = y + dy[d];

                if (!inRange(nx, ny) || map[nx][ny] == 2) {
                    piece.d = reverseDirection(d);
                    nx = x + dx[piece.d];
                    ny = y + dy[piece.d];

                    if (!inRange(nx, ny) || map[nx][ny] == 2) {
                        board[x][y].addAll(moving);
                        continue;
                    }
                }

                if (map[nx][ny] == 1) {
                    Collections.reverse(moving);
                }

                for (int pieceIdx : moving) {
                    Piece p = pieces.get(pieceIdx);
                    p.x = nx;
                    p.y = ny;
                }

                board[nx][ny].addAll(moving);

                if (board[nx][ny].size() >= 4) {
                    System.out.println(turn);
                    return;
                }
            }
        }

        System.out.println(-1);
    }

    private static class Piece {
        int x;
        int y;
        int d;

        public Piece(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }

    static int reverseDirection(int d) {
        if (d == 0) return 1;
        if (d == 1) return 0;
        if (d == 2) return 3;
        return 2;
    }

    static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}
