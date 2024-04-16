package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class BOJ1931_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = parseInt(br.readLine());
        List<Meeting> meetingList = new ArrayList<>(n);
        StringTokenizer st;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = parseInt(st.nextToken());
            int end = parseInt(st.nextToken());

            meetingList.add(new Meeting(start, end));
        }

        meetingList.sort((m1, m2) -> {
            if (m1.getEnd() == m2.getEnd()) { // 종료 시간이 같다면
                return m1.getStart() - m2.getStart(); // 시작 시간이 빠른 순으로 정렬
            } else {
                return m1.getEnd() - m2.getEnd(); // 종료 시간 오름차순으로 정렬
            }
        });

        int count = 0;
        int lastEnd = 0;

        for (Meeting m : meetingList) {
            if (lastEnd <= m.getStart()) {
                lastEnd = m.getEnd();
                count++;
            }
        }

        System.out.println(count);
    }

    public static class Meeting {
        private int start;
        private int end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }
    }
}
