package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;
import static java.util.Arrays.sort;

public class BOJ1931 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = parseInt(br.readLine());

        int[][] meetingInfo = new int[n][2];

        StringTokenizer st;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            meetingInfo[i][0] = parseInt(st.nextToken());
            meetingInfo[i][1] = parseInt(st.nextToken());
        }

        sort(meetingInfo, (m1, m2) -> {
            if (m1[1] == m2[1]) return m1[0] - m2[0];
            return m1[1] - m2[1];
        });

        int count = 0;
        int lastMeetingEndTime = 0;

        for (int i = 0; i < n; i++) {
            if (lastMeetingEndTime <= meetingInfo[i][0]) {
                lastMeetingEndTime = meetingInfo[i][1];
                count++;
            }
        }

        System.out.println(count);
    }
}
