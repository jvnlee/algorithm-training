package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1931_3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] meetings = new int[n][2];

        StringTokenizer st;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            meetings[i][0] = Integer.parseInt(st.nextToken());
            meetings[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(meetings, (m1, m2) -> {
            if (m1[1] == m2[1]) {
                return m1[0] - m2[0];
            } else {
                return m1[1] - m2[1];
            }
        });

        int lastEndTime = 0;
        int answer = 0;

        for (int[] meeting : meetings) {
            if (meeting[0] >= lastEndTime) {
                lastEndTime = meeting[1];
                answer++;
            }
        }

        System.out.println(answer);
    }
}
