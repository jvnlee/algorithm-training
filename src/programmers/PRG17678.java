package programmers;

import java.util.*;

public class PRG17678 {
    public String solution(int n, int t, int m, String[] timetable) {
        PriorityQueue<Time> arrivedTimes = new PriorityQueue<>();
        Time lastShuttleTime = new Time(18, 0);

        for (int i = 0; i < timetable.length; i++) {
            String[] time = timetable[i].split(":");
            int hour = Integer.parseInt(time[0]);
            int minute = Integer.parseInt(time[1]);

            Time arrivedTime = new Time(hour, minute);

            if (arrivedTime.compareTo(lastShuttleTime) <= 0) {
                arrivedTimes.offer(arrivedTime);
            }
        }

        Time currentShuttleTime = new Time(9, 0);
        Time lastArrivedTime = null;

        for (int i = 0; i < n; i++) {
            if (i > 0) {
                currentShuttleTime.addMinutes(t);
            }

            int max = m;

            while (max > 0 && !arrivedTimes.isEmpty()) {
                if (arrivedTimes.peek().compareTo(currentShuttleTime) <= 0) {
                    lastArrivedTime = arrivedTimes.poll();
                    max--;
                } else {
                    break;
                }
            }

            if (i == n - 1) {
                if (max > 0) {
                    lastArrivedTime = currentShuttleTime;
                } else {
                    lastArrivedTime.subtractMinutes(1);
                }
            }
        }

        return lastArrivedTime.toString();
    }

    private class Time implements Comparable<Time> {
        int hour;
        int minute;

        public Time(int hour, int minute) {
            this.hour = hour;
            this.minute = minute;
        }

        public void addMinutes(int m) {
            if (this.minute + m >= 60) {
                this.hour++;
                this.minute += (m - 60);
            } else {
                this.minute += m;
            }
        }

        public void subtractMinutes(int m) {
            if (this.minute - m < 0) {
                this.hour--;
                this.minute -= (m - 60);
            } else {
                this.minute -= m;
            }
        }

        @Override
        public int compareTo(Time t) {
            if (this.hour == t.hour) {
                return this.minute - t.minute;
            } else {
                return this.hour - t.hour;
            }
        }

        @Override
        public String toString() {
            String h = this.hour < 10 ? "0" + this.hour : "" + this.hour;
            String m = this.minute < 10 ? "0" + this.minute : "" + this.minute;

            return h + ":" + m;
        }
    }
}
