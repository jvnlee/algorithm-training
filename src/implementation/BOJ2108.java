package implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class BOJ2108 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        List<Integer> nums = new ArrayList<>();
        Map<Integer, Integer> frequencies = new HashMap<>();

        int sum = 0;

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            nums.add(num);

            frequencies.merge(num, 1, Integer::sum);

            sum += num;
        }

        bw.write((int) (Math.round(((double) sum / n))) + "\n"); // 산술평균

        Collections.sort(nums);

        bw.write(nums.get(n / 2) + "\n"); // 중앙값

        int maxFrequency = 1;

        List<Integer> maxFreqNums = new ArrayList<>();

        for (Entry<Integer, Integer> entry : frequencies.entrySet()) {
            if (entry.getValue() == maxFrequency) {
                maxFreqNums.add(entry.getKey());
            } else if (entry.getValue() > maxFrequency) {
                maxFreqNums.clear();
                maxFreqNums.add(entry.getKey());
                maxFrequency = entry.getValue();
            }
        }

        Collections.sort(maxFreqNums);

        int maxFreqNum;

        if (maxFreqNums.size() == 1) {
            maxFreqNum = maxFreqNums.get(0);
        } else {
            maxFreqNum = maxFreqNums.get(1);
        }

        bw.write(maxFreqNum + "\n"); // 최빈값

        bw.write((nums.get(n - 1) - nums.get(0)) + ""); // 범위

        bw.flush();
        bw.close();
    }
}
