package question_collection.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ15686 {
    private static int N, M;
    private static List<int[]> homes = new ArrayList<>();
    private static List<int[]> chickens = new ArrayList<>();
    private static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= N; j++) {
                int cell = Integer.parseInt(st.nextToken());

                if (cell == 1) {
                    homes.add(new int[]{i, j});
                } else if (cell == 2) {
                    chickens.add(new int[]{i, j});
                }
            }
        }

        combination(0, 0, new ArrayList<>());

        System.out.println(answer);
    }

    private static void combination(int startIdx, int depth, List<int[]> availableChickens) {
        if (depth == M) {
            int chickenDist = 0;

            for (int[] h : homes) {
                int minDist = Integer.MAX_VALUE;

                for (int[] c : availableChickens) {
                    int d = Math.abs(h[0] - c[0]) + Math.abs(h[1] - c[1]);
                    minDist = Math.min(minDist, d);
                }

                chickenDist += minDist;
            }

            answer = Math.min(answer, chickenDist);
            return;
        }

        for (int i = startIdx; i < chickens.size(); i++) {
            availableChickens.add(chickens.get(i));
            combination(i + 1, depth + 1, availableChickens);
            availableChickens.remove(availableChickens.size() - 1);
        }
    }
}
