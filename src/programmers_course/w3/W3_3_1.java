package programmers_course.w3;

import java.util.*;

public class W3_3_1 {
    public int solution1(int n) {
        int answer = 0;

        // DFS
        Stack<Parentheses> stack = new Stack<>();
        stack.push(new Parentheses(0, 0)); // 아무것도 없는 상태부터 시작

        // 유효한 케이스에 대해서만 괄호들을 하나씩 추가해보면서 끝까지 진행
        while (!stack.isEmpty()) {
            Parentheses p = stack.pop();

            if (p.opened > n) continue; // 여는 괄호는 전체 쌍의 개수보다는 작아야함
            if (p.closed > p.opened) continue; // 닫는 괄호가 여는 괄호보다 많으면 안됨
            if (p.opened + p.closed == 2 * n) { // 그 외의 경우에서 여는 괄호와 닫는 괄호의 합이 2n이라면
                answer++; // 유효한 케이스
                continue;
            }

            // 스택에 다음 경우의 수 삽입
            stack.push(new Parentheses(p.opened + 1, p.closed)); // 여는 괄호를 하나 추가하거나
            stack.push(new Parentheses(p.opened, p.closed + 1)); // 닫는 괄호를 하나 추가한 케이스
        }

        return answer;
    }

    private class Parentheses {
        int opened;
        int closed;

        Parentheses(int opened, int closed) {
            this.opened = opened;
            this.closed = closed;
        }
    }

    /*
    DFS 풀이

    여는 괄호와 닫는 괄호 모두 0개인 상태에서부터 둘 중 한개의 괄호를 하나씩 추가한 케이스를 스택에 넣음.
    스택에서 가장 최근 요소를 꺼내 유효성을 검증하면서 또 괄호 하나를 추가한 케이스를 스택에 넣는 과정을 반복하다보면
    괄호를 유효한 방식으로 최대 길이까지 배치해보는 것과 동일한 효과.

    최대 길이까지 배치해보는 것은 결국 최대 깊이까지 탐색해보는 DFS와 동일한 컨셉
     */

    public int solution2(int n) {
        int[] dp = new int[n + 1];

        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }

        return dp[n];
    }

    /*
    DP 풀이 (점화식을 이용한 풀이)

    괄호쌍 n개로 만들어낼 수 있는 모든 유효한 괄호 배치를 f(n)개라고 했을 때,
    f(n) = f(0) * f(n - 1) + f(1) * f (n - 2) + ... + f(n - 2) * f(1) + f(n - 1) * f(0)
     */
}
