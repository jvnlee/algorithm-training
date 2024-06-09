package programmers_course.w3;

import java.util.*;

public class W3_2_2 {
    public int solution(int n, int c, int k, int[][] contact) {
        int answer = 0;

        // 편지 수신 횟수를 저장할 배열
        int[] receiveCnt = new int[n + 1];

        // contact 정보를 저장할 그래프
        List<List<Integer>> contactGraph = new ArrayList<>();

        // 그래프 초기화
        for (int i = 0; i < n + 1; i++) {
            contactGraph.add(new ArrayList<>());
        }

        // 그래프에 contact 정보 입력
        for (int[] info : contact) {
            contactGraph.get(info[0]).add(info[1]);
        }

        // BFS
        Queue<Integer> queue = new LinkedList<>(); // 편지를 보내야하는 사람들의 큐

        for (int i = 1; i <= c; i++) {
            queue.offer(i); // 악동클럽 회원들을 큐에 넣음 (최초 발신자들)
        }

        while (!queue.isEmpty()) {
            int sender = queue.poll();

            // 발신 작업
            contactGraph.get(sender).forEach(receiver -> {
                if (receiveCnt[receiver] == k) return; // k 만큼 받은 사람은 이미 큐에 존재하므로 패스

                receiveCnt[receiver]++; // 수신 횟수 + 1
                if (receiveCnt[receiver] == k) queue.offer(receiver); // 이번에 수신하면서 수신 횟수가 k가 되었다면 큐에 넣음
            });
        }

        for (int i = c + 1; i <= n; i++) {
            if (receiveCnt[i] == 0) answer++;
        }

        return answer;
    }

    /*
    편지를 보내야할 사람들이 모두 편지를 보낸 상태에서 한 통도 받지 못한 사람들을 찾아야함

    이 작업은 발신자가 가진 연락처의 모든 수신자에게 편지를 보내고,
    또 그 수신자들 중에 편지 발신 조건이 만족되는 사람이 자신이 가진 연락처의 모든 수신자에게 보내는 작업의 연속
    따라서 해당 문제는 그래프 구조에서 인접한 모든 노드부터 탐색하는 BFS 문제임

    편지를 보내야할 사람들을 큐에 넣으면서 각자가 자신이 가진 수신자 연락처로 편지를 보내도록 하면 됨
     */
}
