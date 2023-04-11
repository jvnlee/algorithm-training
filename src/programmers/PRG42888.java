package programmers;

import java.util.*;

public class PRG42888 {
    public String[] solution(String[] record) {
        List<String> result = new ArrayList<>();
        Map<String, String> users = new HashMap<>();

        for (String r : record) {
            String[] parts = r.split(" ");
            String action = parts[0];
            String uid = parts[1];

            if (action.equals("Enter")) {
                result.add(uid + "님이 들어왔습니다.");
                users.put(uid, parts[2]);
            } else if (action.equals("Leave")) {
                result.add(uid + "님이 나갔습니다.");
            } else if (action.equals("Change")) {
                users.put(uid, parts[2]);
            }
        }

        for (int i = 0; i < result.size(); i++) {
            String r = result.get(i);
            String uuid = r.split("님")[0];
            String username = users.get(uuid);
            String newRecord = r.replace(uuid, username);
            result.set(i, newRecord);
        }

        return result.toArray(new String[0]);
    }
}
