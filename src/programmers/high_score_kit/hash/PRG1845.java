package programmers.high_score_kit.hash;

import java.util.*;

public class PRG1845 {
    public int solution(int[] nums) {
        int max = nums.length / 2;

        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        return Math.min(set.size(), max);
    }
}
