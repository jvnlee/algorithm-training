package programmers_course.w3;

import java.util.*;

public class W3_3_2 {
    Stack<Integer> stack;
    int visitDepth;
    int groupCnt;

    public int solution(int[][] reply) {
        visitDepth = 0;
        groupCnt = 0;
        stack = new Stack<>();

        Member[] members = new Member[reply.length];
        List<List<Integer>> graph = new ArrayList<>(); // 어떤 멤버가 가면 누가 같이 가는지 그래프
        for (int i = 0; i < members.length; i++) {
            members[i] = new Member();
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i < reply.length; i++) {
            for (int n : reply[i]) {
                graph.get(n).add(i); // n이 가면 i도 감
            }
        }

        for (int i = 1; i < members.length; i++) {
            if (members[i].groupNum == -1) { // 아직 그룹이 결정되지 않은 멤버라면
                dfs(members, graph, i); // 그룹 설정해주기
            }
        }

        boolean[] skip = new boolean[groupCnt];
        for (int i = 1; i < graph.size(); i++) {
            for (int follower : graph.get(i)) {
                if (members[i].groupNum != members[follower].groupNum) { // 그룹 번호가 다른데 연결되어있으므로
                    skip[members[follower].groupNum] = true; // 한 그룹으로 간주하고 하나는 스킵
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < groupCnt; i++) {
            if (!skip[i]) answer++; // 스킵된거 제외하고 그룹 개수 세기
        }
        return answer;
    }

    private int dfs(Member[] members, List<List<Integer>> graph, int memberNum) {
        members[memberNum].visit = visitDepth;
        stack.push(memberNum);

        int returnValue = visitDepth;
        visitDepth++;

        for (int follower : graph.get(memberNum)) {
            if (members[follower].visit == -1) {
                returnValue = Math.min(returnValue, dfs(members, graph, follower));
            }
            if (members[follower].groupNum == -1) {
                returnValue = Math.min(returnValue, members[follower].visit);
            }
        }

        if (returnValue == members[memberNum].visit) {
            while (true) {
                int cur = stack.pop();
                members[cur].groupNum = groupCnt;
                if (cur == memberNum) break;
            }

            groupCnt++;
        }

        return returnValue;
    }

    private class Member {
        int visit = -1;
        int groupNum = -1;
    }
}
