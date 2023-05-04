package programmers;

import java.util.*;

public class PRG92341 {
    public int[] info;
    public int MAX_TIME;

    public int[] solution(int[] fees, String[] records) {
        info = fees;
        MAX_TIME = getMinutes("23:59");

        Map<String, List<String>> map = new HashMap<>();

        for (String record : records) {
            String[] split = record.split(" ");

            if (map.containsKey(split[1])) {
                map.get(split[1]).add(split[0]);
            } else {
                map.put(split[1], new ArrayList<>());
                map.get(split[1]).add(split[0]);
            }
        }

        Map<String, Integer> feeMap = new HashMap<>();

        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            List<String> times = entry.getValue();
            if (times.size() == 1) {
                feeMap.put(entry.getKey(), getFee(MAX_TIME - getMinutes(times.get(0))));
            } else {
                int totalMinutes = 0;
                for (int i = 0; i < times.size(); i += 2) {
                    if (i == times.size() - 1) {
                        totalMinutes += (MAX_TIME - getMinutes(times.get(i)));
                    } else {
                        totalMinutes += (getMinutes(times.get(i + 1)) - getMinutes(times.get(i)));
                    }
                }
                feeMap.put(entry.getKey(), getFee(totalMinutes));
            }
        }

        List<Map.Entry<String, Integer>> entries = new ArrayList<>(feeMap.entrySet());
        entries.sort(Map.Entry.comparingByKey());

        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : entries) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        int[] answer = new int[feeMap.size()];

        int i = 0;
        for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
            answer[i] = entry.getValue();
            i++;
        }

        return answer;
    }

    public int getMinutes(String time) {
        String[] split = time.split(":");
        return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
    }

    public int getFee(int minutes) {
        return minutes < info[0] ? info[1] : (int) (info[1] + Math.ceil((double) (minutes - info[0]) / (double) info[2]) * info[3]);
    }
}
