package queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class BOJ24511 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = parseInt(br.readLine());
        int[] a = new int[n];

        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            a[i] = parseInt(st.nextToken());
        }

        Deque<Integer> queueStack = new ArrayDeque<>();

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            int value = parseInt(st.nextToken());
            if (a[i] == 0) { // 큐인 경우에만 기본 값을 저장. 스택에는 무언가를 넣었다가 빼도 그대로 같은 값이 나오기 때문에 고려할 필요가 없음.
                queueStack.offerLast(value);
            }
        }

        int m = parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m; i++) {
            int input = parseInt(st.nextToken());
            queueStack.offerFirst(input); // 큐로만 이루어졌다고 생각해보면, 새 원소를 투입했을 때 새 원소가 첫번째 자리에 들어오고 모든 원소가 뒤로 한 칸씩 밀리는 효과와 동일함
            sb.append(queueStack.pollLast()).append(" "); // 모든 원소가 뒤로 한 칸씩 밀리면 결국 원래 마지막 원소였던 것이 튀어나옴
        }

        System.out.println(sb);
    }
}
