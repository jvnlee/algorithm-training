package minimum_spanning_tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17472 {
    private static int N, M;
    private static int islandNum = 0;
    private static int[][] map;
    private static boolean[][] visited;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    private static int[] parents;
    private static PriorityQueue<Bridge> bridges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 섬 넘버링
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    islandNum++;
                    markIslandNum(i, j);
                }
            }
        }

        /*
         다리 건설 시도
         1. 상하좌우 중에서 맵 범위를 벗어나지 않는 0인 칸이 있다면 시도
         2. 해당 방향으로 탐색을 이어갔을 때 출발지와 다른 값에 도달한다면 유효한 다리로 인정
         3. 단, 길이가 1이면 무효. 이미 출발-도착 섬이 같은 다리가 존재한다면 최소 길이의 것으로 저장
         */

        /*
         유효한 다리 건설 조건
         - 한 방향으로만 갈 것
         - 길이가 1보다 클 것
         - 겹쳐도 상관은 없으나 길이는 각각 계산할 것
         */

        bridges = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int cur = map[i][j];

                if (cur != 0) {
                    for (int dir = 0; dir < 4; dir++) {
                        buildBridge(i, j, dir, cur, 0);
                    }
                }
            }
        }

        /*
         모든 다리 건설을 마친 후,
         그래프 정보를 가지고 크루스칼 알고리즘 적용
         */

        parents = new int[islandNum + 1];

        for (int i = 1; i < islandNum; i++) {
            parents[i] = i;
        }

        int edgeCount = 0;
        int answer = 0;

        while (!bridges.isEmpty() && edgeCount < islandNum - 1) {
            Bridge bridge = bridges.poll();

            int from = bridge.from;
            int to = bridge.to;
            int dist = bridge.dist;

            if (find(from) != find(to)) {
                union(from, to);
                edgeCount++;
                answer += dist;
            }
        }

        if (edgeCount < islandNum - 1) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }

    private static void markIslandNum(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});
        visited[x][y] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cx = cur[0];
            int cy = cur[1];

            map[cx][cy] = islandNum;

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny] && map[nx][ny] == 1) {
                    q.offer(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }
    }

    private static void buildBridge(int x, int y, int dir, int from, int dist) {
        int nx = x + dx[dir];
        int ny = y + dy[dir];

        if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
            return;
        }

        int to = map[nx][ny];

        if (to == 0) {
            buildBridge(nx, ny, dir, from, dist + 1);
        } else {
            if (to != from && dist >= 2) {
                bridges.offer(new Bridge(from, to, dist));
            }
        }
    }

    private static void union(int n1, int n2) {
        int r1 = find(n1);
        int r2 = find(n2);

        if (r1 == r2) {
            return;
        }

        if (r1 < r2) {
            parents[r2] = r1;
        } else {
            parents[r1] = r2;
        }
    }

    private static int find(int node) {
        if (parents[node] == node) {
            return node;
        }

        return parents[node] = find(parents[node]);
    }

    private static class Bridge implements Comparable<Bridge> {
        int from;
        int to;
        int dist;

        public Bridge(int from, int to, int dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Bridge e) {
            return Integer.compare(this.dist, e.dist);
        }
    }
}
