package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ12789 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        Queue<Integer> queueLine = new LinkedList<>(); // 기존 줄

        for (int i = 0; i < n; i++) {
            queueLine.offer(Integer.parseInt(st.nextToken()));
        }

        Stack<Integer> stackLine = new Stack<>(); // 보조 줄

        int currentStudent = 0; // 가장 최근에 간식을 받은 학생의 번호표

        while (!queueLine.isEmpty()) {
            int student = queueLine.peek();

            if (student == currentStudent + 1) {
                queueLine.poll();
                currentStudent++;
            } else if (!stackLine.isEmpty() && stackLine.peek() == currentStudent + 1) {
                stackLine.pop();
                currentStudent++;
            } else {
                queueLine.poll();
                stackLine.push(student);
            }
        }

        while (!stackLine.isEmpty()) {
            int student = stackLine.peek();

            if (student == currentStudent + 1) {
                stackLine.pop();
                currentStudent++;
            } else {
                break;
            }
        }

        if (currentStudent == n) {
            System.out.println("Nice");
        } else {
            System.out.println("Sad");
        }
    }
}
