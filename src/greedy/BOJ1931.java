package greedy;

import java.util.*;

public class BOJ1931 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int[][] meetingInfo = new int[n][2];

        for (int i = 0; i < n; i++) {
            meetingInfo[i][0] = scanner.nextInt();
            meetingInfo[i][1] = scanner.nextInt();
        }

        Arrays.sort(meetingInfo, (o1, o2) -> {
            if (o1[1] == o2[1]) return o1[0] - o2[0];
            return o1[1] - o2[1];
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
