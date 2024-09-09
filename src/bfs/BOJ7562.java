package bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ7562 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        StringTokenizer st;
        Queue<int[]> q;

        int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2}; // 1시 방향부터 시계 방향으로
        int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};


        for (int i = 0; i < t; i++) {
            int l = Integer.parseInt(br.readLine());
            int[][] board = new int[l][l];
            boolean[][] visited = new boolean[l][l];

            st = new StringTokenizer(br.readLine()); // 시작 좌표
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine()); // 목표 좌표
            int fx = Integer.parseInt(st.nextToken());
            int fy = Integer.parseInt(st.nextToken());

            if (x == fx && y == fy) {
                bw.write("0\n");
                continue;
            }

            q = new LinkedList<>();
            q.offer(new int[]{x, y});
            visited[x][y] = true;

            while (!q.isEmpty()) {
                int[] poll = q.poll();
                int curX = poll[0];
                int curY = poll[1];

                for (int j = 0; j < dx.length; j++) {
                    int nx = curX + dx[j];
                    int ny = curY + dy[j];

                    if (nx >= 0 && nx < l && ny >= 0 && ny < l) {
                        if (!visited[nx][ny]) {
                            visited[nx][ny] = true;
                            board[nx][ny] = board[curX][curY] + 1;
                            q.offer(new int[]{nx, ny});
                        }
                    }
                }
            }

            bw.write(board[fx][fy] + "\n");
        }

        bw.flush();
        bw.close();
    }
}
