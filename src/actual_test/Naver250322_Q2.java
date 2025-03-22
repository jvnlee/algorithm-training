package actual_test;

import java.util.*;

public class Naver250322_Q2 {
    public int[] solution(int[][] request, int n, int a, int b) {
        int[] answer = new int[request.length];

        PriorityQueue<User> users = new PriorityQueue<>(Comparator.comparingInt(u -> u.lastAccept));
        Map<Integer, User> userMap = new HashMap<>();

        for (int i = 0; i < request.length; i++) {
            int time = request[i][0];
            int id = request[i][1];

            while (!users.isEmpty()) {
                User oldUser = users.peek();
                if (time - oldUser.lastAccept >= a) {
                    users.poll();
                    userMap.remove(oldUser.id);
                } else {
                    break;
                }
            }

            if (userMap.containsKey(id)) {
                User existingUser = userMap.get(id);
                users.remove(existingUser);

                User newUser = new User(id, time);
                users.offer(newUser);
                userMap.put(id, newUser);

                answer[i] = users.size();
            } else {
                if (users.size() < n) {
                    User newUser = new User(id, time);
                    users.offer(newUser);
                    userMap.put(id, newUser);

                    answer[i] = users.size();
                } else {
                    User oldestUser = users.peek();

                    if (time - oldestUser.lastAccept >= b) {
                        users.poll();
                        userMap.remove(oldestUser.id);

                        User newUser = new User(id, time);
                        users.offer(newUser);
                        userMap.put(id, newUser);

                        answer[i] = users.size();
                    } else {
                        answer[i] = -1;
                    }
                }
            }
        }

        return answer;
    }

    private class User {
        int id;
        int lastAccept;

        public User(int id, int lastAccept) {
            this.id = id;
            this.lastAccept = lastAccept;
        }
    }
}

/*
고민했던 부분:
이미 채팅방에 있던 유저의 lastAccept를 새로 갱신할 방법

시험 당시 채택한 방식:
userMap에 users에 들어간 User 객체와 동일한 객체를 보관하고,
갱신이 필요할 때는 users.remove()로 userMap에서 유저 객체를 찾아 제거 후 새로운 유저 삽입

대안:
내가 선택했던 방식 remove()는 O(N) 시간복잡도를 가짐 (일치하는 객체를 순차적으로 탐색하는 모양)
우선순위큐는 힙 자료구조로, 최대 최소 정렬에는 유리하지만 특정 원소를 찾는 것에는 불리하므로 지양

일종의 soft delete 방식을 사용해볼 수 있음
deletedUsers에 제거된 유저의 ID를 보관하고, 유저 검증 시 여기서 제거 여부를 판단하여 제거된 유저는 스킵 가능
대신 구현 복잡도가 올라감
 */