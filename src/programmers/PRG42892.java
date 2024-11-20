package programmers;

import java.util.*;

public class PRG42892 {
    private int[][] answer;
    private int idx;

    public int[][] solution(int[][] nodeinfo) {
        int len = nodeinfo.length;

        Node[] nodes = new Node[len];

        for (int i = 0; i < len; i++) {
            nodes[i] = new Node(nodeinfo[i][0], nodeinfo[i][1], i + 1);
        }

        // y값 큰 순, x값 작은 순 (위 -> 아래, 좌 -> 우 방향으로 정렬)
        Arrays.sort(nodes, (n1, n2) -> n2.y - n1.y == 0 ? n1.x - n2.x : n2.y - n1.y);

        Node root = nodes[0];

        for (int i = 1; i < nodes.length; i++) {
            addNode(root, nodes[i]);
        }

        answer = new int[2][len];

        buildPre(root);

        idx = 0;
        buildPost(root);

        return answer;
    }

    private class Node {
        int x;
        int y;
        int num;
        Node left;
        Node right;

        public Node(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
            this.left = null;
            this.right = null;
        }
    }

    private void addNode(Node parent, Node node) {
        if (parent.x > node.x) {
            if (parent.left == null) {
                parent.left = node;
            } else {
                addNode(parent.left, node);
            }
        } else {
            if (parent.right == null) {
                parent.right = node;
            } else {
                addNode(parent.right, node);
            }
        }
    }

    private void buildPre(Node root) {
        if (root != null) {
            answer[0][idx++] = root.num;
            buildPre(root.left);
            buildPre(root.right);
        }
    }

    private void buildPost(Node root) {
        if (root != null) {
            buildPost(root.left);
            buildPost(root.right);
            answer[1][idx++] = root.num;
        }
    }
}
