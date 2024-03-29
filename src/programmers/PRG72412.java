package programmers;

import java.util.*;

public class PRG72412 {
    public Map<String, ArrayList<Integer>> allInfo;
    public ArrayList<Integer> scores;

    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        allInfo = new HashMap<>();

        for (int i = 0; i < info.length; i++) {
            dfs("", 0, info[i].split(" "));
        }

        List<String> list = new ArrayList<>(allInfo.keySet());
        for (int i = 0; i < list.size(); i++) {
            List<Integer> scoreList = allInfo.get(list.get(i));
            Collections.sort(scoreList);
        }

        for (int i = 0; i < query.length; i++) {
            query[i] = query[i].replaceAll(" and ", "");
            String[] str = query[i].split(" ");
            int score = Integer.parseInt(str[1]);
            answer[i] = search(str[0], score);
        }

        return answer;
    }


    public void dfs(String pos, int depth, String[] info) {
        if (depth == 4) {
            if (!allInfo.containsKey(pos)) {
                scores = new ArrayList<>();
                scores.add(Integer.parseInt(info[4]));
                allInfo.put(pos, scores);
            } else {
                allInfo.get(pos).add(Integer.parseInt(info[4]));
            }
            return;
        }

        dfs(pos + "-", depth + 1, info);
        dfs(pos + info[depth], depth + 1, info);

    }

    public int search(String str, int score) {
        if (!allInfo.containsKey(str)) return 0;

        List<Integer> scoreList = allInfo.get(str);
        int start = 0, end = scoreList.size() - 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (scoreList.get(mid) < score) start = mid + 1;
            else end = mid - 1;
        }

        return scoreList.size() - start;
    }
}
