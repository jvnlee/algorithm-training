package algorithm_book;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class P152 {

    public static int n, m;
    public static int[][] graph;
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        graph = new int[n][m];

        sc.nextLine();

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine();
            for (int j = 0; j < m; j++) {
                graph[i][j] = line.charAt(j) - '0';
            }
        }

        System.out.println(bfs(0, 0));
    }

    public static int bfs(int x, int y) {
        Queue<Node> queue = new LinkedList<>();
        Node start = new Node(0, 0);

        queue.offer(start);

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            x = node.getX();
            y = node.getY();

            for (int i = 0; i < 4; i++) {
                int tmpX = x + dx[i];
                int tmpY = y + dy[i];

                if (tmpX < 0 || tmpX >= n || tmpY < 0 || tmpY >= m) continue;

                if (graph[tmpX][tmpY] == 0) continue;

                if (graph[tmpX][tmpY] == 1) {
                    graph[tmpX][tmpY] = graph[x][y] + 1;
                    queue.offer(new Node(tmpX, tmpY));
                }
            }
        }

        return graph[n - 1][m - 1];
    }

    public static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}
