package question_collection.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ17825 {
    private static int[] dice, order;
    private static Node[] pieces;
    private static Node start;
    private static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        dice = new int[11];
        order = new int[11];
        pieces = new Node[5];

        for (int i = 1; i <= 10; i++) {
            dice[i] = Integer.parseInt(st.nextToken());
        }

        init();
        permutation(1);
        System.out.println(answer);
    }

    private static void permutation(int depth) {
        if (depth >= 11) {
            answer = Math.max(answer, gameStart());
            return;
        }

        for (int i = 1; i <= 4; i++) {
            order[depth] = i;
            permutation(depth + 1);
        }
    }

    private static int gameStart() {
        Arrays.fill(pieces, start);

        int score = 0;
        for (int i = 1; i <= 10; i++) {
            Node cur = pieces[order[i]];
            cur.isEmpty = true;

            for (int j = 1; j <= dice[i]; j++) {
                if (j == 1 && cur.shortcut != null) {
                    cur = cur.shortcut;
                } else {
                    cur = cur.next;
                }
            }

            pieces[order[i]] = cur;

            if (!cur.isEmpty && !cur.isFinish) {
                score = 0;
                break;
            } else {
                cur.isEmpty = false;
                score += cur.score;
            }
        }

        for (int i = 1; i <= 4; i++)
            pieces[i].isEmpty = true;

        return score;
    }

    private static void init() {
        start = new Node(0);

        Node tmp = start;
        for (int i = 2; i <= 40; i += 2) {
            tmp = tmp.addNext(i);
        }

        Node end = tmp.addNext(0);
        end.isFinish = true;
        end.next = end;

        Node crossroads = new Node(25);

        tmp = crossroads.addNext(30);
        tmp = tmp.addNext(35);
        tmp.next = Node.getNode(start, 40);

        tmp = Node.getNode(start, 10);
        tmp = tmp.shortcut = new Node(13);
        tmp = tmp.addNext(16);
        tmp = tmp.addNext(19);
        tmp.next = crossroads;

        tmp = Node.getNode(start, 20);
        tmp = tmp.shortcut = new Node(22);
        tmp = tmp.addNext(24);
        tmp.next = crossroads;

        tmp = Node.getNode(start, 30);
        tmp = tmp.shortcut = new Node(28);
        tmp = tmp.addNext(27);
        tmp = tmp.addNext(26);
        tmp.next = crossroads;
    }

    private static class Node {
        int score;
        boolean isEmpty;
        boolean isFinish;
        Node next;
        Node shortcut;

        public Node(int score) {
            this.score = score;
            this.isEmpty = true;
        }

        public Node addNext(int score) {
            Node nextNode = new Node(score);
            this.next = nextNode;
            return nextNode;
        }

        public static Node getNode(Node start, int target) {
            Node tmp = start;

            while (true) {
                if (tmp == null) {
                    return null;
                }

                if (tmp.score == target) {
                    return tmp;
                }
                tmp = tmp.next;
            }
        }
    }
}
