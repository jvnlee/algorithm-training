package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11399_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] withdrawTimes = new int[n];

        for (int i = 0; i < n; i++) {
            withdrawTimes[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(withdrawTimes);

        int[] totalTimes = new int[n];
        totalTimes[0] = withdrawTimes[0];

        for (int i = 1; i < n; i++) {
            totalTimes[i] = totalTimes[i - 1] + withdrawTimes[i];
        }

        int answer = 0;

        for (int totalTime : totalTimes) {
            answer += totalTime;
        }

        System.out.println(answer);
    }
}
