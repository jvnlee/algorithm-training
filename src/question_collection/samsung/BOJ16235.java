package question_collection.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ16235 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] A = new int[N][N];
        int[][] nutritions = new int[N][N];
        List<Tree>[][] trees = new ArrayList[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
                nutritions[i][j] = 5;
                trees[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int z = Integer.parseInt(st.nextToken());

            trees[x][y].add(new Tree(z, true));
        }

        // 북쪽부터 시계방향
        int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
        int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

        for (int year = 0; year < K; year++) {
            // 봄
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    List<Tree> treeList = trees[i][j];

                    if (!treeList.isEmpty()) {
                        treeList.sort(Comparator.comparingInt(t -> t.age));

                        for (Tree t : treeList) {
                            if (t.isAlive && nutritions[i][j] >= t.age) {
                                nutritions[i][j] -= t.age;
                                t.age++;
                            } else {
                                t.isAlive = false;
                            }
                        }
                    }
                }
            }

            // 여름
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    List<Tree> treeList = trees[i][j];

                    if (!treeList.isEmpty()) {
                        for (Tree t : treeList) {
                            if (!t.isAlive) {
                                nutritions[i][j] += t.age / 2;
                            }
                        }

                        treeList.removeIf(t -> !t.isAlive);
                    }
                }
            }

            // 가을
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    List<Tree> treeList = trees[i][j];

                    if (!treeList.isEmpty()) {
                        for (Tree t : treeList) {
                            if (t.age % 5 == 0) {
                                for (int k = 0; k < 8; k++) {
                                    int nx = i + dx[k];
                                    int ny = j + dy[k];

                                    if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                                        trees[nx][ny].add(new Tree(1, true));
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // 겨울
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    nutritions[i][j] += A[i][j];
                }
            }
        }

        int answer = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!trees[i][j].isEmpty()) {
                    answer += trees[i][j].size();
                }
            }
        }

        System.out.println(answer);
    }

    private static class Tree {
        int age;
        boolean isAlive;

        public Tree(int age, boolean isAlive) {
            this.age = age;
            this.isAlive = isAlive;
        }
    }
}
