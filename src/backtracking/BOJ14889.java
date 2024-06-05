package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14889 {
    public static int n;
    public static int[][] synergies;
    public static int answer = Integer.MAX_VALUE;
    public static int[] nums;
    public static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        synergies = new int[n][n];

        StringTokenizer st;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                synergies[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = i;
        }

        visited = new boolean[n];

        combination(0, 0);

        System.out.println(answer);
    }

    public static void combination(int depth, int start) {
        if (depth == n / 2) {
            calculateTeamSynergy();
            return;
        }

        for (int i = start; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                combination(depth + 1, i + 1);
                visited[i] = false;
            }
        }
    }

    private static void calculateTeamSynergy() {
        int t1 = 0;
        int t2 = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (visited[i] && visited[j]) {
                    t1 += synergies[i][j] + synergies[j][i];
                } else if (!visited[i] && !visited[j]) {
                    t2 += synergies[i][j] + synergies[j][i];
                }
            }
        }

        answer = Math.min(answer, Math.abs(t1 - t2));

        if (answer == 0) {
            System.out.println(answer);
            System.exit(0);
        }
    }
}
